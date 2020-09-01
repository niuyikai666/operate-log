package cn.wwmxd.parser;

import cn.wwmxd.EnableModifyLog;
import cn.wwmxd.service.IService;
import cn.wwmxd.util.ReflectionUtils;
import cn.wwmxd.util.SpringUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基础解析类
 * 单表编辑时可以直接使用id来查询
 * 如果为多表复杂逻辑，请自行编写具体实现类
 *
 * @author lw
 */
@Component
public class DefaultContentParse implements ContentParser {

    @Override
    public Object getOldResult(JoinPoint joinPoint, EnableModifyLog enableModifyLog) {
        Object info = joinPoint.getArgs()[0];
        Object id = ReflectionUtils.getFieldValue(info, "id");
        Assert.notNull(id, "未解析到id值，请检查前台传递参数是否正确");
        Class idType = enableModifyLog.idType();
        if (idType.isInstance(id)) {
            Class cls = enableModifyLog.serviceclass();
            IService service = (IService) SpringUtil.getBean(cls);
            Object result = service.selectById(idType.cast(id));
            return result;
        } else {
            throw new RuntimeException("请核实id type");
        }
    }

    @Override
    public Object getNewResult(JoinPoint joinPoint, EnableModifyLog enableModifyLog) {
        return getOldResult(joinPoint, enableModifyLog);
    }

    public Object getChildResult(JoinPoint joinPoint, EnableModifyLog enableModifyLog) {
        Class[] serviceClasses = enableModifyLog.serviceClasses();
        String[] idNames = enableModifyLog.idNames();
        Object object = null;
        object = joinPoint.getArgs()[0];//父
        Object id = null;
        for (int i = 0; i <= serviceClasses.length - 1; i++) {
            if (i == 0) {//父
                object = joinPoint.getArgs()[0];//父
                id = ReflectionUtils.getFieldValue(object, idNames[0]);
                IService service = (IService) SpringUtil.getBean(serviceClasses[0]);
                object = service.selectById(id);
            } else {
                IService service = (IService) SpringUtil.getBean(serviceClasses[i]);
                id = ReflectionUtils.getFieldValue(object, idNames[i]);
                object = service.selectById(id);
            }
        }
        return object;
    }

    private Object getObject(Class cls, Object id) {
        IService service = (IService) SpringUtil.getBean(cls);
        Object obj = service.selectById(id);
        return obj;
    }


}

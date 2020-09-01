package cn.log;

import cn.log.service.IService;
import cn.log.parser.DefaultContentParse;
import cn.log.util.ModifyName;

import java.lang.annotation.*;

/**
 * 记录编辑详细信息的标注
 *
 * @author lw
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EnableModifyLog {
    /**
     * @return 操作的类型 可以直接调用ModifyName 不传时根据METHOD自动确定
     */
    ModifyName modifyType() default ModifyName.NONE;

    /**
     * @return 获取编辑信息的解析类，目前为使用id获取，复杂的解析需要自己实现，默认不填写
     * 则使用默认解析类
     */
    Class parseclass() default DefaultContentParse.class;

    /**
     * @return 查询数据库所调用的class文件
     */
    Class serviceclass() default IService.class;


    /**
     * 父查询,子查询,class文件
     *
     * @return
     */
    Class[] serviceClasses() default {};

    /**
     * @return 具体业务操作名称
     */
    String handleName() default "";

    /**
     * @return 是否需要默认的改动比较
     */
    boolean needDefaultCompare() default false;

    /**
     * @return id的类型(单层查询, 单表的)
     */
    Class idType() default String.class;

    String[] idNames() default {};

    /**
     * 子查询对象的类型
     *
     * @return
     */
    Class objType() default Object.class;
}

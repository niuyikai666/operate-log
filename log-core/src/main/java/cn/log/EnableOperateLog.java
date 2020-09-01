package cn.log;

import cn.log.Interceptor.ModifyAspect;
import cn.log.util.SpringUtil;
import cn.log.parser.DefaultContentParse;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author liwei
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({ModifyAspect.class, SpringUtil.class, DefaultContentParse.class})
public @interface EnableOperateLog {
}

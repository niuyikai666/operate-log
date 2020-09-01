package cn.log.config;

import cn.log.Interceptor.ModifyAspect;
import cn.log.parser.DefaultContentParse;
import cn.log.util.SpringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liwei
 */
@Configuration
@Import({ModifyAspect.class, SpringUtil.class, DefaultContentParse.class})
public class LogStarterAutoConfiguration {
}

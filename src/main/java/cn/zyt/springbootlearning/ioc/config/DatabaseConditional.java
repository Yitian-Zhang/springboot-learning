package cn.zyt.springbootlearning.ioc.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 条件装配Bean
 */
public class DatabaseConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 获取环境配置
        Environment environment = conditionContext.getEnvironment();
        // 检查配置文件中是否包含相应的属性
        return environment.containsProperty("database.drivername")
                && environment.containsProperty("database.url")
                && environment.containsProperty("database.username")
                && environment.containsProperty("database.password");
    }
}

package cn.zyt.springbootlearning.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 使用Spring Security配置Actuator端点的访问用户和权限
 * 在Spring Boot中加入spring security的依赖以默认开启了spring security，无需注解
 *
 * @author yitian
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 密码编码器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 使用内存存储
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .and()
                .withUser("yitian")
                .password(passwordEncoder.encode("yitian"))
                .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 根据角色设置访问权限
        setRoleAuthorize(http);

        // 根据Endpoint这是访问权限
//        setEndpointAuthorize(http);
    }

    private void setDefaultAuthorize(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/logoutResult");
    }

    private void setRoleAuthorize(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/**").hasRole("ADMIN")
                .antMatchers("/web/index").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/logoutResult"); // 这里的响应controller位于WebSecurityConfiguration
    }

    /**
     * 暂时有点问题：
     * Can't configure antMatchers after anyRequest
     */
    private void setEndpointAuthorize(HttpSecurity http) throws Exception {
        String[] endPoints = {"auditevents", "beans", "conditions", "configprops", "env", "flyway",
                "httptrace", "loggers", "liquibase", "metrics", "mappings", "scheduletasks", "sessions", "shutdown",
                "threaddump"};

        http.requestMatcher(EndpointRequest.to(endPoints))
                .authorizeRequests().anyRequest().hasRole("ADMIN")
                .antMatchers("/web/index").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/logoutResult"); // 这里的响应controller位于WebSecurityConfiguration
    }

    private void disableCsrf(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}

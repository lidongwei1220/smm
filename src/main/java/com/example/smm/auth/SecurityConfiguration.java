package com.example.smm.auth;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lidongwei
 * @date 2019-10-24
 **/

//@Configuration
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String DEV_ENVIRONMENTS = "dev";

    protected String active(){
        return "dev";
    }

    protected long expiration(){
        return 10 * 1000;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/login");
        web.ignoring().antMatchers("/", "/console", "/console/**", "/static/**", "/*.png", "/*.js", "/*.css");
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (DEV_ENVIRONMENTS.equals(active())) {
            http.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers("/**").permitAll();
        } else {
            http.cors().and().csrf().disable().authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .addFilter(new JwtLoginFilter(authenticationManager() , expiration()))
                    .addFilter(new JwtAuthenticationFilter(authenticationManager()));

        }
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}

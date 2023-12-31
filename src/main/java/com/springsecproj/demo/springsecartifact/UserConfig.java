package com.springsecproj.demo.springsecartifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter {
    @Value("${user.authority.developer}")
    private String DEVELOPER_AUTHORITY;
    @Value("${user.authority.devops}")
    private String DEVOPS_AUTHORITY;

    @Autowired
    private UserService userService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/devops/**").hasAnyAuthority(DEVOPS_AUTHORITY)
                .antMatchers(HttpMethod.POST,"/sre/**").hasAnyAuthority(DEVELOPER_AUTHORITY)
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }
    @Bean
    public PasswordEncoder getPe(){
        return new BCryptPasswordEncoder(10);
    }
}

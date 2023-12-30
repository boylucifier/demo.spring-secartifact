package com.springsecproj.demo.springsecartifact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class StudentConfig extends WebSecurityConfigurerAdapter {
    private final String DEVELOPER_AUTHORITY = "developer";
    private final String DEVOPS_AUTHORITY = "devops";

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("Raj")
                .password("jaiHo")
                .authorities(DEVOPS_AUTHORITY)
                .and()
                .withUser("Samyak")
                .password("hojai")
                .authorities(DEVELOPER_AUTHORITY,DEVOPS_AUTHORITY)
                .and()
                .withUser("akash")
                .password("hohijai")
                .authorities(DEVELOPER_AUTHORITY);
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
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

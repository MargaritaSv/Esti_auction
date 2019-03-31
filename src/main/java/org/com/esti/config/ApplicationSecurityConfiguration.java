package org.com.esti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                    .withUser("USER")
//                        .password("password")
//                        .roles("USER")
//                        .and()
//                  .withUser("ADMIN")
//                        .password("password")
//                        .roles("ADMIN","USER");
                        //.and();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().disable()
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/js/**", "/css/**").permitAll()
//                    .antMatchers("/", "/user/register", "/user/login").anonymous()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/user/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/index")
//                    .permitAll()
//                    .and()
//                .logout()
//                .logoutSuccessUrl("/index");
    }
}

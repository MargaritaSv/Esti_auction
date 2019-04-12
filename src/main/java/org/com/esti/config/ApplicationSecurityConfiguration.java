package org.com.esti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

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
//                        .roles("ADMIN","USER")
//                        .and();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers( "/resources/**","/js/**", "/css/**").permitAll()
                    .antMatchers( "/","/department/**", "/private/**", "/user/register", "/user/login").anonymous()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/user/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    // .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .and()
                .rememberMe()
                    .key("my-secure-key")
                    .rememberMeCookieName("my-remember-me-cookie")
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(24 * 60 * 60)
                    .and()
                    .exceptionHandling()
                   // .key("workerKey");
                    .and();

//        http
//                .headers()
//                .frameOptions().sameOrigin()
//                .and()
//                    .authorizeRequests()
//                    .antMatchers("/resources/*", "/css/**", "/images/**", "/js/**").permitAll()
//                    .antMatchers("/", "/department/**", "/private/**", "/user/**").permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/index")
//                    .failureUrl("/login?error")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/login?logout")
//                    .deleteCookies("my-remember-me-cookie")
//                    .permitAll()
//                    .and()
//                .rememberMe()
//                //.key("my-secure-key")
//                .rememberMeCookieName("my-remember-me-cookie")
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(24 * 60 * 60)
//                .and()
//                .exceptionHandling()
//        ;
    }

    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}

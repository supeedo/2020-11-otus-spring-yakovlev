package ru.library.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    public SecurityConfiguration(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                  .authorizeRequests()
                  .antMatchers("/", "index", "index.html").permitAll()
                .and()
                  .authorizeRequests()
                .antMatchers("/book").authenticated()
                .antMatchers("/books").authenticated()
                .antMatchers("/edit-book/**").hasAnyRole(ADMIN)
                .antMatchers("/add-book/**").hasAnyRole(ADMIN)
                .antMatchers("/delete/**").hasAnyRole(ADMIN)
                .antMatchers("/authors/**").hasAnyRole(ADMIN, USER)
                .antMatchers("/comment").hasAnyRole(ADMIN, USER)
                .antMatchers("/genres").hasAnyRole(ADMIN, USER)
                .antMatchers("/**").authenticated()
                .and()
                  .formLogin().defaultSuccessUrl("/books")
                  .and()
                .rememberMe()
                  .key("someKey").tokenValiditySeconds(60 * 60 * 24 * 90);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

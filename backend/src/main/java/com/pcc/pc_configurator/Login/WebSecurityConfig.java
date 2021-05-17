package com.pcc.pc_configurator.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    //UserRepository userRepository = null;
//
    //    auth.inMemoryAuthentication()
    //            .withUser(/*userRepository.findAllByEmail("yaubrey0@nature.com").get(0).getEmail()*/"admin")
    //            .password(passwordEncoder().encode(/*userRepository.findAllByEmail("yaubrey0@nature.com").get(0).getPassword()*/"admin"))
    //            .roles("admin");
//
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/list_users").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/loginSuccess")
                .permitAll()
                .and()
                .oauth2Login().defaultSuccessUrl("/loginSuccess")
                .and()
                .logout().logoutSuccessUrl("/logoutsucces").permitAll();
    }
}

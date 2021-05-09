package com.pcc.pc_configurator.Login;

import com.pcc.pc_configurator.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //UserRepository userRepository = null;

        auth.inMemoryAuthentication()
                .withUser(/*userRepository.findAllByEmail("yaubrey0@nature.com").get(0).getEmail()*/"admin")
                .password(passwordEncoder().encode(/*userRepository.findAllByEmail("yaubrey0@nature.com").get(0).getPassword()*/"admin"))
                .roles("admin");

    }


   // @Override
   // protected void configure(HttpSecurity http) throws Exception{
   //     UserRepository userRepository;
   //     http.antMatcher("/**").authorizeRequests()
   //             .antMatchers("/products").permitAll()
   //             .anyRequest().authenticated()
   //             .and()
   //             .oauth2Login();
   //     //userRepository.save()
   // }
}

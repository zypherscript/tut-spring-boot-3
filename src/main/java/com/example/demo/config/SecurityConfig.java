package com.example.demo.config;

import com.example.demo.vaadin.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().ignoringRequestMatchers("/graphql")
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/graphql", "/test/**")
        .permitAll();
    super.configure(http);
    setLoginView(http, LoginView.class);
  }

  @Bean
  public UserDetailsService users() {
    var admin = User.builder()
        .username("admin")
        .password("{noop}p@ssw0rd")
        .roles("USER", "ADMIN")
        .build();
    return new InMemoryUserDetailsManager(admin);
  }

}
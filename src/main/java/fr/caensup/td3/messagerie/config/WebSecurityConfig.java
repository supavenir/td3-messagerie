package fr.caensup.td3.messagerie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import fr.caensup.td3.messagerie.services.DbUserLoginService;

@Configuration // (1)
@EnableWebSecurity // (1)
public class WebSecurityConfig {

  @Bean // (2)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/rest/**", "/init/**", "/h2-console/**", "/css/**", "/assets/**").permitAll() // (3)
        .anyRequest().authenticated() // (4)
        .and().formLogin() // (5)
        .loginPage("/login") // (5)
        .permitAll().and().logout() // (6)
        .permitAll().and().httpBasic(); // (7)
    http.headers().frameOptions().sameOrigin(); // (8)
    http.csrf().disable();
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new DbUserLoginService(); // (2)
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() { // (2)
    return new BCryptPasswordEncoder();
  }
}

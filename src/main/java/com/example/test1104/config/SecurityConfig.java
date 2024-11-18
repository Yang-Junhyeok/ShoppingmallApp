package com.example.test1104.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()).formLogin((formLogin) -> formLogin.loginPage("/members/login").failureUrl("/members/login/error").defaultSuccessUrl("/")).logout((logout)->logout.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")).logoutSuccessUrl("/").invalidateHttpSession(true));
//        return http.build();
//    }
@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                    .requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
            ).formLogin(formLoginCustomizer -> formLoginCustomizer
                    .loginPage("/members/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("username")
                    .failureUrl("/members/login/error")
                    .failureHandler(new CustomAuthenticationFailureHandler())
            ).logout( logoutCustomizer -> logoutCustomizer
                    .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    )
                .build();
}


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
        }
}

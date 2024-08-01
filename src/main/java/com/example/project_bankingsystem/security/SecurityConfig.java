package com.example.project_bankingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // private final CustomUserDetailsService userDetailsService;
    // private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtManager jwtManager;
    private final CustomUserDetailsService userDetailsService;

    // authorizeHttpRequests 부분 정리 필요함. httpBasic <<<
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())

                .exceptionHandling(e -> e.authenticationEntryPoint(jwtEntryPoint))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(a -> a.requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build(); // requestmatchers, anyrequest 순서 신경써야함?
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtManager, userDetailsService);
    }

    // AuthenticationManager,BCryptPasswordEncoder 빈 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration a) throws Exception {
        return a.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // "//" 이중슬래쉬 허용
    @Bean
    public HttpFirewall firewall() {
        return new DefaultHttpFirewall();
    }

    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(firewall());
    }

}

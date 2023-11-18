package org.example.security;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

//    @Bean
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .requestMatchers("/**").permitAll()
//                .and()
//                .csrf().disable();
//    }
//     return http.build();
    //private final AuthenticationProvider authenticationProvider;
    //private final JwtAuthenticationFilter jwtAuthFilter;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .requestMatchers("/**").permitAll()
            .and()
            .csrf()
            .disable();
    return http.build();

}}


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().and().csrf().disable()
//                .authorizeHttpRequests(auth-> auth
//                                .anyRequest().authenticated()
//                );
//        return http.build();
// //                       .requestMatchers("/").permitAll()
////                        .requestMatchers("/api/account/**").permitAll()
////                        .requestMatchers("/uploading/**").permitAll()
////                        .requestMatchers("/static/**").permitAll()
////                        .requestMatchers("/swagger-resources/**").permitAll()
////                        .requestMatchers("/v3/api-docs/**").permitAll()
////                        .requestMatchers("/webjars/**").permitAll()
////                        .requestMatchers("/rest-api-docs/**").permitAll()
////                        .requestMatchers("/swagger-ui/**").permitAll()
////                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
//
//
//
//                 //       .anyRequest().authenticated()
//                //)
//           //     .sessionManagement(it->it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//              //  .authenticationProvider(authenticationProvider)
//           //     .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

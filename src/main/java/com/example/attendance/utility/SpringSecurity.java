//package com.example.attendance.utility;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurity {
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((requests) -> requests
//                		.requestMatchers("/**").permitAll()
//                		.requestMatchers("/registration/**").permitAll()
//                        .requestMatchers("/upload-student-sheet/**").permitAll()
//                        .requestMatchers("/upload/**").permitAll()
//                        .requestMatchers("/save-attendance/**").permitAll()
//                        .requestMatchers("/get-account-information/**").permitAll()
//                        .requestMatchers("/login/**").permitAll()
//                        .requestMatchers("/get-account-information/**").permitAll()
//                        .requestMatchers("/employee/**").permitAll()
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/user/")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll())
//                .exceptionHandling().accessDeniedPage("/access-denied");
//        return http.build();
//    }
//}

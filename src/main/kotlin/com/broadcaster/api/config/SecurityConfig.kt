package com.broadcaster.api.config

import com.broadcaster.api.config.security.JwtAuthenticationEntryPoint
import com.broadcaster.api.config.security.JwtRequestFilter
import com.broadcaster.api.service.UserService
import com.broadcaster.api.utils.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val userService: UserService,
    private val jwtUtil: JwtUtil
) {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic().disable()
            .formLogin().disable()
            .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors()
            .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and().authorizeRequests()
            .antMatchers("/sign/**").permitAll()
            .anyRequest().authenticated()
            .and().addFilterBefore(
                JwtRequestFilter(
                    jwtUtil
                ), UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }
}
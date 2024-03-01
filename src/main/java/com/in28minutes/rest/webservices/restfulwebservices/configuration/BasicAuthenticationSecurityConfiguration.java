package com.in28minutes.rest.webservices.restfulwebservices.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
    // We have to override the filter chain regarding the coming http requests ( Filter Chain )
    /*
     * By default: All our requests are authenticated in a basic way. So we need to disable the default authentication and override this up to us.
     * Then, we need to disabled CSRF and overriding it to accept all crud requests
     * Also, we need to mention that's about a stateless REST API
     * */
    //@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       /*
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); // All our request became authenticated
        http.httpBasic(Customizer.withDefaults()); // with the default way
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // disabled session between requests. The server doesn't have the charge of storing information about clients.
        http.csrf().disable(); // disable the CSRF to allow us to pass all the crud operations over our REST API
        return http.build();
        */

        /* Also, we need to make considerations of CORS issues and handling the authentication feature in our Rest Api.
         * 1) Response to the preflight request from thr client => auth.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
         * 2) Basic Authentication URL to be able to call the basic auth url at the time of login and check the token is valid or not. it will performed in our rest controller
         */

        return http.authorizeHttpRequests(auth -> auth
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // here, we're allowing options request on all urls for everybody
                        .anyRequest().authenticated()) // All our request became authenticated
                .httpBasic(Customizer.withDefaults()) // with the default way
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // disabled session between requests. The server doesn't have the charge of storing information about clients.
                .csrf().disable() // disable the CSRF to allow us to pass all the crud operations over our REST API
                .build();
    }
}

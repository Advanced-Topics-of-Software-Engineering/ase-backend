package edu.tum.ase.ase23.config;

import edu.tum.ase.ase23.security.RequestFilter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public RequestFilter requestFilter() {
        return new RequestFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .cors()
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();

        http.addFilterBefore(requestFilter(), ChannelProcessingFilter.class);
        return http.build();
    }
}
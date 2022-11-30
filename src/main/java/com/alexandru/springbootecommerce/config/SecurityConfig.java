package com.alexandru.springbootecommerce.config;


import com.alexandru.springbootecommerce.security.CorsFilter;
import com.alexandru.springbootecommerce.security.ExceptionHandlerFilter;
import com.alexandru.springbootecommerce.security.jwt.JwtConfigurer;
import com.alexandru.springbootecommerce.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final CorsFilter corsFilter;
    private static final String AUTHENTICATION_ENDPOINT = "/auth/**";
    private static final String REGISTRATION_ENDPOINT = "/registration/**";
    private static final String ACTUATOR_ENDPOINT = "/actuator/**";
    private static final String[] SWAGGER_AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**"
    };

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, ExceptionHandlerFilter exceptionHandlerFilter,
                          CorsFilter corsFilter) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
        this.corsFilter = corsFilter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/categories/***").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/orders/**").permitAll()
                .antMatchers("/products/delete/**").permitAll()
                .antMatchers().permitAll()
                .antMatchers().permitAll()
                .antMatchers(ACTUATOR_ENDPOINT).permitAll()
                .antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
                .antMatchers(AUTHENTICATION_ENDPOINT).permitAll()
                .antMatchers("/verify/**").permitAll()
                .antMatchers(REGISTRATION_ENDPOINT).permitAll()
                .antMatchers("/test").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(exceptionHandlerFilter, ChannelProcessingFilter.class)
                .addFilterAfter(corsFilter, ExceptionHandlerFilter.class)
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}

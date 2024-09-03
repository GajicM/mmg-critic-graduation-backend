package raf.diplomski.mmgcritic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import raf.diplomski.mmgcritic.filters.JwtFilter;
import raf.diplomski.mmgcritic.services.UserService;
import raf.diplomski.mmgcritic.services.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    private final JwtFilter jwtFilter;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    public SpringSecurityConfig(JwtFilter jwtFilter, PasswordEncoder bCryptPasswordEncoder,UserService userService) {
        this.jwtFilter = jwtFilter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService=userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userService);
        authProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //TODO update later
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        requests -> requests
                                .requestMatchers(new AntPathRequestMatcher("/api/games/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/movies/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/music/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/review/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                                .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}


package github.io.Gusta_code22.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfig.setAllowedMethods(Arrays.asList("PUT", "POST", "GET", "PATCH", "DELETE", "OPTIONS", "DELETE"));

        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L);
        corsConfig.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource souce = new UrlBasedCorsConfigurationSource();

        souce.registerCorsConfiguration("/**", corsConfig);
        return souce;
    }
}

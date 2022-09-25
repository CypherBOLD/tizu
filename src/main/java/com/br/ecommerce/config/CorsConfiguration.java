package com.br.ecommerce.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {



//    @Autowired
//    private Environment env;
//
//    // Liberação de todos os endpoints e requests para o spring security
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Configuração para o H2
//        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
//            http.headers().frameOptions().disable();
//        }
//        // Desabilitado o cors
//        http.cors().and().csrf().disable();
//        // Liberando o policiamento da sessão
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        // Autorizando qualquer requisição com permissão total
//        http.authorizeRequests().anyRequest().permitAll();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration().applyPermitDefaultValues();
//        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }



    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

}

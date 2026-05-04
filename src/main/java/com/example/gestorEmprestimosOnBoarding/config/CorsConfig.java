package com.example.gestorEmprestimosOnBoarding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera todas as rotas da API
                .allowedOrigins("http://localhost:9000") // URL Frontend Quasar
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //Métodos permitidos
                .allowedHeaders("*") //Permite todos os cabeçalhos
                .allowCredentials(true);
    }
}

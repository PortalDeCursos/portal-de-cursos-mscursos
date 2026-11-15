package com.projetointegrador.cursos.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient usuariosWebClient(WebClient.Builder builder,
                                       @Value("${servico-usuarios.url}") String url) {

        return builder.baseUrl(url).build();
    }
}

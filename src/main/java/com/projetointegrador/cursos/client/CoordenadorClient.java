package com.projetointegrador.cursos.client;

import com.projetointegrador.cursos.business.dto.CoordenadorDTO;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
import com.projetointegrador.cursos.infrastructure.exception.ServicoIndisponivelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@RequiredArgsConstructor
public class CoordenadorClient {

    private final WebClient webClient;

    public CoordenadorDTO buscarPorId(Long id, String token) {

        try {

            return webClient.get()
                    .uri("/coordenadores/{id}", id)
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(CoordenadorDTO.class)
                    .block();

        } catch (WebClientResponseException.NotFound e) {

            throw new ResourceNotFoundException("Coordenador não encontrado com id: " + id);

        } catch (WebClientResponseException.Unauthorized e) {

            throw new RuntimeException("Token inválido ou ausente");

        } catch (WebClientRequestException e) {

            throw new ServicoIndisponivelException("servico-usuarios");

        }
    }
}

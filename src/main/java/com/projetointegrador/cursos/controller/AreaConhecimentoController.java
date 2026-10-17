package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.AreaConhecimentoService;
import com.projetointegrador.cursos.business.dto.request.AreaConhecimentoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.AreaConhecimentoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/areas-conhecimento")
@RequiredArgsConstructor
@Tag(name = "Áreas do Conhecimento", description = "Gerenciamento das Áreas de Conhecimento dos Cursos")
public class AreaConhecimentoController {

    private final AreaConhecimentoService areaConhecimentoService;

    @PostMapping
    @Operation(
            summary = "Cadastrar Área do Conhecimento",
            description = "Cadastrar uma nova área do conhecimento"
    )
    @ApiResponse(responseCode = "201", description = "Área cadastrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "409", description = "Nome não disponível")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<AreaConhecimentoResponseDTO> cadastrar(@Valid @RequestBody AreaConhecimentoRequestDTO dto) {

        AreaConhecimentoResponseDTO response = areaConhecimentoService.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}

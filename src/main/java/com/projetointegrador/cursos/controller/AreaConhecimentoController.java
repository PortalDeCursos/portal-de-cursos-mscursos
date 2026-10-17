package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.AreaConhecimentoService;
import com.projetointegrador.cursos.business.dto.request.AreaConhecimentoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.AreaConhecimentoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Área do Conhecimento",
            description = "Buscar uma área do conhecimento pelo id"
    )
    @ApiResponse(responseCode = "201", description = "Área encontrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Área não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<AreaConhecimentoResponseDTO> buscarPorId(@Parameter(description = "ID da Área") @PathVariable Long id) {

        return ResponseEntity.ok(areaConhecimentoService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(
            summary = "Buscar Área do Conhecimento",
            description = "Buscar área do conhecimento pelo nome"
    )
    @ApiResponse(responseCode = "200", description = "Área cadastrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<AreaConhecimentoResponseDTO>> buscarPorNome(@RequestParam String nome) {

        return ResponseEntity.ok(areaConhecimentoService.buscarPorNome(nome));
    }
}

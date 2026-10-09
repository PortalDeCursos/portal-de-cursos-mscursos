package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.CursoService;
import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "Gerenciamento de Cursos")
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    @Operation(
            summary = "Cadastrar Curso",
            description = "Cadastrar um novo curso"
    )
    @ApiResponse(responseCode = "201", description = "Curso cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "409", description = "Nome ou acrônimo já em uso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<CursoResponseDTO> cadastrar(@Valid @RequestBody CursoRequestDTO dto) {

        CursoResponseDTO response = cursoService.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}

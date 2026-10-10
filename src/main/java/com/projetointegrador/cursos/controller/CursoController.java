package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.CursoService;
import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    @Operation(
            summary = "Listar Cursos",
            description = "Lista os cursos cadastrados de forma paginada. Padrão: 10 por página, ordenados por nome"
    )
    @ApiResponse(responseCode = "200", description = "Cursos listados com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<Page<CursoResponseDTO>> listar(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        return ResponseEntity.ok().body(cursoService.listar(pageable));
    }
}

package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.DisciplinaService;
import com.projetointegrador.cursos.business.dto.request.DisciplinaRequestDTO;
import com.projetointegrador.cursos.business.dto.response.DisciplinaResponseDTO;
import com.projetointegrador.cursos.infrastructure.enums.SemestreEnum;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
@Tag(name = "Disciplinas", description = "Gerenciamento de Disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    @Operation(
            summary = "Cadastrar Disciplina",
            description = "Cadastrar uma nova disciplina em um curso"
    )
    @ApiResponse(responseCode = "201", description = "Disciplina cadastrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "409", description = "Código já em uso por outra disciplina")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<DisciplinaResponseDTO> cadastrar(@Valid @RequestBody DisciplinaRequestDTO dto) {

        DisciplinaResponseDTO response = disciplinaService.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar Disciplinas por Curso",
            description = "Listar todas as disciplinas vinculadas a um curso específico"
    )
    @ApiResponse(responseCode = "200", description = "Disciplinas listadas com sucesso")
    @ApiResponse(responseCode = "400", description = "ID do curso inválido")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<DisciplinaResponseDTO>> listarPorCurso(@RequestParam Long cursoId) {

        return ResponseEntity.ok(disciplinaService.listarPorCurso(cursoId));
    }

    @GetMapping("/filtros")
    @Operation(
            summary = "Filtrar Disciplinas",
            description = "Listar disciplinas de um curso com filtros opcionais por semestre e obrigatoriedade"
    )
    @ApiResponse(responseCode = "200", description = "Disciplinas listadas com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<DisciplinaResponseDTO>> filtar(
            @RequestParam Long cursoId,
            @RequestParam(required = false) SemestreEnum semestre,
            @RequestParam(required = false) Boolean obrigatoria) {

        return ResponseEntity.ok(disciplinaService.filtrar(cursoId, semestre, obrigatoria));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Disciplina",
            description = "Atualiza todos os dados de uma disciplina existente"
    )
    @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    @ApiResponse(responseCode = "409", description = "Nome ou Código já em uso por outra disciplina")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(@PathVariable Long id,
                                                           @Valid @RequestBody DisciplinaRequestDTO dto) {

        return ResponseEntity.ok().body(disciplinaService.atualizar(id, dto));
    }
}

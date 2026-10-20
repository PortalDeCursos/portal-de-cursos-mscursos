package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.CursoService;
import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.infrastructure.enums.GrauEnum;
import com.projetointegrador.cursos.infrastructure.enums.ModalidadeEnum;
import com.projetointegrador.cursos.infrastructure.enums.TurnoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @ApiResponse(responseCode = "404", description = "Campus não encontrado")
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

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Curso por ID",
            description = "Busca um curso pelo seu identificador"
    )
    @ApiResponse(responseCode = "200", description = "Curso encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<CursoResponseDTO> buscarPorId(@Parameter(description = "ID do curso") @PathVariable Long id) {

        return ResponseEntity.ok(cursoService.buscarPorId(id));
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

    @GetMapping("/busca")
    @Operation(
            summary = "Buscar Cursos",
            description = "Busca cursos de forma paginada por grau, turno ou modalidade. Sem filtro, lista todos"
    )
    @ApiResponse(responseCode = "200", description = "Cursos listados com sucesso")
    @ApiResponse(responseCode = "400", description = "Valor de filtro inválido")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<Page<CursoResponseDTO>> buscar(@RequestParam(required = false) GrauEnum grau,
                                                         @RequestParam(required = false) TurnoEnum turno,
                                                         @RequestParam(required = false) ModalidadeEnum modalidade,
                                                         @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        return ResponseEntity.ok(cursoService.buscar(grau, turno, modalidade, pageable));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Curso",
            description = "Atualiza todos os dados de um curso existente"
    )
    @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "409", description = "Nome ou acrônimo já em uso por outro curso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<CursoResponseDTO> atualizar(@Parameter(description = "ID do curso") @PathVariable Long id,
                                                      @Valid @RequestBody CursoRequestDTO dto) {

        return ResponseEntity.ok().body(cursoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar Curso",
            description = "Deleta um curso existente"
    )
    @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do curso") @PathVariable Long id) {

        cursoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}

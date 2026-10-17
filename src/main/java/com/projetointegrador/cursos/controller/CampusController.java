package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.CampusService;
import com.projetointegrador.cursos.business.dto.request.CampusRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CampusResponseDTO;
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
@RequestMapping("/campus")
@RequiredArgsConstructor
@Tag(name = "Campus", description = "Gerenciamento de Campus")
public class CampusController {

    private final CampusService campusService;

    @PostMapping
    @Operation(
            summary = "Cadastrar Campus",
            description = "Cadastrar um novo campus"
    )
    @ApiResponse(responseCode = "201", description = "Campus cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    @ApiResponse(responseCode = "409", description = "Nome ou Acrônimo já em uso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<CampusResponseDTO> cadastrar(@Valid @RequestBody CampusRequestDTO dto) {

        CampusResponseDTO response = campusService.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Campus por ID",
            description = "Busca um campus pelo seu identificador"
    )
    @ApiResponse(responseCode = "200", description = "Campus encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<CampusResponseDTO> buscarPorId(@Parameter(description = "ID do campus") @PathVariable Long id) {

        return ResponseEntity.ok(campusService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(
            summary = "Listar Campus",
            description = "Lista todos os campus"
    )
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<CampusResponseDTO>> buscarPorNome(@RequestParam String nome) {

        return ResponseEntity.ok(campusService.buscarPorNome(nome));
    }

    @GetMapping
    @Operation(
            summary = "Buscar Campus por nome",
            description = "Busca um campus pelo seu nome"
    )
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<CampusResponseDTO>> listar() {

        return ResponseEntity.ok(campusService.listar());
    }

    @GetMapping("/filtros")
    @Operation(
            summary = "Filtrar Campus",
            description = "Filtrar campus por cidade"
    )
    @ApiResponse(responseCode = "200", description = "Campus listados com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<CampusResponseDTO>> filtar(@RequestParam String cidade) {

        return ResponseEntity.ok(campusService.filtrar(cidade));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Cadastrar Campus",
            description = "Cadastrar um novo campus"
    )
    @ApiResponse(responseCode = "200", description = "Campus atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    @ApiResponse(responseCode = "409", description = "Nome ou Acrônimo não disponível")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<CampusResponseDTO> atualizar(@Parameter(description = "ID do campus") @PathVariable Long id,
                                                       @Valid @RequestBody CampusRequestDTO dto) {

        return ResponseEntity.ok(campusService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar Campus",
            description = "Deleta um campus existente"
    )
    @ApiResponse(responseCode = "204", description = "Campus deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do campus") @PathVariable Long id) {

        campusService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}

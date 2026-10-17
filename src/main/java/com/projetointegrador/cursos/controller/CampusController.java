package com.projetointegrador.cursos.controller;

import com.projetointegrador.cursos.business.CampusService;
import com.projetointegrador.cursos.business.dto.request.CampusRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CampusResponseDTO;
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
}

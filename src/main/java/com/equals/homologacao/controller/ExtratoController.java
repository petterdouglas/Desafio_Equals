package com.equals.homologacao.controller;

import com.equals.homologacao.dto.ExtratoDTO;
import com.equals.homologacao.service.ExtratoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresa/{numeroEmpresa}/extrato")
@RequiredArgsConstructor
@Tag(name = "Extratos", description = "Endpoints para consultar os detalhes dos extratos de uma empresa")
public class ExtratoController {

    private final ExtratoService extratoService;

    @Operation(
            summary = "Lista todos os extratos",
            description = "Retorna todos os extratos de uma empresa detentora de um certo ID"
    )
    @GetMapping("/all")
    public ResponseEntity<List<ExtratoDTO>> listarTodos(@PathVariable Long numeroEmpresa) {
        List<ExtratoDTO> extratos = extratoService.listarPorEmpresa(numeroEmpresa);
        return ResponseEntity.ok(extratos);
    }

    @Operation(
            summary = "Busca um extrato pelo id",
            description = "Retorna o extrato pelo seu ID, desde que seja de uma empresa com um certo ID"
    )
    @GetMapping("/{extratoId}")
    public ResponseEntity<ExtratoDTO> buscarPorId(@PathVariable Long extratoId, @PathVariable Long numeroEmpresa) {
        ExtratoDTO extrato = extratoService.buscarExtratoPorId(extratoId, numeroEmpresa);
        return ResponseEntity.ok(extrato);
    }

}

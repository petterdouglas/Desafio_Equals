package com.equals.homologacao.controller;

import com.equals.homologacao.dto.TransacaoDTO;
import com.equals.homologacao.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extrato/{extratoId}/transacao")
@RequiredArgsConstructor
@Tag(name = "Transações", description = "Endpoints para consultar os detalhes das transações de um extrato")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Operation(
            summary = "Listar todas as transações de um extrato",
            description = "Retorna uma lista com todas as transações pertencentes a um extrato específico, identificado pelo seu ID."
    )
    @GetMapping("/all")
    public ResponseEntity<List<TransacaoDTO>> listarTodas(@PathVariable Long extratoId) {
        List<TransacaoDTO> transacoes = transacaoService.listarPorExtrato(extratoId);
        return ResponseEntity.ok(transacoes);
    }

    @Operation(
            summary = "Busca uma transação específica de um extrato pela data",
            description = "Retorna uma transação realizada em certa data, que seja pertencente a um extrato específico, identificado pelo seu ID."
    )
    @GetMapping("/date/{dataTransacao}")
    public ResponseEntity<List<TransacaoDTO>> buscarPorData(@PathVariable Long extratoId, @PathVariable String dataTransacao) {
        List<TransacaoDTO> transacao = transacaoService.buscarTransacoesPorData(extratoId, dataTransacao);
        return ResponseEntity.ok(transacao);
    }

    @Operation(
            summary = "Busca a transação específica de um extrato pelo seu código de transação",
            description = "Retorna uma transação de um certo código, que seja pertencente a um extrato específico, identificado pelo seu ID."
    )
    @GetMapping("/codigo/{codigoTransacao}")
    public ResponseEntity<TransacaoDTO> buscarPorCodigo(@PathVariable Long extratoId, @PathVariable String codigoTransacao) {
        TransacaoDTO transacao = transacaoService.buscarTransacaoPorCodigo(extratoId, codigoTransacao);
        return ResponseEntity.ok(transacao);
    }


}

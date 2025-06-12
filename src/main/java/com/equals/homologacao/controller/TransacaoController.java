package com.equals.homologacao.controller;

import com.equals.homologacao.dto.TransacaoDetalhadaDTO;
import com.equals.homologacao.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extrato/{extratoId}/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @GetMapping("/all")
    public ResponseEntity<List<TransacaoDetalhadaDTO>> listarTodas(@PathVariable Long extratoId) {
        List<TransacaoDetalhadaDTO> transacoes = transacaoService.listarPorExtrato(extratoId);
        if (!transacoes.isEmpty()) {
            return ResponseEntity.ok(transacoes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id/{transacaoId}")
    public ResponseEntity<TransacaoDetalhadaDTO> buscarPorId(@PathVariable Long extratoId, @PathVariable Long transacaoId) {
        TransacaoDetalhadaDTO transacao = transacaoService.buscarTransacaoPorId(extratoId, transacaoId);
        if (transacao != null) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigo/{codigoTransacao}")
    public ResponseEntity<List<TransacaoDetalhadaDTO>> buscarPorCodigo(@PathVariable Long extratoId, @PathVariable String codigoTransacao) {
        List<TransacaoDetalhadaDTO> transacoes = transacaoService.buscarTransacaoPorCodigo(extratoId, codigoTransacao);

        if (!transacoes.isEmpty()) {
            return ResponseEntity.ok(transacoes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

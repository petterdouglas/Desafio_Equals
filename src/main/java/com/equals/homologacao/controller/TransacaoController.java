package com.equals.homologacao.controller;

import com.equals.homologacao.model.Transacao;
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
    public ResponseEntity<List<Transacao>> listarTodas(@PathVariable Long extratoId) {
        List<Transacao> transacoes = transacaoService.listarPorExtrato(extratoId);
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{transacaoId}")
    public Transacao buscarPorId(@PathVariable Long extratoId, @PathVariable Long transacaoId) {
        return transacaoService.buscarTransacaoPorId(extratoId, transacaoId);
    }
}

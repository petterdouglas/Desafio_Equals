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
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/id/{dataTransacao}")
    public ResponseEntity<TransacaoDetalhadaDTO> buscarPorData(@PathVariable Long extratoId, @PathVariable String dataTransacao) {
        TransacaoDetalhadaDTO transacao = transacaoService.buscarTransacaoPorId(extratoId, dataTransacao);
        return ResponseEntity.ok(transacao);
    }


    @GetMapping("/codigo/{codigoTransacao}")
    public ResponseEntity<List<TransacaoDetalhadaDTO>> buscarPorCodigo(@PathVariable Long extratoId, @PathVariable String codigoTransacao) {
        List<TransacaoDetalhadaDTO> transacoes = transacaoService.buscarTransacaoPorCodigo(extratoId, codigoTransacao);
        return ResponseEntity.ok(transacoes);
    }


}

package com.equals.homologacao.service;

import com.equals.homologacao.model.Transacao;
import com.equals.homologacao.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public List<Transacao> listarPorExtrato(Long extratoId) {
        return transacaoRepository.findAllByExtratoId(extratoId);
    }

    public Transacao buscarTransacaoPorId(Long extratoId, Long transacaoId) {
        return transacaoRepository.findByExtratoIdAndId(extratoId, transacaoId).orElse(null);
    }

    public List<Transacao> buscarTransacaoPorCodigo(Long extratoId, String codigoTransacao) {
        return transacaoRepository.findAllByExtratoIdAndCodigoTransacao(extratoId, codigoTransacao);
    }
}

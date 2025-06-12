package com.equals.homologacao.repository;

import com.equals.homologacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    Optional<Transacao> findByCodigoTransacao(String codigoTransacao);

    List<Transacao> findAllByExtratoId(Long extratoId);

    List<Transacao> findAllByExtratoIdAndCodigoTransacao(Long extratoId, String codigoTransacao);

    Optional<Transacao> findByExtratoIdAndId(Long extratoId, Long transacaoId);
}

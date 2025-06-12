package com.equals.homologacao.repository;

import com.equals.homologacao.model.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExtratoRepository extends JpaRepository<Extrato, Long> {
    List<Extrato> findAllByEmpresaId(Long empresaId);

    Optional<Extrato> findByEmpresaIdAndId(Long empresaId, Long extratoId);
}

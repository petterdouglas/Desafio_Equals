package com.equals.homologacao.repository;

import com.equals.homologacao.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCodigoEstabelecimento(String codigoEstabelecimento);
}

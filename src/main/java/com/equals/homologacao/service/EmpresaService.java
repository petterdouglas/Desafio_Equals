package com.equals.homologacao.service;

import com.equals.homologacao.model.Empresa;
import com.equals.homologacao.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;


    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Empresa buscarPorCodigo(String codigoEstabelecimento) {
        return empresaRepository.findByCodigoEstabelecimento(codigoEstabelecimento).orElse(null);
    }
}

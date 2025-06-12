package com.equals.homologacao.service;

import com.equals.homologacao.dto.EmpresaDTO;
import com.equals.homologacao.model.Empresa;
import com.equals.homologacao.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;


    public List<EmpresaDTO> listarTodas() {
        List<Empresa> empresas = empresaRepository.findAll();

        return converterParaDto(empresas);
    }

    public EmpresaDTO buscarPorCodigo(String codigoEstabelecimento) {
        Empresa empresa = empresaRepository.findByCodigoEstabelecimento(codigoEstabelecimento).orElse(null);

        return converterParaDto(empresa);
    }

    private List<EmpresaDTO> converterParaDto(List<Empresa> empresas) {

        List<EmpresaDTO> empresasDto = new ArrayList<>();

        for (Empresa empresa : empresas) {
            empresasDto.add(converterParaDto(empresa));
        }

        return empresasDto;
    }

    private EmpresaDTO converterParaDto(Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        EmpresaDTO empresaDto = new EmpresaDTO();
        empresaDto.setId(empresa.getId());
        empresaDto.setCodigoEstabelecimento(empresa.getCodigoEstabelecimento());

        return empresaDto;
    }

}

package com.equals.homologacao.service;

import com.equals.homologacao.dto.ExtratoDTO;
import com.equals.homologacao.model.Empresa;
import com.equals.homologacao.model.Extrato;
import com.equals.homologacao.model.TipoPagamento;
import com.equals.homologacao.model.Transacao;
import com.equals.homologacao.repository.EmpresaRepository;
import com.equals.homologacao.repository.ExtratoRepository;
import com.equals.homologacao.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtratoService {

    private final ExtratoRepository extratoRepository;

    public List<ExtratoDTO> listarPorEmpresa(Long empresaId) {
        List<Extrato> extratos = extratoRepository.findAllByEmpresaId(empresaId);

        return converterParaDto(extratos);
    }

    public ExtratoDTO buscarExtratoPorId(Long extratoId, Long empresaId) {
        Extrato extrato = extratoRepository.findByEmpresaIdAndId(empresaId, extratoId).orElse(null);

        return converterParaDto(extrato);
    }

    private List<ExtratoDTO> converterParaDto(List<Extrato> extratos) {

        List<ExtratoDTO> extratosDto = new ArrayList<>();

        for (Extrato extrato : extratos) {
            extratosDto.add(converterParaDto(extrato));
        }

        return extratosDto;
    }

    private ExtratoDTO converterParaDto(Extrato extrato) {
        if (extrato == null) {
            return null;
        }

        ExtratoDTO extratoDto = new ExtratoDTO();

        extratoDto.setId(extrato.getId());
        extratoDto.setDataProcessamento(extrato.getDataProcessamento());
        extratoDto.setPeriodoInicial(extrato.getPeriodoInicial());
        extratoDto.setPeriodoFinal(extrato.getPeriodoFinal());
        extratoDto.setTipoExtrato(extrato.getTipoExtrato());
        extratoDto.setTotalRegistros(extrato.getTotalRegistros());

        return extratoDto;
    }

}

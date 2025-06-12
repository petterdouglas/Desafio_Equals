package com.equals.homologacao.service;

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
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtratoService {

    private final ExtratoRepository extratoRepository;

    public List<Extrato> listarPorEmpresa(Long empresaId) {
        return extratoRepository.findAllByEmpresaId(empresaId);
    }

    public Extrato buscarExtratoPorId(Long extratoId, Long empresaId) {
        return extratoRepository.findByEmpresaIdAndId(empresaId, extratoId).orElse(null);
    }
}

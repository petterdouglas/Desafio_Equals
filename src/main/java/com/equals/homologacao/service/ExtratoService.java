package com.equals.homologacao.service;

import com.equals.homologacao.exception.DadoNaoEncontradoException;
import com.equals.homologacao.exception.SemConteudoException;
import com.equals.homologacao.dto.ExtratoDTO;
import com.equals.homologacao.model.Extrato;
import com.equals.homologacao.repository.ExtratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtratoService {

    private final ExtratoRepository extratoRepository;

    /**
     * Método responsável por listar todos os extratos de uma empresa
     *
     * @param empresaId identificador da empresa detentora do extrato
     * @return Retorna uma lista de objetos de ExtratoDTO
     * @throws SemConteudoException retorna uma mensagem de erro quando nenhum extrato é encontrado
     */
    public List<ExtratoDTO> listarPorEmpresa(Long empresaId) {
        List<Extrato> extratos = extratoRepository.findAllByEmpresaId(empresaId);
        // caso não haja extratos para a empresa buscada, retorna o erro adequado
        if (extratos.isEmpty()) {
            throw new SemConteudoException("Nenhum extrato encontrado para a empresa de ID: " + empresaId);
        }
        return converterParaDto(extratos);
    }

    /**
     * Método responsável por buscar um extrato específico de uma empresa pelo ID
     *
     * @param extratoId identificador do extrato buscado
     * @param empresaId identificador da empresa detentora do extrato
     * @return Retorna um objeto de ExtratoDTO
     * @throws DadoNaoEncontradoException retorna uma mensagem de erro quando um extrato não é encontrado
     */
    public ExtratoDTO buscarExtratoPorId(Long extratoId, Long empresaId) {
        Extrato extrato = extratoRepository.findByEmpresaIdAndId(empresaId, extratoId).orElse(null);

        // caso o extrato buscado não seja encontrado, retorna o erro adequado
        if (extrato == null) {
            throw new DadoNaoEncontradoException("Extrato de ID " + extratoId + " não encontrado");
        }

        return converterParaDto(extrato);
    }

    /**
     * Método responsável por converter uma lista dos extratos obtidos para seu DTO
     *
     * @param extratos Lista de objetos de Extrato
     * @return Retorna uma lista de objetos de ExtratoDTO
     */
    private List<ExtratoDTO> converterParaDto(List<Extrato> extratos) {

        List<ExtratoDTO> extratosDto = new ArrayList<>();

        for (Extrato extrato : extratos) {
            extratosDto.add(converterParaDto(extrato));
        }

        return extratosDto;
    }

    /**
     * Método responsável por converter uma extrato obtido para seu DTO
     *
     * @param extrato Objeto de Extrato
     * @return Retorna um objeto de ExtratoDTO
     */
    private ExtratoDTO converterParaDto(Extrato extrato) {
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

package com.equals.homologacao.service;

import com.equals.homologacao.exception.DadoNaoEncontradoException;
import com.equals.homologacao.exception.SemConteudoException;
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

    /**
     * Método responsável por listar todas as empresas cadastradas no sistema
     *
     * @return Retorna uma lista de objetos de EmpresaDTO
     * @throws SemConteudoException retorna uma mensagem de erro quando nenhuma empresa é encontrada
     */
    public List<EmpresaDTO> listarTodas() {
        List<Empresa> empresas = empresaRepository.findAll();

        if (empresas.isEmpty()) {
            throw new SemConteudoException("Nenhuma empresa foi encontrada.");
        }

        return converterParaDto(empresas);
    }

    /**
     * Método responsável por buscar uma empresa específica pelo numero do estabelecimento
     *
     * @param codigoEstabelecimento numero de empresa buscada
     * @return Retorna um objeto de EmpresaDTO
     * @throws DadoNaoEncontradoException retorna uma mensagem de erro quando nenhuma empresa é encontrada
     */
    public EmpresaDTO buscarPorCodigo(String codigoEstabelecimento) {
        Empresa empresa = empresaRepository.findByCodigoEstabelecimento(codigoEstabelecimento).orElse(null);

        if (empresa == null) {
            throw new DadoNaoEncontradoException("Empresa de numero " + codigoEstabelecimento + " não encontrada.");
        }

        return converterParaDto(empresa);
    }

    /**
     * Método responsável por converter uma lista de objetos de Empresa para o modelo do DTO
     *
     * @param empresas Lista de objetos de Empresa
     * @return Retorna uma lista de objetos de EmpresaDTO
     */
    private List<EmpresaDTO> converterParaDto(List<Empresa> empresas) {
        List<EmpresaDTO> empresasDto = new ArrayList<>();

        for (Empresa empresa : empresas) {
            empresasDto.add(converterParaDto(empresa));
        }

        return empresasDto;
    }

    /**
     * Método responsável por converter um objeto de Empresa para o modelo do DTO
     *
     * @param empresa Objeto de Empresa
     * @return Retorna um objeto de uma EmpresaDTO
     */
    private EmpresaDTO converterParaDto(Empresa empresa) {
        EmpresaDTO empresaDto = new EmpresaDTO();

        empresaDto.setId(empresa.getId());
        empresaDto.setNumeroEstabelecimento(empresa.getNumeroEstabelecimento());

        return empresaDto;
    }

}

package com.equals.homologacao.service;

import com.equals.homologacao.dto.TransacaoDTO;
import com.equals.homologacao.exception.DadoNaoEncontradoException;
import com.equals.homologacao.exception.SemConteudoException;
import com.equals.homologacao.model.Transacao;
import com.equals.homologacao.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    /**
     * Método responsável por listar todos as transações em um determinado extrato
     *
     * @param extratoId identificador do extrato detentor das transações
     * @return Retorna uma lista de objetos de TransacaoDto
     * @throws SemConteudoException retorna uma mensagem de erro quando nenhuma transação é encontrada
     */
    public List<TransacaoDTO> listarPorExtrato(Long extratoId) {
        List<Transacao> transacoes = transacaoRepository.findAllByExtratoId(extratoId);

        if (transacoes.isEmpty()) {
            throw new SemConteudoException("Nenhuma transação encontrada para o extrato de ID " + extratoId);
        }

        return converterParaDto(transacoes);
    }

    /**
     * Método responsável por buscar uma transação de um determinado extrato pela data evento da transação
     *
     * @param extratoId identificador do extrato detentor das transações
     * @return Retorna um objeto de TransacaoDTO
     * @throws DadoNaoEncontradoException retorna uma mensagem de erro quando nenhuma transação é encontrada
     */
    public List<TransacaoDTO> buscarTransacoesPorData(Long extratoId, String dataTransacao) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // formata a data enviada como String para LocalDate e poder ser usada como comparador na busca no banco de dados
        LocalDate dataEventoTransacao = LocalDate.parse(dataTransacao, dateFormatter);

        List<Transacao> transacoes = transacaoRepository.findAllByExtratoIdAndDataEvento(extratoId, dataEventoTransacao);

        if (transacoes.isEmpty()) {
            throw new DadoNaoEncontradoException("Nenhuma transação realizada na data " + dataTransacao + " pelo extrato de ID " + extratoId);
        }


        return converterParaDto(transacoes);
    }

    /**
     * Método responsável por buscar uma transação de um determinado extrato pelo código da transação
     *
     * @param extratoId       identificador do extrato detentor das transações
     * @param codigoTransacao código de identificação de uma transação
     * @return Retorna um objeto de TransacaoDTO
     */
    public TransacaoDTO buscarTransacaoPorCodigo(Long extratoId, String codigoTransacao) {
        Transacao transacao = transacaoRepository.findByExtratoIdAndCodigoTransacao(extratoId, codigoTransacao);

        if (transacao == null) {
            throw new DadoNaoEncontradoException("Nenhuma transação de código " + codigoTransacao + " realizada pelo extrato de ID " + extratoId);
        }

        return converterParaDto(transacao);
    }

    /**
     * Método responsável por converter uma lista de objetos de Transacao para seu DTO
     *
     * @param transacoes lista de objetos de Transacao
     * @return Retorna uma Lista de objetos de TransacaoDTO
     */
    private List<TransacaoDTO> converterParaDto(List<Transacao> transacoes) {

        List<TransacaoDTO> transacoesDto = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            transacoesDto.add(converterParaDto(transacao));
        }

        return transacoesDto;
    }

    /**
     * Método responsável por converter um objeto de Transacao para seu DTO
     *
     * @param transacao um objeto de Transacao
     * @return Retorna um objeto de TransacaoDTO
     */
    private TransacaoDTO converterParaDto(Transacao transacao) {
        if (transacao == null) {
            return null;
        }

        TransacaoDTO transacaoDto = new TransacaoDTO();

        transacaoDto.setId(transacao.getId());
        transacaoDto.setCodigoTransacao(transacao.getCodigoTransacao());
        transacaoDto.setCodigoAutorizacao(transacao.getCodigoAutorizacao());
        transacaoDto.setCodigoPedido(transacao.getCodigoPedido());
        transacaoDto.setCodigoCv(transacao.getCodigoCv());
        transacaoDto.setCartaoHolder(transacao.getCartaoHolder());
        transacaoDto.setCartaoBin(transacao.getCartaoBin());
        transacaoDto.setCanalEntrada(transacao.getCanalEntrada());
        transacaoDto.setDataInicioTransacao(transacao.getDataInicioTransacao());
        transacaoDto.setDataEvento(transacao.getDataEvento());
        transacaoDto.setDataPagamento(transacao.getDataPagamento());
        transacaoDto.setBandeira(transacao.getBandeira());
        transacaoDto.setTipoEvento(transacao.getTipoEvento());
        transacaoDto.setTipoTransacao(transacao.getTipoTransacao());
        transacaoDto.setNsuDaTransacao(transacao.getNsuDaTransacao());
        transacaoDto.setNumeroLogico(transacao.getNumeroLogico());
        transacaoDto.setNumeroSerieLeitor(transacao.getNumeroSerieLeitor());
        transacaoDto.setHoraEvento(transacao.getHoraEvento());
        transacaoDto.setLeitor(transacao.getLeitor());
        transacaoDto.setParcelaLiberada(transacao.getParcelaLiberada());
        transacaoDto.setPlano(transacao.getPlano());
        transacaoDto.setRepasseAplicacao(transacao.getRepasseAplicacao());
        transacaoDto.setQuantidadeParcelas(transacao.getQuantidadeParcelas());
        transacaoDto.setValorTotal(transacao.getValorTotal());
        transacaoDto.setValorParcela(transacao.getValorParcela());
        transacaoDto.setValorLiquidoTransacao(transacao.getValorLiquidoTransacao());
        transacaoDto.setValorOriginal(transacao.getValorOriginal());
        transacaoDto.setTaxaParcelamentoVendedor(transacao.getTaxaParcelamentoVendedor());
        transacaoDto.setTaxaIntermediacao(transacao.getTaxaIntermediacao());
        transacaoDto.setTaxaParcelamentoComprador(transacao.getTaxaParcelamentoComprador());
        transacaoDto.setTarifaIntermediacao(transacao.getTarifaIntermediacao());
        transacaoDto.setTarifaBoletoComprador(transacao.getTarifaBoletoComprador());
        transacaoDto.setTarifaBoletoVendedor(transacao.getTarifaBoletoVendedor());
        transacaoDto.setMeioCaptura(transacao.getMeioCaptura());
        transacaoDto.setMeioPagamento(transacao.getMeioPagamento());
        transacaoDto.setStatusPagamento(transacao.getStatusPagamento());
        transacaoDto.setTipoPagamento(transacao.getTipoPagamento().toString());

        return transacaoDto;
    }

}

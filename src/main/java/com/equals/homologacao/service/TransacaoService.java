package com.equals.homologacao.service;

import com.equals.homologacao.dto.TransacaoDetalhadaDTO;
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

    public List<TransacaoDetalhadaDTO> listarPorExtrato(Long extratoId) {
        List<Transacao> transacoes = transacaoRepository.findAllByExtratoId(extratoId);

        return converterParaDto(transacoes);
    }

    public TransacaoDetalhadaDTO buscarTransacaoPorId(Long extratoId, String dataTransacao) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // formata a data enviada como String para LocalDate e poder ser usada como comparador na busca no banco de dados
        LocalDate dataEventoTransacao = LocalDate.parse(dataTransacao, dateFormatter);

        Transacao transacao = transacaoRepository.findByExtratoIdAndDataEvento(extratoId, dataEventoTransacao).orElse(null);

        return converterParaDto(transacao);
    }

    public List<TransacaoDetalhadaDTO> buscarTransacaoPorCodigo(Long extratoId, String codigoTransacao) {
        List<Transacao> transacoes = transacaoRepository.findAllByExtratoIdAndCodigoTransacao(extratoId, codigoTransacao);

        return converterParaDto(transacoes);
    }

    private List<TransacaoDetalhadaDTO> converterParaDto(List<Transacao> transacoes) {

        List<TransacaoDetalhadaDTO> transacoesDto = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            transacoesDto.add(converterParaDto(transacao));
        }

        return transacoesDto;
    }

    private TransacaoDetalhadaDTO converterParaDto(Transacao transacao) {
        if (transacao == null) {
            return null;
        }

        TransacaoDetalhadaDTO transacaoDto = new TransacaoDetalhadaDTO();

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

package com.equals.homologacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {

    private Long id;

    private LocalDate dataInicioTransacao;

    private LocalDate dataEvento;

    private LocalTime horaEvento;

    private String tipoEvento;

    private String tipoTransacao;

    private String numeroSerieLeitor;

    private String codigoTransacao;

    private String codigoPedido;

    private Long valorTotal;

    private Long valorParcela;

    private String tipoPagamento;

    private Integer plano;

    private Integer parcelaLiberada;

    private Integer quantidadeParcelas;

    private LocalDate dataPagamento;

    private Long taxaParcelamentoComprador;

    private Long tarifaBoletoComprador;

    private Long valorOriginal;

    private Long taxaParcelamentoVendedor;

    private Long taxaIntermediacao;

    private Long tarifaIntermediacao;

    private Long tarifaBoletoVendedor;

    private Long repasseAplicacao;

    private Long valorLiquidoTransacao;

    private String statusPagamento;

    private String meioPagamento;

    private String bandeira;

    private String canalEntrada;

    private String leitor;

    private String meioCaptura;

    private String numeroLogico;

    private String nsuDaTransacao;

    private String cartaoBin;

    private String cartaoHolder;

    private String codigoAutorizacao;

    private String codigoCv;

}

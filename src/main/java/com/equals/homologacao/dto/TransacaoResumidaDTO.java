package com.equals.homologacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoResumidaDTO {

    private Long id;

    private LocalDate dataInicioTransacao;

    private LocalDate dataEvento;

    private LocalTime horaEvento;

    private String tipoTransacao;

    private String codigoTransacao;

    private String codigoPedido;

    private Long valorTotal;

    private Long valorParcela;

    private String tipoPagamento;

    private Integer parcelaLiberada;

    private Integer quantidadeParcelas;

    private LocalDate dataPagamento;

    private Long repasseAplicacao;

    private Long valorLiquidoTransacao;

    private String statusPagamento;

    private String meioPagamento;

    private String bandeira;

}

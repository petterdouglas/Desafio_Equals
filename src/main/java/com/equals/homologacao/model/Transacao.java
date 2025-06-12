package com.equals.homologacao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicioTransacao;

    private LocalDate dataEvento;

    private LocalTime horaEvento;

    @Column(name = "tipo_evento", length = 2)
    private String tipoEvento;

    @Column(name = "tipo_transacao", length = 2)
    private String tipoTransacao;

    @Column(name = "numero_serie_leitor", length = 38)
    private String numeroSerieLeitor;

    // o código de transação completa a identificação da tabela como uma chave secundária com 'id'
    @Column(name = "codigo_transacao", length = 32)
    private String codigoTransacao;

    @Column(name = "codigo_pedido", length = 20)
    private String codigoPedido;

    // valor total da transação em centavos
    private Long valorTotal;

    // valor total da transação em centavos
    private Long valorParcela;

    // tipo de pagamento baseado enum de TipoPagamento
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

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

    @Column(name = "status_pagamento", length = 2)
    private String statusPagamento;

    @Column(name = "meio_pagamento", length = 2)
    private String meioPagamento;

    @Column(name = "bandeira", length = 30)
    private String bandeira;

    @Column(name = "canal_entrada", length = 2)
    private String canalEntrada;

    @Column(name = "leitor", length = 2)
    private String leitor;

    @Column(name = "meio_captura", length = 2)
    private String meioCaptura;

    @Column(name = "numero_logico", length = 32)
    private String numeroLogico;

    @Column(name = "nsu", length = 11)
    private String nsuDaTransacao;

    @Column(name = "cartao_bin", length = 6)
    private String cartaoBin;

    @Column(name = "cartao_holder", length = 4)
    private String cartaoHolder;

    @Column(name = "codigo_autorizacao", length = 6)
    private String codigoAutorizacao;

    @Column(name = "codigo_cv", length = 32)
    private String codigoCv;

    @ManyToOne
    @JoinColumn(name = "extrato_id", nullable = false)
    @JsonBackReference
    private Extrato extrato;

}

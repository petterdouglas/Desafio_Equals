package com.equals.homologacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;
    private String nomeArquivo;
    private String tipoExtrato;
    private LocalDateTime dataProcessamento;
    private LocalDateTime periodoInicial;
    private LocalDateTime periodoFinal;
    @OneToMany(mappedBy = "extrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacoes = new ArrayList<>();
    private Integer totalRegistros;
}

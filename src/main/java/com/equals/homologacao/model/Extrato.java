package com.equals.homologacao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    @JsonBackReference
    private Empresa empresa;

    @Column(name = "tipo_extrato", length = 2)
    private String tipoExtrato;

    private LocalDate dataProcessamento;

    private LocalDate periodoInicial;

    private LocalDate periodoFinal;

    @OneToMany(mappedBy = "extrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transacao> transacoes = new ArrayList<>();

    @Column(nullable = true)
    private Integer totalRegistros;
}

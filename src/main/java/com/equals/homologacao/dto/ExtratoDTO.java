package com.equals.homologacao.dto;

import com.equals.homologacao.model.Empresa;
import com.equals.homologacao.model.Transacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoDTO {

    private Long id;

    private String tipoExtrato;

    private LocalDate dataProcessamento;

    private LocalDate periodoInicial;

    private LocalDate periodoFinal;

    private Integer totalRegistros;

}

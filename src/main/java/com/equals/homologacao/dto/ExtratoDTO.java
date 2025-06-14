package com.equals.homologacao.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


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

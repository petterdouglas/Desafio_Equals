package com.equals.homologacao.controller;

import com.equals.homologacao.service.ImportacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/importar")
@RequiredArgsConstructor
@Tag(name = "Importação", description = "Endpoint para envio do arquivo .txt de extratos")
public class ImportacaoController {

    private final ImportacaoService importacaoService;

    @Operation(
            summary = "Importa um arquivo de extratos para o sistema",
            description = "Envia o arquivo de extratos para ser lido, tratado e inserido no banco de dados"
    )
    @PostMapping
    public String importarDadosTransacoes(@RequestParam("arquivo") MultipartFile arquivo) {
        return importacaoService.importarDadosTransacoes(arquivo);
    }
}

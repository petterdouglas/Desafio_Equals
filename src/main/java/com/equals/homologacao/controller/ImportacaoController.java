package com.equals.homologacao.controller;

import com.equals.homologacao.model.Extrato;
import com.equals.homologacao.service.ExtratoService;
import com.equals.homologacao.service.ImportacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/importar")
@RequiredArgsConstructor
public class ImportacaoController {

    private final ImportacaoService importacaoService;

    @PostMapping
    public String importarDadosTransacoes(@RequestParam("arquivo") MultipartFile arquivo) {
        return importacaoService.importarDadosTransacoes(arquivo);
    }
}

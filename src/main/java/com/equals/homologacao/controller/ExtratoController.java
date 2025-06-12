package com.equals.homologacao.controller;

import com.equals.homologacao.dto.ExtratoDTO;
import com.equals.homologacao.model.Extrato;
import com.equals.homologacao.service.ExtratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresa/{codigoEmpresa}/extrato")
@RequiredArgsConstructor
public class ExtratoController {

    private final ExtratoService extratoService;

    @GetMapping("/all")
    public ResponseEntity<List<Extrato>> listarTodos(@PathVariable Long codigoEmpresa) {
        List<Extrato> extratos = extratoService.listarPorEmpresa(codigoEmpresa);
        return ResponseEntity.ok(extratos);
    }

    @GetMapping("/{extratoId}")
    public ResponseEntity<ExtratoDTO> buscarPorId(@PathVariable Long extratoId, @PathVariable Long codigoEmpresa) {
        Extrato extrato = extratoService.buscarExtratoPorId(extratoId, codigoEmpresa);
        return ResponseEntity.ok(extrato);
    }

}

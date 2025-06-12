package com.equals.homologacao.controller;

import com.equals.homologacao.dto.ExtratoDTO;
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
    public ResponseEntity<List<ExtratoDTO>> listarTodos(@PathVariable Long codigoEmpresa) {
        List<ExtratoDTO> extratos = extratoService.listarPorEmpresa(codigoEmpresa);
        if (!extratos.isEmpty()) {
            return ResponseEntity.ok(extratos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{extratoId}")
    public ResponseEntity<ExtratoDTO> buscarPorId(@PathVariable Long extratoId, @PathVariable Long codigoEmpresa) {
        ExtratoDTO extrato = extratoService.buscarExtratoPorId(extratoId, codigoEmpresa);
        if (extrato != null) {
            return ResponseEntity.ok(extrato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

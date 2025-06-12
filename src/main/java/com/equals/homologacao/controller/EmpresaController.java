package com.equals.homologacao.controller;

import com.equals.homologacao.dto.EmpresaDTO;
import com.equals.homologacao.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping("/empresa/all")
    public ResponseEntity<List<EmpresaDTO>> listarTodas() {
        List<EmpresaDTO> empresas = empresaService.listarTodas();
        if (!empresas.isEmpty()) {
            return ResponseEntity.ok(empresas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/empresa/{codigoEmpresa}")
    public ResponseEntity<EmpresaDTO> buscarPorId(@PathVariable String codigoEmpresa) {
        EmpresaDTO empresa = empresaService.buscarPorCodigo(codigoEmpresa);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

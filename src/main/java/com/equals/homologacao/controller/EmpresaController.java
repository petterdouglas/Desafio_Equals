package com.equals.homologacao.controller;

import com.equals.homologacao.model.Empresa;
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
    public ResponseEntity<List<Empresa>> listarTodas() {
        List<Empresa> empresas = empresaService.listarTodas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/empresa/{codigoEmpresa}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable String codigoEmpresa) {
        Empresa empresa = empresaService.buscarPorCodigo(codigoEmpresa);
        return ResponseEntity.ok(empresa);
    }

}

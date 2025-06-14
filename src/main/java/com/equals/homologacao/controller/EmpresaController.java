package com.equals.homologacao.controller;

import com.equals.homologacao.dto.EmpresaDTO;
import com.equals.homologacao.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Empresas", description = "Endpoints para consultar as empresas cadastradas no sistema")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Operation(
            summary = "Lista todas as empresas",
            description = "Retorna a lista de todas as empresas cadastradas no banco de dados do sistema"
    )
    @GetMapping("/empresa/all")
    public ResponseEntity<List<EmpresaDTO>> listarTodas() {
        List<EmpresaDTO> empresas = empresaService.listarTodas();
        return ResponseEntity.ok(empresas);
    }

    @Operation(
            summary = "Buscar uma certa empresa por um certo ID",
            description = "Retorna uma empresa que detenha um certo ID"
    )
    @GetMapping("/empresa/{codigoEmpresa}")
    public ResponseEntity<EmpresaDTO> buscarPorId(@PathVariable String codigoEmpresa) {
        EmpresaDTO empresa = empresaService.buscarPorCodigo(codigoEmpresa);
        return ResponseEntity.ok(empresa);
    }

}

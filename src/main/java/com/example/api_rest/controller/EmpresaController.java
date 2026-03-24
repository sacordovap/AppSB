package com.example.api_rest.controller;

import com.example.api_rest.dto.request.EmpresaRequest;
import com.example.api_rest.dto.response.ApiResponse;
import com.example.api_rest.dto.response.EmpresaResponseDto;
import com.example.api_rest.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {


    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    /*
    **CON JSON
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<EmpresaResponseDto>> save(@RequestBody EmpresaRequest request) {
        EmpresaResponseDto response = empresaService.saveEmpresa(request.getRuc());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Empresa Creada con Éxito", response));
    }
    */

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<EmpresaResponseDto>> save(@RequestParam("ruc") String ruc) {
        EmpresaResponseDto response = empresaService.saveEmpresa(ruc);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Empresa creada", response));
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse<EmpresaResponseDto>> findById(@PathVariable UUID id) {
        EmpresaResponseDto response = empresaService.findById(id);
        return ResponseEntity.ok(new ApiResponse<EmpresaResponseDto>(
                true, "Empresa no Encontrada", response
        ));
    }

    @GetMapping("/find/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<EmpresaResponseDto>>> findByNombre(@PathVariable String nombre) {
        List<EmpresaResponseDto> response = empresaService.findByNombre(nombre);
        String mensaje = response.isEmpty()
                ? "No se encontraron resultados para: " + nombre
                : "Se encontraron " + response.size() + " coincidencia(s)";

        return ResponseEntity.ok(new ApiResponse<>(true, mensaje, response));
    }
}
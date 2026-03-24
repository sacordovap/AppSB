package com.example.api_rest.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmpresaResponseDto {
    private UUID id;
    private String razonSocial;
    private String numeroDocumento;
    private String estado;
    private String condicion;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String tipo;
    private String actividadEconomica;
    private String numeroTrabajadores;
    private boolean esAgenteRetencion;
    private boolean esBuenContribuyente;
}
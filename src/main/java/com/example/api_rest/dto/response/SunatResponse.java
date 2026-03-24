package com.example.api_rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SunatResponse {
    @JsonProperty("razon_social")
    private String razonSocial;
    @JsonProperty("numero_documento")
    private String numeroDocumento;
    private String estado;
    private String condicion;
    private String direccion;
    private String ubigeo;
    @JsonProperty("via_tipo")
    private String viaTipo;
    @JsonProperty("via_nombre")
    private String viaNombre;
    @JsonProperty("zona_codigo")
    private String zonaCodigo;
    @JsonProperty("zona_tipo")
    private String zonaTipo;
    private String numero;
    private String interior;
    private String lote;
    private String dpto;
    private String manzana;
    private String kilometro;
    private String distrito;
    private String provincia;
    private String departamento;
    @JsonProperty("es_agente_retencion")
    private boolean esAgenteRetencion;
    @JsonProperty("es_buen_contribuyente")
    private boolean esBuenContribuyente;
    private String tipo;
    @JsonProperty("actividad_economica")
    private String actividadEconomica;
    @JsonProperty("numero_trabajadores")
    private String numeroTrabajadores;
    @JsonProperty("tipo_facturacion")
    private String tipoFacturacion;
    @JsonProperty("tipo_contabilidad")
    private String tipoContabilidad;
    @JsonProperty("comercio_exterior")
    private String comercioExterior;
}
package com.example.api_rest.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CategoriaResponseDto {
    private UUID id;
    private String nombre;
    private String descripcion;
    private double popularidad;

    private int totalArticulos;
    /*OPCIONAL*/
    private List<String> titulosArticulos;
}

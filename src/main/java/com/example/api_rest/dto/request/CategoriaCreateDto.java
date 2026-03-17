package com.example.api_rest.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoriaCreateDto {

    private String nombre;
    private String descripcion;


    //private List<String> nombresArticulos;
}

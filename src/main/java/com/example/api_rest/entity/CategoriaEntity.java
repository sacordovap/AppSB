package com.example.api_rest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nombre;
    private String descripcion;
    private double popularidad = 0;

    @ManyToMany
    @JoinTable(
            name = "categoria_articulo", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_categoria_fk"), // Columna que mira a Categoria
            inverseJoinColumns = @JoinColumn(name = "id_articulo_fk") // Columna que mira a Articulo
    )
    private List<ArticuloEntity> articulos;

}
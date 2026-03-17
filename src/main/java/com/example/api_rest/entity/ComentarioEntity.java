package com.example.api_rest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name="comentarios")
public class ComentarioEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID Id;
    private String contenido;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @ManyToOne()
    @JoinColumn(name = "usuario_id_fk")
    private UsuarioEntity usuario;

    @ManyToOne()
    @JoinColumn(name = "articulo_id_fk")
    private ArticuloEntity articulo;

}

package com.example.api_rest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.api_rest.dto.response.ResponseArticuloDto;
import jakarta.persistence.*; // O javax.persistence.* si usas una versión antigua
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String descripcion;
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private String email;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    private char sexo;
    private String dni;
    @Column(name = "num_comentarios")
    private int numComentarios = 0;
    private Date fechaCreación = new Date();
    private Date fechaActualizacion;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<ArticuloEntity>articulos;

}
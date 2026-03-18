package com.example.api_rest.controller;

import com.example.api_rest.dto.request.CreateArticuloDto;
import com.example.api_rest.dto.request.UsuarioCreateDto;
import com.example.api_rest.dto.response.ResponseArticuloDto;
import com.example.api_rest.dto.response.UsuarioResponseDto;
import com.example.api_rest.service.ArticuloService;
import com.example.api_rest.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/articulo")
public class ArticuloController {
    //private final UsuarioRepository usuarioRepository;

   /* public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }*/

    private final ArticuloService articuloService;

    public ArticuloController(UsuarioService usuarioService, ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @PostMapping("/save")
    public ResponseArticuloDto createArticulo(@RequestBody CreateArticuloDto createArticuloDto) {
        return articuloService.createArticulo(createArticuloDto);

    }

    @PostMapping("/find/{id}")
    public ResponseArticuloDto findById(@PathVariable UUID id) {
        return articuloService.findById(id);

    }

}
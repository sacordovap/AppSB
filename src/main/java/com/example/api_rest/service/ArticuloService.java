package com.example.api_rest.service;

import com.example.api_rest.dto.request.CreateArticuloDto;
import com.example.api_rest.dto.response.ResponseArticuloDto;
import com.example.api_rest.entity.ArticuloEntity;
import com.example.api_rest.entity.UsuarioEntity;
import com.example.api_rest.repository.ArticuloRepository;
import com.example.api_rest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;

    public ArticuloService(ArticuloRepository articuloRepository, UsuarioRepository usuarioRepository) {
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public ResponseArticuloDto createArticulo(CreateArticuloDto createArticuloDto){
        UUID idUsuario = createArticuloDto.getUsuarioId();
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) return null;

        UsuarioEntity usuario = usuarioOptional.get();

        ArticuloEntity articuloEntity = new ArticuloEntity();
        articuloEntity.setContenido(createArticuloDto.getContenido());
        articuloEntity.setTitulo(createArticuloDto.getTitulo());
        articuloEntity.setUrl("www."+createArticuloDto.getTitulo()+".com");

        articuloEntity.setUsuario(usuario);
        articuloRepository.save(articuloEntity);



        return  new ResponseArticuloDto(
                articuloEntity.getId(),
                articuloEntity.getContenido(),
                articuloEntity.getTitulo(),
                articuloEntity.getUrl());

    }


    public ResponseArticuloDto findById (UUID artId){
        Optional<ArticuloEntity> articuloOptional = articuloRepository.findById(artId);
        if (articuloOptional.isEmpty()) return null;

        ArticuloEntity articulo = articuloOptional.get();
        ResponseArticuloDto articuloDto = new ResponseArticuloDto();

        articuloDto.setId(articulo.getId());
        articuloDto.setContenido(articulo.getContenido());
        articuloDto.setTitulo(articulo.getTitulo());
        articuloDto.setUrl(articulo.getUrl());



        return articuloDto;


    }


}

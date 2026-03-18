package com.example.api_rest.service;

import com.example.api_rest.dto.request.UsuarioCreateDto;
import com.example.api_rest.dto.response.ResponseArticuloDto;
import com.example.api_rest.dto.response.UsuarioResponseDto;
import com.example.api_rest.entity.ArticuloEntity;
import com.example.api_rest.entity.UsuarioEntity;
import com.example.api_rest.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public UsuarioResponseDto saveUsuario(UsuarioCreateDto usuario){

        /*UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();

        String nombres = usuario.getNombres().toUpperCase();
        String apellidos = usuario.getApellidos().toUpperCase();


        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombres(nombres);
        usuarioEntity.setApellidos(apellidos);
        usuarioEntity.setDescripcion(usuario.getDescripcion());
        usuarioEntity.setUsername(usuario.getUsername());
        usuarioEntity.setPassword(usuario.getPassword());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioEntity.setSexo(usuario.getSexo());
        usuarioEntity.setDni(usuario.getDni());

        usuarioRepository.save(usuarioEntity);



        usuarioResponseDto.setId(usuarioEntity.getId());
        usuarioResponseDto.setDescripcion(usuarioEntity.getDescripcion());
        usuarioResponseDto.setNombres(usuarioEntity.getNombres());
        usuarioResponseDto.setApellidos(usuarioEntity.getApellidos());
        usuarioResponseDto.setUsername(usuarioEntity.getUsername());
        usuarioResponseDto.setEmail(usuarioEntity.getEmail());
        usuarioResponseDto.setFechaNacimiento(usuarioEntity.getFechaNacimiento());
        usuarioResponseDto.setNumComentarios(usuarioEntity.getNumComentarios());

        return usuarioResponseDto;*/

        usuario.setNombres(usuario.getNombres().toUpperCase());
        usuario.setApellidos(usuario.getApellidos().toUpperCase());
        // una vez las reglas se cumplen

        // de usuario create dto ---> Usuario entity
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        modelMapper.map(usuario, usuarioEntity);
        usuarioRepository.save(usuarioEntity);

        // de usuario entity a ---> Usuario response dto
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
        modelMapper.map(usuarioEntity, usuarioResponseDto);
        return usuarioResponseDto;
    }

    public UsuarioResponseDto finById (UUID usuarioId){
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(usuarioId);

        if (usuarioOptional.isEmpty()) return null;

        UsuarioEntity usuario = usuarioOptional.get();
        List<ArticuloEntity>articulos = usuario.getArticulos();
        List<ResponseArticuloDto>responseArticuloDtos =
                articulos.stream().map(articuloEntity -> {
                    ResponseArticuloDto responseArticuloDto = new ResponseArticuloDto();
                    modelMapper.map(articuloEntity,responseArticuloDto);
                    return responseArticuloDto;
                }).toList();
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
        modelMapper.map(usuario, usuarioResponseDto);
        usuarioResponseDto.setArticulos(responseArticuloDtos);
        return usuarioResponseDto;
    }
}

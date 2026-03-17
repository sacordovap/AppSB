package com.example.api_rest.service;

import com.example.api_rest.dto.request.UsuarioCreateDto;
import com.example.api_rest.dto.response.UsuarioResponseDto;
import com.example.api_rest.entity.UsuarioEntity;
import com.example.api_rest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDto saveUsuario(UsuarioCreateDto usuario){

        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();

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

        return usuarioResponseDto;



  //      return usuarioRepository.save(usuario);
    }
}

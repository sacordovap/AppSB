package com.example.api_rest.service;

import com.example.api_rest.dto.request.UsuarioCreateDto;
import com.example.api_rest.dto.response.ReniecResponse;
import com.example.api_rest.dto.response.ResponseArticuloDto;
import com.example.api_rest.dto.response.UsuarioResponseDto;
import com.example.api_rest.entity.ArticuloEntity;
import com.example.api_rest.entity.UsuarioEntity;
import com.example.api_rest.feignClient.ReniecClient;
import com.example.api_rest.repository.UsuarioRepository;
import feign.FeignException;
import feign.RetryableException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final ReniecClient reniecClient;
    @Value("${api.token}")
    private String apiToken;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, ReniecClient reniecClient) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.reniecClient = reniecClient;
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

//        usuario.setNombres(usuario.getNombres().toUpperCase());
//        usuario.setApellidos(usuario.getApellidos().toUpperCase());


        // una vez las reglas se cumplen

        String dni = usuario.getDni();
        if (dni.length()!= 8 && !dni.matches("\\\\d{8}")) return null;

        try {
            ReniecResponse response = reniecClient.getPersonaInfo(dni, apiToken);

            if (response == null) return null;

            // Lógica para generar username y apellido
            String firstName = response.getFirstName().split("\\s+")[0].toLowerCase();
            String firstLastName = response.getFirstLastName().toLowerCase();
            String username = firstName + "." + firstLastName;

            String apellido = response.getFirstLastName() + " " + response.getSecondLastName();

            // Mapeo a Entity
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            modelMapper.map(usuario, usuarioEntity);

            usuarioEntity.setUsername(username);
            usuarioEntity.setNombres(response.getFirstName());
            usuarioEntity.setApellidos(apellido.trim());

            usuarioRepository.save(usuarioEntity);

            // Mapeo a Response DTO
            UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
            modelMapper.map(usuarioEntity, usuarioResponseDto);

            return usuarioResponseDto;

        } catch (RetryableException e) {
            System.err.println("Error de tiempo de espera o conexión: " + e.getMessage());
            return null;
        } catch (FeignException e) {
            System.err.println("Error de la API: " + e.status());
            return null;
        }
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

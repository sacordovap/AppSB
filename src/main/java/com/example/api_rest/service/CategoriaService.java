package com.example.api_rest.service;

import com.example.api_rest.dto.request.CategoriaCreateDto;
import com.example.api_rest.dto.response.CategoriaResponseDto;
import com.example.api_rest.entity.CategoriaEntity;
import com.example.api_rest.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponseDto saveCategoria(CategoriaCreateDto categoria){
        CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto();

        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setDescripcion(categoria.getDescripcion());
        categoriaEntity.setNombre(categoria.getNombre());
        //categoriaEntity.setPopularidad(categoria.getPopularidad());

        categoriaRepository.save(categoriaEntity);

        categoriaResponseDto.setId(categoriaEntity.getId());
        categoriaResponseDto.setNombre(categoriaEntity.getNombre());
        categoriaResponseDto.setDescripcion(categoriaEntity.getDescripcion());
        categoriaResponseDto.setPopularidad(categoriaEntity.getPopularidad());
        return categoriaResponseDto;

    }
}

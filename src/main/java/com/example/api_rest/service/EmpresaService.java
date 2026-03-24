package com.example.api_rest.service;

import com.example.api_rest.dto.response.SunatResponse;
import com.example.api_rest.exception.ExternalServiceException;
import com.example.api_rest.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.example.api_rest.dto.response.EmpresaResponseDto;
import com.example.api_rest.entity.EmpresaEntity;
import com.example.api_rest.feignClient.SunatClient;
import com.example.api_rest.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final SunatClient sunatClient;
    private final ModelMapper modelMapper;
        @Value("${api.token}")
    private String apiToken;

    public EmpresaService(EmpresaRepository empresaRepository,
                          SunatClient sunatClient,
                          ModelMapper modelMapper) {
        this.empresaRepository = empresaRepository;
        this.sunatClient = sunatClient;
        this.modelMapper = modelMapper;
    }

    public EmpresaResponseDto saveEmpresa(String ruc) {

        if (ruc == null || ruc.length() != 11) {
            throw new RuntimeException("RUC no válido");
        }

        if (empresaRepository.existsByNumeroDocumento(ruc)) {
            throw new RuntimeException("La empresa con RUC " + ruc + " ya se encuentra registrada.");
        }

        // Consumo API
        SunatResponse sunatData = null;
        try {
            sunatData = sunatClient.getInfoSunat(ruc, apiToken);
        } catch (Exception ex) {
            throw new ExternalServiceException(ex.getMessage(), ex);
        }
        // Mapeo y persistencia
        EmpresaEntity entity = modelMapper.map(sunatData, EmpresaEntity.class);
        entity = empresaRepository.save(entity);
        return modelMapper.map(entity, EmpresaResponseDto.class);
    }

    public EmpresaResponseDto findById(UUID id) {
        /*
        Optional<EmpresaEntity> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Empresa no encontrada");
        }
        EmpresaEntity entity = empresaOptional.get();
        return modelMapper.map(entity, EmpresaResponseDto.class);
        */
        EmpresaEntity entity = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return modelMapper.map(entity, EmpresaResponseDto.class);
    }

    public List<EmpresaResponseDto> findByNombre(String nombre) {
        return empresaRepository.findByRazonSocial(nombre).stream()
                .map(e -> modelMapper.map(e, EmpresaResponseDto.class))
                .collect(Collectors.toList());
    }
}
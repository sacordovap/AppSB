package com.example.api_rest.repository;

import com.example.api_rest.entity.EmpresaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, UUID> {

    boolean existsByNumeroDocumento(String numeroDocumento);

    @Query(value = "SELECT * FROM empresas WHERE razon_social ILIKE %:nombre%", nativeQuery = true)
    List<EmpresaEntity> findByRazonSocial(@Param("nombre") String nombre);
}
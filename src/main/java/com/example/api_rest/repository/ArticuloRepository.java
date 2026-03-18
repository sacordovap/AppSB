package com.example.api_rest.repository;

import com.example.api_rest.entity.ArticuloEntity;
import com.example.api_rest.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, UUID> {
}

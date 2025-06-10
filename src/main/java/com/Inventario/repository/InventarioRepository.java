package com.Inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Inventario.models.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}
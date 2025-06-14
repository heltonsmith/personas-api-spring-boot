package com.Inventario.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Inventario")
@Data
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInventario;

    private Integer stockDisponible;

    private Integer idProducto;

    private Integer idDireccion;
}

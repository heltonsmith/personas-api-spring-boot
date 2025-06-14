package com.Inventario.dto;

import lombok.Data;

@Data
public class InventarioDTO {
    private Integer idInventario;
    private Integer stockDisponible;
    private Integer idProducto; 
    private Integer idUbicacion;
}
package com.Inventario.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PutMapping;

import com.Inventario.models.Inventario;
import com.Inventario.service.InventarioService;


@RestController
@RequestMapping("/api/inventario")
public class Inventariocontroller {

    private final InventarioService InventarioService;


    public Inventariocontroller(InventarioService InventarioService) {
        this.InventarioService = InventarioService;
    }
    
    @GetMapping
    public ResponseEntity<List<Inventario>> getAll(){
        return ResponseEntity.ok(InventarioService.findById());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Optional<Inventario> inventario = InventarioService.findById(id);
        if (inventario.isPresent()) {
            return ResponseEntity.ok(inventario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Inventario> CrearInventario(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = InventarioService.savInventario(inventario);
        return ResponseEntity.ok(nuevoInventario);
    }

    @PutMapping("/ajuste/{id}")
    public ResponseEntity<Inventario> ActualizarInventario(@PathVariable Integer id, @RequestBody Inventario inventario) {
       
        Inventario inventarioActalizado = InventarioService.ActualizarInventario(id, inventario);
        return ResponseEntity.ok(inventarioActalizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventario(@PathVariable Integer id) {
        if (InventarioService.findById(id).isPresent()) {
            InventarioService.deleteInventario(id);
            return ResponseEntity.ok("Inventario eliminado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    


}
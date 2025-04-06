package com.personas.Controller;

import com.personas.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/personas")
public class PersonaController {

    private List<Persona> personas = new ArrayList<>();

    public PersonaController() {
        personas.add(new Persona(1, "Juan", "Pérez"));
        personas.add(new Persona(2, "María", "González"));
    }

    // GET /personas - Obtener todas las personas
    @GetMapping("/")
    public ResponseEntity<List<Persona>> getAllPersonas() {
        return ResponseEntity.ok(personas);
    }

    // GET /personas/{id} - Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable int id) {
        Persona persona = personas.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);

        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Mensaje("Persona no encontrada"));
        }
    }

    // POST /personas - Agregar una nueva persona
    @PostMapping
    public ResponseEntity<Mensaje> addPersona(@RequestBody Persona persona) {
        personas.add(persona);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Mensaje("Persona agregada"));
    }

    // PUT /personas/{id} - Actualizar una persona existente
    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> updatePersona(@PathVariable int id, @RequestBody Persona personaActualizada) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId() == id) {
                personas.set(i, personaActualizada);
                return ResponseEntity.ok(new Mensaje("Persona actualizada"));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("Persona no encontrada"));
    }

    // DELETE /personas/{id} - Eliminar una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> deletePersona(@PathVariable int id) {
        Persona persona = personas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (persona != null) {
            personas.remove(persona);
            return ResponseEntity.ok(new Mensaje("Persona eliminada"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("Persona no encontrada"));
    }

    // Clase interna para respuesta de mensajes en JSON
    static class Mensaje {
        private String mensaje;

        public Mensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}

package com.medtriage.medtriage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medtriage.medtriage.dto.CitaMedicaDTO;
import com.medtriage.medtriage.dto.CrearCitaDTO;
import com.medtriage.medtriage.service.CitaMedicaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/api/v1/citas")
@CrossOrigin (origins = "http://localhost:4200")
public class CitaMedicaController {
 
    private final CitaMedicaService citaMedicaService;

    public CitaMedicaController(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    @GetMapping
    public List<CitaMedicaDTO> obtenerTodas() {
        return citaMedicaService.obtenerTodas();
    }

    @GetMapping("/prioridad/{prioridad}")
    public List<CitaMedicaDTO> obtenerPorPrioridad(
            @PathVariable String prioridad) {
        return citaMedicaService.obtenerPorPrioridad(prioridad);
    }

    @PostMapping
    public CitaMedicaDTO crearCita(@RequestBody CrearCitaDTO dto) {
        return citaMedicaService.crearCita(dto);
    }

    @PatchMapping("/{id}/estado")
    public CitaMedicaDTO actualizarEstado(
            @PathVariable Long id,
            @RequestBody String nuevoEstado) {
        return citaMedicaService.actualizarEstado(id, nuevoEstado);
    }
    
    
}

package com.proyectoT.sena.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public Map<String, String> probarConexion() {
        return Map.of("mensaje", "¡CONEXIÓN EXITOSA: El Backend y el Frontend están hablando!");
    }
}

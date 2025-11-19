package com.proyectoT.sena.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ManejadorGlobal {

    public static Map<String, Object> error(String mensaje, int status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("message", mensaje);
        return body;
    }
}


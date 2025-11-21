package com.proyectoT.sena.dtos;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;
    private String name;
    private Integer price;

    private MultipartFile productImage; // Para recibir la imagen desde el front
}

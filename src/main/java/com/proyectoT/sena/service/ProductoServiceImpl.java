package com.proyectoT.sena.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoT.sena.dtos.ProductoDTO;
import com.proyectoT.sena.mapper.ProductoMapper;
import com.proyectoT.sena.models.Producto;
import com.proyectoT.sena.repositoryes.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    private final String UPLOAD_DIR = "uploads/productos/";

    @Override
    public ProductoDTO save(ProductoDTO dto, MultipartFile image) {
        try {
            Producto product = productoMapper.toEntity(dto);

            if (image != null && !image.isEmpty()) {
                String fileName = saveImage(image);
                product.setImageUrl(fileName);
            }

            return productoMapper.toDto(productoRepository.save(product));

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar producto");
        }
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO dto, MultipartFile image) {

        Producto product = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        if (image != null && !image.isEmpty()) {
            String fileName = saveImage(image);
            product.setImageUrl(fileName);
        }

        return productoMapper.toDto(productoRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO findById(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<ProductoDTO> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .toList();
    }

    private String saveImage(MultipartFile image) {
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            Files.write(path, image.getBytes());

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la imagen");
        }
    }
}

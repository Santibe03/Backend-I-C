package com.proyectoT.sena.service;

import java.io.IOException;
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

    @Override
    public ProductoDTO save(ProductoDTO dto) {
        try {
            MultipartFile file = dto.getProductImage();
            byte[] bytes = file.getBytes();
            String contentType = file.getContentType();

            Producto producto = productoMapper.toEntity(dto, bytes, contentType);
            Producto saved = productoRepository.save(producto);

            return productoMapper.toDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error procesando la imagen");
        }
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        try {
            MultipartFile file = dto.getProductImage();
            byte[] bytes = file.getBytes();
            String contentType = file.getContentType();

            producto.setName(dto.getName());
            producto.setPrice(dto.getPrice());
            producto.setProductImage(bytes);
            producto.setProductImageContentType(contentType);

            productoRepository.save(producto);

            return productoMapper.toDto(producto);

        } catch (IOException e) {
            throw new RuntimeException("Error procesando la imagen");
        }
    }

    @Override
    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return productoMapper.toDto(producto);
    }

    @Override
    public List<ProductoDTO> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .toList();
    }
}

package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.OrdenProductoDTO;
import com.proyectoT.sena.mapper.OrdenProductoMapper;
import com.proyectoT.sena.models.OrdenProducto;
import com.proyectoT.sena.repositoryes.OrdenProductoRepository;
import com.proyectoT.sena.repositoryes.ProductoRepository;
import com.proyectoT.sena.repositoryes.OrdenRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenProductoServiceImpl implements OrdenProductoService {

    private final OrdenProductoRepository ordenProductoRepository;
    private final ProductoRepository productoRepository;
    private final OrdenRepository ordenRepository;
    private final OrdenProductoMapper mapper;

    @Override
    public OrdenProductoDTO save(OrdenProductoDTO dto) {

        OrdenProducto entity = mapper.toEntity(dto);

        // Relaciones
        entity.setProduct(productoRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));

        entity.setOrder(ordenRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada")));

        OrdenProducto saved = ordenProductoRepository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenProductoDTO> findOne(Long id) {
        return ordenProductoRepository.findById(id).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenProductoDTO> findAll() {
        return ordenProductoRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        ordenProductoRepository.deleteById(id);
    }
}


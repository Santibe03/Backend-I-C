package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.ProductoFacturaDTO;
import com.proyectoT.sena.mapper.ProductoFacturaMapper;
import com.proyectoT.sena.models.ProductoFactura;
import com.proyectoT.sena.repositoryes.ProductoFacturaRepository;
import com.proyectoT.sena.repositoryes.ProductoRepository;
import com.proyectoT.sena.repositoryes.FacturaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoFacturaServiceImpl implements ProductoFacturaService {

    private final ProductoFacturaRepository productoFacturaRepository;
    private final ProductoRepository productoRepository;
    private final FacturaRepository facturaRepository;
    private final ProductoFacturaMapper mapper;

    @Override
    public ProductoFacturaDTO save(ProductoFacturaDTO dto) {

        ProductoFactura entity = mapper.toEntity(dto);

        // PRODUCTO
        entity.setProduct(productoRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));

        // FACTURA
        entity.setBill(facturaRepository.findById(dto.getBillId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada")));

        ProductoFactura saved = productoFacturaRepository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoFacturaDTO> findOne(Long id) {
        return productoFacturaRepository.findById(id).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoFacturaDTO> findAll() {
        return productoFacturaRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        productoFacturaRepository.deleteById(id);
    }
}


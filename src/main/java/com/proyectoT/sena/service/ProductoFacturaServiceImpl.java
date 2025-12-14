package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(ProductoFacturaServiceImpl.class);

    private final ProductoFacturaRepository productoFacturaRepository;
    private final ProductoRepository productoRepository;
    private final FacturaRepository facturaRepository;
    private final ProductoFacturaMapper mapper;
    private final InsumoService insumoService;

    @Override
    public ProductoFacturaDTO save(ProductoFacturaDTO dto) {
        log.info("=== GUARDANDO ProductoFactura ===");
        log.info("ProductId: {}, BillId: {}, Amount: {}", dto.getProductId(), dto.getBillId(), dto.getAmount());

        ProductoFactura entity = mapper.toEntity(dto);

        // PRODUCTO - Use special query to load with recipe (productInputs)
        var product = productoRepository.findByIdWithRecipe(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        entity.setProduct(product);

        log.info("Producto encontrado: {} - Tiene {} ingredientes (receta)",
                product.getName(),
                product.getProductInputs() != null ? product.getProductInputs().size() : 0);

        // FACTURA
        entity.setBill(facturaRepository.findById(dto.getBillId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada")));

        // DESCONTAR INSUMOS (RECETA)
        // Por cada "InsumosProducto" asociado al producto, descontamos del inventario
        if (product.getProductInputs() != null && !product.getProductInputs().isEmpty()) {
            int productQuantity = dto.getAmount() != null ? dto.getAmount() : 1;
            log.info("Cantidad de productos vendidos: {}", productQuantity);

            for (var inputRelation : product.getProductInputs()) {
                Double recipeAmount = inputRelation.getAmount();
                Double totalToDeduct = recipeAmount * productQuantity;

                log.info("Descontando insumo: {} - Receta: {} x Cantidad: {} = {} a descontar",
                        inputRelation.getInput().getInputName(),
                        recipeAmount,
                        productQuantity,
                        totalToDeduct);

                insumoService.deductStock(inputRelation.getInput().getId(), totalToDeduct);
            }
        } else {
            log.warn("El producto {} NO tiene ingredientes asociados (receta vacía)", product.getName());
        }

        ProductoFactura saved = productoFacturaRepository.save(entity);
        log.info("ProductoFactura guardado con ID: {}", saved.getId());

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

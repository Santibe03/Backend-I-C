package com.proyectoT.sena.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoT.sena.dtos.ProductoDTO;
import com.proyectoT.sena.mapper.ProductoMapper;
import com.proyectoT.sena.models.Producto;
import com.proyectoT.sena.models.InsumosProducto;
import com.proyectoT.sena.repositoryes.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

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
        product.setDescription(dto.getDescription());

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
    @Transactional(readOnly = true)
    public ProductoDTO findById(Long id) {
        return productoRepository.findByIdWithRecipe(id)
                .map(this::toDtoWithStock)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findAll() {
        log.info("=== CARGANDO TODOS LOS PRODUCTOS ===");

        // Get all products with their recipes in one query
        List<Producto> products = productoRepository.findAll();

        return products.stream()
                .map(p -> {
                    try {
                        // Load recipe for each product
                        var productWithRecipe = productoRepository.findByIdWithRecipe(p.getId());
                        if (productWithRecipe.isPresent()) {
                            return toDtoWithStock(productWithRecipe.get());
                        }
                        return productoMapper.toDto(p);
                    } catch (Exception e) {
                        log.error("Error calculando stock para producto {}: {}", p.getName(), e.getMessage());
                        ProductoDTO dto = productoMapper.toDto(p);
                        dto.setCalculatedStock(0);
                        return dto;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Converts a Producto entity to DTO and calculates available stock
     * based on insumo availability and recipe requirements.
     */
    private ProductoDTO toDtoWithStock(Producto product) {
        ProductoDTO dto = productoMapper.toDto(product);
        dto.setCalculatedStock(calculateAvailableStock(product));
        return dto;
    }

    /**
     * Calculates how many units of this product can be made
     * based on available insumo stock and recipe requirements.
     */
    private Integer calculateAvailableStock(Producto product) {
        try {
            if (product.getProductInputs() == null || product.getProductInputs().isEmpty()) {
                log.info("Producto {} no tiene receta, stock = 0", product.getName());
                return 0;
            }

            Integer minStock = null;

            for (InsumosProducto recipe : product.getProductInputs()) {
                Double requiredAmount = recipe.getAmount();
                if (requiredAmount == null || requiredAmount <= 0)
                    continue;

                if (recipe.getInput() == null) {
                    log.warn("Receta de {} tiene input nulo", product.getName());
                    continue;
                }

                Double insumoStock = recipe.getInput().getAmount();
                if (insumoStock == null)
                    insumoStock = 0.0;

                int possibleUnits = (int) Math.floor(insumoStock / requiredAmount);
                log.info("  - Insumo {}: stock={}, requiere={}, posibles={}",
                        recipe.getInput().getInputName(), insumoStock, requiredAmount, possibleUnits);

                if (minStock == null || possibleUnits < minStock) {
                    minStock = possibleUnits;
                }
            }

            log.info("Producto {} stock calculado = {}", product.getName(), minStock);
            return minStock != null ? minStock : 0;

        } catch (Exception e) {
            log.error("Error calculando stock para {}: {}", product.getName(), e.getMessage());
            return 0;
        }
    }

    private String saveImage(MultipartFile image) {
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists())
                uploadDir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            Files.write(path, image.getBytes());

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la imagen");
        }
    }
}

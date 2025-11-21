package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.InsumosProductoDTO;
import com.proyectoT.sena.mapper.InsumosProductoMapper;
import com.proyectoT.sena.models.InsumosProducto;
import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.models.Producto;
import com.proyectoT.sena.models.Medidas;

@Component
public class InsumosProductoMapperImpl implements InsumosProductoMapper {

    @Override
    public InsumosProductoDTO toDto(InsumosProducto entity) {
        if (entity == null) return null;

        InsumosProductoDTO dto = new InsumosProductoDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());

        // INSUMO
        if (entity.getInput() != null) {
            dto.setInputId(entity.getInput().getId());
            dto.setInputNombre(entity.getInput().getInputName()); // <-- nombre correcto
        }

        // PRODUCTO
        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName()); // <-- name
        }

        // MEDIDAS
        if (entity.getMeasure() != null) {
            dto.setMeasureId(entity.getMeasure().getId());
            dto.setMeasureNombre(entity.getMeasure().getMedNam()); // <-- medNam
        }

        return dto;
    }

    @Override
    public InsumosProducto toEntity(InsumosProductoDTO dto) {
        if (dto == null) return null;

        InsumosProducto entity = new InsumosProducto();
        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());

        // INSUMO
        if (dto.getInputId() != null) {
            Insumo insumo = new Insumo();
            insumo.setId(dto.getInputId());
            entity.setInput(insumo);
        }

        // PRODUCTO
        if (dto.getProductId() != null) {
            Producto pro = new Producto();
            pro.setId(dto.getProductId());
            entity.setProduct(pro);
        }

        // MEDIDA
        if (dto.getMeasureId() != null) {
            Medidas med = new Medidas();
            med.setId(dto.getMeasureId());
            entity.setMeasure(med);
        }

        return entity;
    }
}

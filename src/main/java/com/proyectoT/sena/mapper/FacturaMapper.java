package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.FacturaDTO;
import com.proyectoT.sena.models.Factura;

public interface FacturaMapper {

    FacturaDTO toDTO(Factura entity);

    Factura toEntity(FacturaDTO dto);

    void updateEntityFromDTO(FacturaDTO dto, Factura entity);
}

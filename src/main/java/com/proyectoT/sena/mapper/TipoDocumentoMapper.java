package com.proyectoT.sena.mapper;

import com.proyectoT.sena.models.TipoDocumento;
import com.proyectoT.sena.dtos.TipoDocumentoDTO;

public interface TipoDocumentoMapper {

    TipoDocumentoDTO toDto(TipoDocumento entity);

    TipoDocumento toEntity(TipoDocumentoDTO dto);
}

package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.models.TipoDocumento;
import com.proyectoT.sena.dtos.TipoDocumentoDTO;

@Component
public class TipoDocumentoMapperImpl implements TipoDocumentoMapper {

    @Override
    public TipoDocumentoDTO toDto(TipoDocumento entity) {
        if (entity == null) return null;

        TipoDocumentoDTO dto = new TipoDocumentoDTO();
        dto.setId(entity.getId());
        dto.setInitials(entity.getInitials());
        dto.setDocumentName(entity.getDocumentName());
        dto.setStateDocumentType(entity.getStateDocumentType());
        return dto;
    }

    @Override
    public TipoDocumento toEntity(TipoDocumentoDTO dto) {
        if (dto == null) return null;

        TipoDocumento entity = new TipoDocumento();
        entity.setId(dto.getId());
        entity.setInitials(dto.getInitials());
        entity.setDocumentName(dto.getDocumentName());
        entity.setStateDocumentType(dto.getStateDocumentType());
        return entity;
    }
}

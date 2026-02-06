package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.SolicitudAsociacionDTO;
import com.proyectoT.sena.models.SolicitudAsociacion;

@Component
public class SolicitudAsociacionMapper {

    public SolicitudAsociacionDTO toDTO(SolicitudAsociacion entity) {
        if (entity == null) {
            return null;
        }

        SolicitudAsociacionDTO dto = new SolicitudAsociacionDTO();
        dto.setId(entity.getId());
        dto.setEsParaAdministrador(entity.getEsParaAdministrador());
        dto.setEstado(entity.getEstado());
        dto.setFechaSolicitud(entity.getFechaSolicitud());
        dto.setFechaRespuesta(entity.getFechaRespuesta());

        if (entity.getUsuarioSolicitante() != null) {
            dto.setUsuarioSolicitanteId(entity.getUsuarioSolicitante().getId());
            dto.setNombreSolicitante(
                    entity.getUsuarioSolicitante().getFirstName() + " " +
                            entity.getUsuarioSolicitante().getLastName());
        }

        if (entity.getRestaurante() != null) {
            dto.setRestauranteId(entity.getRestaurante().getId());
            dto.setNombreRestaurante(entity.getRestaurante().getNombre());
        }

        if (entity.getUsuarioAprobador() != null) {
            dto.setUsuarioAprobadorId(entity.getUsuarioAprobador().getId());
        }

        return dto;
    }

    public SolicitudAsociacion toEntity(SolicitudAsociacionDTO dto) {
        if (dto == null) {
            return null;
        }

        SolicitudAsociacion entity = new SolicitudAsociacion();
        entity.setId(dto.getId());
        entity.setEsParaAdministrador(dto.getEsParaAdministrador());
        entity.setEstado(dto.getEstado());
        entity.setFechaSolicitud(dto.getFechaSolicitud());
        entity.setFechaRespuesta(dto.getFechaRespuesta());

        // Las relaciones se setean en el servicio

        return entity;
    }
}

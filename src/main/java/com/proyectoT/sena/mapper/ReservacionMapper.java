package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.models.Reservacion;

public interface ReservacionMapper {

    ReservacionDTO toDto(Reservacion entity);

    Reservacion toEntity(ReservacionDTO dto);
}


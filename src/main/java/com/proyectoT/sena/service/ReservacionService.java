package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.ReservacionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservacionService {
    ReservacionDTO save(ReservacionDTO reservacionDTO);
    ReservacionDTO update(ReservacionDTO reservacionDTO);
    Optional<ReservacionDTO> partialUpdate(ReservacionDTO reservacionDTO);
    Page<ReservacionDTO> findAll(Pageable pageable);
    Page<ReservacionDTO> findAllWithEagerRelationships(Pageable pageable);
    Optional<ReservacionDTO> findOne(Long id);
    void delete(Long id);

}

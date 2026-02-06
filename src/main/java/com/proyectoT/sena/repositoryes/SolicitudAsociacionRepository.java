package com.proyectoT.sena.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoT.sena.models.SolicitudAsociacion;

@Repository
public interface SolicitudAsociacionRepository extends JpaRepository<SolicitudAsociacion, Long> {

    List<SolicitudAsociacion> findByEstado(String estado);

    List<SolicitudAsociacion> findByRestauranteIdAndEstado(Long restauranteId, String estado);

    List<SolicitudAsociacion> findByUsuarioSolicitanteId(Long userId);

    List<SolicitudAsociacion> findByRestauranteId(Long restauranteId);
}

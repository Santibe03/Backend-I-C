package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.TipoDocumentoDTO;
import java.util.List;
import java.util.Optional;

public interface TipoDocumentoService {

    TipoDocumentoDTO save(TipoDocumentoDTO dto);

    TipoDocumentoDTO update(TipoDocumentoDTO dto);

    Optional<TipoDocumentoDTO> findOne(Long id);

    List<TipoDocumentoDTO> findAll();

    void delete(Long id);
}

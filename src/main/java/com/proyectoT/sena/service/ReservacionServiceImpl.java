package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.mapper.ReservacionMapper;
import com.proyectoT.sena.models.Reservacion;
import com.proyectoT.sena.repositoryes.ReservacionRepository;

@Service
@Transactional
public class ReservacionServiceImpl implements ReservacionService {

    private final ReservacionRepository reservacionRepository;
    private final ReservacionMapper reservacionMapper;

    public ReservacionServiceImpl(ReservacionRepository reservacionRepository,
                                  ReservacionMapper reservacionMapper) {
        this.reservacionRepository = reservacionRepository;
        this.reservacionMapper = reservacionMapper;
    }

    @Override
    public ReservacionDTO save(ReservacionDTO dto) {
        Reservacion entity = reservacionMapper.toEntity(dto);
        entity = reservacionRepository.save(entity);
        return reservacionMapper.toDto(entity);
    }

    @Override
    public ReservacionDTO update(ReservacionDTO dto) {
        Reservacion entity = reservacionMapper.toEntity(dto);
        entity = reservacionRepository.save(entity); // mismo save() funciona para update
        return reservacionMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservacionDTO> findOne(Long id) {
        return reservacionRepository.findById(id)
                .map(reservacionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> findAll() {
        return reservacionRepository.findAll()
                .stream()
                .map(reservacionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        reservacionRepository.deleteById(id);
    }
}


package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.mapper.ReservacionMapper;
import com.proyectoT.sena.models.Reservacion;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.repositoryes.ReservacionRepository;
import com.proyectoT.sena.repositoryes.PersonRepository;
import com.proyectoT.sena.repositoryes.BarraMesaRepository;

@Service
@Transactional
public class ReservacionServiceImpl implements ReservacionService {

    private final ReservacionRepository reservacionRepository;
    private final ReservacionMapper reservacionMapper;
    private final PersonRepository personRepository;
    private final BarraMesaRepository barraMesaRepository;

    public ReservacionServiceImpl(ReservacionRepository reservacionRepository,
            ReservacionMapper reservacionMapper,
            PersonRepository personRepository,
            BarraMesaRepository barraMesaRepository) {
        this.reservacionRepository = reservacionRepository;
        this.reservacionMapper = reservacionMapper;
        this.personRepository = personRepository;
        this.barraMesaRepository = barraMesaRepository;
    }

    @Override
    public ReservacionDTO save(ReservacionDTO dto) {
        Reservacion entity = reservacionMapper.toEntity(dto);

        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(dto.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Person not found"));
            entity.setPerson(person);
        }

        if (dto.getBarTableId() != null) {
            BarraMesa barTable = barraMesaRepository.findById(dto.getBarTableId())
                    .orElseThrow(() -> new RuntimeException("BarTable not found"));
            entity.setBarTable(barTable);
        }

        entity = reservacionRepository.save(entity);
        return reservacionMapper.toDto(entity);
    }

    @Override
    public ReservacionDTO update(ReservacionDTO dto) {
        // 1. Fetch existing entity
        Reservacion entity = reservacionRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // 2. Update fields if they are present in DTO
        if (dto.getAplicationDate() != null)
            entity.setAplicationDate(dto.getAplicationDate());
        if (dto.getReservationDate() != null)
            entity.setReservationDate(dto.getReservationDate());
        if (dto.getAttendat() != null)
            entity.setAttendat(dto.getAttendat());
        if (dto.getCondition() != null)
            entity.setCondition(dto.getCondition());

        // 3. Update relationships if present
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(dto.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Person not found"));
            entity.setPerson(person);
        }

        if (dto.getBarTableId() != null) {
            BarraMesa barTable = barraMesaRepository.findById(dto.getBarTableId())
                    .orElseThrow(() -> new RuntimeException("BarTable not found"));
            entity.setBarTable(barTable);
        }

        // 4. Save
        entity = reservacionRepository.save(entity);
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
    @Transactional(readOnly = true)
    public List<ReservacionDTO> findByPersonId(Long personId) {
        return reservacionRepository.findByPerson_Id(personId) // Using the correct method name from repo
                .stream()
                .map(reservacionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        reservacionRepository.deleteById(id);
    }
}

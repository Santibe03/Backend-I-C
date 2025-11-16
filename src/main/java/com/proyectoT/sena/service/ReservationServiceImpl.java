package co.edu.sena.shtproyect.service;

import co.edu.sena.shtproyect.domain.Reservation;
import co.edu.sena.shtproyect.repository.ReservationRepository;
import co.edu.sena.shtproyect.service.ReservationService;
import co.edu.sena.shtproyect.service.dto.ReservacionDTO;
import co.edu.sena.shtproyect.service.mapper.ReservacionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para gestionar {@link co.edu.sena.shtproyect.domain.Reservation}.
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOG = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservacionRepositorio;

    private final ReservacionMapper reservacionMapper;

    public ReservationServiceImpl(ReservationRepository reservacionRepositorio, ReservacionMapper reservacionMapper) {
        this.reservacionRepositorio = reservacionRepositorio;
        this.reservacionMapper = reservacionMapper;
    }

    @Override
    public ReservacionDTO save(ReservacionDTO reservacionDTO) {
        LOG.debug("Solicitud para guardar Reservación: {}", reservacionDTO);
        Reservation reservacion = reservacionMapper.toEntity(reservacionDTO);
        reservacion = reservacionRepositorio.save(reservacion);
        return reservacionMapper.toDto(reservacion);
    }

    @Override
    public ReservacionDTO update(ReservacionDTO reservacionDTO) {
        LOG.debug("Solicitud para actualizar Reservación: {}", reservacionDTO);
        Reservacion reservacion = reservacionMapper.toEntity(reservacionDTO);
        reservacion = reservacionRepositorio.save(reservacion);
        return reservacionMapper.toDto(reservacion);
    }

    @Override
    public Optional<ReservacionDTO> partialUpdate(ReservacionDTO reservacionDTO) {
        LOG.debug("Solicitud para actualizar parcialmente Reservación: {}", reservacionDTO);

        return reservacionRepositorio
            .findById(reservacionDTO.getId())
            .map(existingReservacion -> {
                reservacionMapper.partialUpdate(existingReservacion, reservacionDTO);
                return existingReservacion;
            })
            .map(reservacionRepositorio::save)
            .map(reservacionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReservacionDTO> findAll(Pageable pageable) {
        LOG.debug("Solicitud para obtener todas las Reservaciones");
        return reservacionRepositorio.findAll(pageable).map(reservacionMapper::toDto);
    }

    /**
     * Obtener todas las reservaciones con carga eager de relaciones many-to-many.
     */
    public Page<ReservacionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return reservacionRepositorio.findAllWithEagerRelationships(pageable).map(reservacionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservacionDTO> findOne(Long id) {
        LOG.debug("Solicitud para obtener Reservación: {}", id);
        return reservacionRepositorio.findOneWithEagerRelationships(id).map(reservacionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Solicitud para eliminar Reservación: {}", id);
        reservacionRepositorio.deleteById(id);
    }
}
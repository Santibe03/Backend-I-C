package com.proyectoT.sena.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.SolicitudAsociacionDTO;
import com.proyectoT.sena.mapper.SolicitudAsociacionMapper;
import com.proyectoT.sena.models.Restaurante;
import com.proyectoT.sena.models.SolicitudAsociacion;
import com.proyectoT.sena.models.User;
import com.proyectoT.sena.repositoryes.RestauranteRepository;
import com.proyectoT.sena.repositoryes.SolicitudAsociacionRepository;
import com.proyectoT.sena.repositoryes.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class SolicitudAsociacionServiceImpl implements SolicitudAsociacionService {

        private final SolicitudAsociacionRepository solicitudRepository;
        private final UserRepository userRepository;
        private final RestauranteRepository restauranteRepository;
        private final UserRestauranteService userRestauranteService;
        private final SolicitudAsociacionMapper solicitudMapper;

        // Constructor con @Lazy para romper dependencia circular
        public SolicitudAsociacionServiceImpl(
                        SolicitudAsociacionRepository solicitudRepository,
                        UserRepository userRepository,
                        RestauranteRepository restauranteRepository,
                        @Lazy UserRestauranteService userRestauranteService,
                        SolicitudAsociacionMapper solicitudMapper) {
                this.solicitudRepository = solicitudRepository;
                this.userRepository = userRepository;
                this.restauranteRepository = restauranteRepository;
                this.userRestauranteService = userRestauranteService;
                this.solicitudMapper = solicitudMapper;
        }

        @Override
        public SolicitudAsociacionDTO enviarSolicitud(Long usuarioId, Long restauranteId, Boolean esParaAdministrador) {
                User usuario = userRepository.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                Restaurante restaurante = restauranteRepository.findById(restauranteId)
                                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

                // Verificar si ya existe una solicitud PENDIENTE al mismo restaurante
                List<SolicitudAsociacion> existentes = solicitudRepository
                                .findByUsuarioSolicitanteIdAndRestauranteIdAndEstado(usuarioId, restauranteId,
                                                "PENDIENTE");
                if (!existentes.isEmpty()) {
                        throw new RuntimeException("Ya tienes una solicitud pendiente en este restaurante");
                }

                SolicitudAsociacion solicitud = new SolicitudAsociacion();
                solicitud.setUsuarioSolicitante(usuario);
                solicitud.setRestaurante(restaurante);
                solicitud.setEsParaAdministrador(esParaAdministrador != null ? esParaAdministrador : false);
                solicitud.setEstado("PENDIENTE");
                solicitud.setFechaSolicitud(Instant.now());

                SolicitudAsociacion saved = solicitudRepository.save(solicitud);
                return solicitudMapper.toDTO(saved);
        }

        @Override
        public SolicitudAsociacionDTO aprobar(Long solicitudId, Long usuarioAprobadorId) {
                SolicitudAsociacion solicitud = solicitudRepository.findById(solicitudId)
                                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

                if (!"PENDIENTE".equals(solicitud.getEstado())) {
                        throw new RuntimeException("La solicitud ya fue procesada");
                }

                User aprobador = userRepository.findById(usuarioAprobadorId)
                                .orElseThrow(() -> new RuntimeException("Usuario aprobador no encontrado"));

                // Crear la asociación UserRestaurante
                userRestauranteService.asociarUsuario(
                                solicitud.getUsuarioSolicitante().getId(),
                                solicitud.getRestaurante().getId(),
                                solicitud.getEsParaAdministrador());

                // Actualizar la solicitud
                solicitud.setEstado("APROBADA");
                solicitud.setFechaRespuesta(Instant.now());
                solicitud.setUsuarioAprobador(aprobador);

                SolicitudAsociacion updated = solicitudRepository.save(solicitud);
                return solicitudMapper.toDTO(updated);
        }

        @Override
        public SolicitudAsociacionDTO rechazar(Long solicitudId, Long usuarioAprobadorId, String motivoRechazo) {
                SolicitudAsociacion solicitud = solicitudRepository.findById(solicitudId)
                                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

                if (!"PENDIENTE".equals(solicitud.getEstado())) {
                        throw new RuntimeException("La solicitud ya fue procesada");
                }

                User aprobador = userRepository.findById(usuarioAprobadorId)
                                .orElseThrow(() -> new RuntimeException("Usuario aprobador no encontrado"));

                solicitud.setEstado("RECHAZADA");
                solicitud.setFechaRespuesta(Instant.now());
                solicitud.setUsuarioAprobador(aprobador);
                solicitud.setMotivoRechazo(motivoRechazo);
                solicitud.setLeida(false);

                SolicitudAsociacion updated = solicitudRepository.save(solicitud);
                return solicitudMapper.toDTO(updated);
        }

        @Override
        public List<SolicitudAsociacionDTO> listarPendientesPorRestaurante(Long restauranteId) {
                return solicitudRepository.findByRestauranteIdAndEstado(restauranteId, "PENDIENTE")
                                .stream()
                                .map(solicitudMapper::toDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<SolicitudAsociacionDTO> listarPorUsuario(Long usuarioId) {
                return solicitudRepository.findByUsuarioSolicitanteId(usuarioId)
                                .stream()
                                .map(solicitudMapper::toDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<SolicitudAsociacionDTO> listarTodasPorRestaurante(Long restauranteId) {
                return solicitudRepository.findByRestauranteId(restauranteId)
                                .stream()
                                .map(solicitudMapper::toDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<SolicitudAsociacionDTO> listarRechazadasNoLeidas(Long usuarioId) {
                return solicitudRepository.findByUsuarioSolicitanteIdAndEstadoAndLeidaFalse(usuarioId, "RECHAZADA")
                                .stream()
                                .map(solicitudMapper::toDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public void marcarComoLeidas(Long usuarioId) {
                List<SolicitudAsociacion> noLeidas = solicitudRepository
                                .findByUsuarioSolicitanteIdAndEstadoAndLeidaFalse(usuarioId, "RECHAZADA");
                for (SolicitudAsociacion s : noLeidas) {
                        s.setLeida(true);
                }
                solicitudRepository.saveAll(noLeidas);
        }
}

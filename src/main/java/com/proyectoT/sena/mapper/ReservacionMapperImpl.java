package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.dtos.CondicionDTO;
import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.models.Reservacion;

@Component
public class ReservacionMapperImpl implements ReservacionMapper {

    @Override
    public ReservacionDTO toDto(Reservacion s) {
        if (s == null) {
            return null;
        }

        ReservacionDTO dto = new ReservacionDTO();

        // Campos básicos (solo los genéricos/seguros: id)
        dto.setId(s.getId());

        // Mapear relaciones usando los métodos auxiliares
        if (s.getPerson() != null) {
            dto.setPerson(toDtoPersonId(s.getPerson()));
        }

        if (s.getCondicion() != null) {
            dto.setCondicion(toDtoCondicionId(s.getCondicion()));
        }

        if (s.getBarraMesa() != null) {
            dto.setBarraMesa(toDtoBarraMesaId(s.getBarraMesa()));
        }

        // Nota: si querés mapear más campos concretos (fecha, hora, estado, cantidad,
        // observaciones...) pégame la cabecera de Reservacion (getters) y del DTO
        // y los agrego aquí igual que en el ejemplo de PersonMapperImpl.

        return dto;
    }

    @Override
    public PersonDTO toDtoPersonId(Person person) {
        if (person == null) {
            return null;
        }

        PersonDTO dto = new PersonDTO();

        dto.setId(person.getId());

        // Si tu Person tiene documentNumber (como en el ejemplo anterior), lo copiamos:
        try {
            dto.setDocumentNumber(person.getDocumentNumber());
        } catch (NoSuchMethodError | NoClassDefFoundError e) {
            // Si el getter no existe, ignoramos (esto evita fallos si las clases difieren).
            // Ideal: confirmar que Person tiene getDocumentNumber(), si no, lo removemos.
        }

        return dto;
    }

    @Override
    public CondicionDTO toDtoCondicionId(Condicion condition) {
        if (condition == null) {
            return null;
        }

        CondicionDTO dto = new CondicionDTO();
        dto.setId(condition.getId());

        // Si querés incluir nombre de condición u otro campo:
        // dto.setNombre(condition.getNombre());

        return dto;
    }

    @Override
    public BarraMesaDTO toDtoBarraMesaId(BarraMesa barTable) {
        if (barTable == null) {
            return null;
        }

        BarraMesaDTO dto = new BarraMesaDTO();
        dto.setId(barTable.getId());

        // Si la BarraMesa tiene número o código, se puede mapear aquí:
        // dto.setCodigo(barTable.getCodigo());

        return dto;
    }
}
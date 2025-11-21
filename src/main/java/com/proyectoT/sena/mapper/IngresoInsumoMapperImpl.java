package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.IngresoInsumoDTO;
import com.proyectoT.sena.models.IngresoInsumo;
import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.models.Medidas;
import com.proyectoT.sena.models.Ingresos;

@Component
public class IngresoInsumoMapperImpl implements IngresoInsumoMapper {

    @Override
    public IngresoInsumoDTO toDto(IngresoInsumo entity) {
        if (entity == null) return null;

        IngresoInsumoDTO dto = new IngresoInsumoDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());

        if (entity.getInput() != null) {
            dto.setInputId(entity.getInput().getId());
            dto.setInputName(entity.getInput().getInputName());
        }

        if (entity.getMeasure() != null) {
            dto.setMeasureId(entity.getMeasure().getId());
            dto.setMeasureName(entity.getMeasure().getMedNam());
        }

        if (entity.getIncome() != null) {
            dto.setIncomeId(entity.getIncome().getId());
        }

        return dto;
    }

    @Override
    public IngresoInsumo toEntity(IngresoInsumoDTO dto) {
        if (dto == null) return null;

        IngresoInsumo entity = new IngresoInsumo();
        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());

        if (dto.getInputId() != null) {
            Insumo insumo = new Insumo();
            insumo.setId(dto.getInputId());
            entity.setInput(insumo);
        }

        if (dto.getMeasureId() != null) {
            Medidas medida = new Medidas();
            medida.setId(dto.getMeasureId());
            entity.setMeasure(medida);
        }

        if (dto.getIncomeId() != null) {
            Ingresos ingreso = new Ingresos();
            ingreso.setId(dto.getIncomeId());
            entity.setIncome(ingreso);
        }

        return entity;
    }
}

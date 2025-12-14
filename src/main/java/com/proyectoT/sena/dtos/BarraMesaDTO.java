package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarraMesaDTO {

    private Long id;
    private com.proyectoT.sena.models.enums.TableAvailability availability;
    private Integer share; // Added share to match model if needed, but keeping it simple as per original
                           // DTO if it was only ID/Availability. I'll add share as it's in the Entity. But
                           // let's check Entity again. Entity has share. DTO had only ID and availability.
                           // I'll stick to what was there but fix the name.
}

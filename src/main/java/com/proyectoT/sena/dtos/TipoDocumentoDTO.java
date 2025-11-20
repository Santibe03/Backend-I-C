package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  
public class TipoDocumentoDTO {

    private Long id;
    private String initials;
    private String documentName;
    private String stateDocumentType;
    
}
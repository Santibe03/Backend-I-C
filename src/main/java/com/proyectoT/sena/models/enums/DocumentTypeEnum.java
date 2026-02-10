package com.proyectoT.sena.models.enums;

import lombok.Getter;

@Getter
public enum DocumentTypeEnum {
    CC("Cédula de Ciudadanía"),
    TI("Tarjeta de Identidad"),
    CE("Cédula de Extranjería"),
    RC("Registro Civil"),
    PA("Pasaporte"),
    DIE("Documento de Identificación Extranjero"),
    PEP("Permiso Especial de Permanencia"),
    PPT("Permiso por Protección Temporal");

    private final String documentName;

    DocumentTypeEnum(String documentName) {
        this.documentName = documentName;
    }
}

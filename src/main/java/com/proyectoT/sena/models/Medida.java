package com.proyectoT.sena.models;

public enum Medida {
    KILOGRAMO("KG"),
    GRAMO("G"),
    LITRO("L"),
    MILILITRO("ML"),
    UNIDAD("UND"),
    ONZA("OZ");

    private String abbreviation;

    Medida(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}

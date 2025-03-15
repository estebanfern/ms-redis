package com.esteban.ms.common.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Genero {
    MALE("Masculino"),
    FEMALE("Femenino"),
    OTHER("Otro"),
    ;
    private final String nombre;
}

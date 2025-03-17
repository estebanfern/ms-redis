package com.esteban.ms.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "clienteid")
@Getter
@Setter
@ToString
public class Cliente extends Persona {

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Cuenta> cuentas = new ArrayList<>();

}

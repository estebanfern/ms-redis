package com.esteban.ms.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
@ToString
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

}

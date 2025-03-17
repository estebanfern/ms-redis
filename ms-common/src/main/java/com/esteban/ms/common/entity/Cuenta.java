package com.esteban.ms.common.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
@ToString
public class Cuenta {

    @Id
    @Column(name = "numero_cuenta", nullable = false, unique = true)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Column(name = "estado", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Movimiento> movimientos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnore
    private Cliente cliente;

}

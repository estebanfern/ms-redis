package com.esteban.ms.clientes;

import com.esteban.ms.common.entity.Cliente;
import com.esteban.ms.common.entity.Cuenta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DominioClienteTest {

    @Test
    void testClienteEntity() {
        Cliente cliente = new Cliente();

        String contrasenaEsperada = "miPassword";
        boolean activeEsperado = true;
        List<Cuenta> cuentasEsperadas = new ArrayList<>();

        cliente.setContrasena(contrasenaEsperada);
        cliente.setActive(activeEsperado);
        cliente.setCuentas(cuentasEsperadas);

        Assertions.assertEquals(contrasenaEsperada, cliente.getContrasena());
        Assertions.assertTrue(cliente.getActive());
        Assertions.assertEquals(cuentasEsperadas, cliente.getCuentas());
    }

}

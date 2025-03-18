package com.esteban.ms.cuentas;

import com.esteban.ms.common.entity.TipoCuenta;
import com.esteban.ms.cuentas.dto.in.CrearCuentaIn;
import com.esteban.ms.cuentas.dto.out.CuentaOut;
import com.esteban.ms.cuentas.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CuentasControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CuentaService cuentaService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CuentaService cuentaService() {
            return Mockito.mock(CuentaService.class);
        }
    }

    @Test
    void testCrearCuenta() throws Exception {
        CrearCuentaIn input = new CrearCuentaIn();
        input.setNumeroCuenta("12345");
        input.setTipoCuenta(TipoCuenta.AHORROS);
        input.setClienteId(100L);

        CuentaOut expectedCuentaOut = new CuentaOut();
        expectedCuentaOut.setNumeroCuenta("12345");
        expectedCuentaOut.setTipoCuenta(TipoCuenta.AHORROS);

        when(cuentaService.crearCuenta(any(CrearCuentaIn.class))).thenReturn(expectedCuentaOut);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = objectMapper.writeValueAsString(input);

        mockMvc.perform(post("/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.numeroCuenta").value("12345"))
                .andExpect(jsonPath("$.result.tipoCuenta").value("AHORROS"))
        ;
    }

}

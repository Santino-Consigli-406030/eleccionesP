package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.models.SeccionModel;
import ar.edu.utn.frc.tup.lc.iv.services.SeccionesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class SeccionesControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeccionesService seccionesService;
    @Test
    void getSeccionesByDistritoAndSeccionTest() throws Exception {
        List<SeccionModel> secciones = new ArrayList<>();
        SeccionModel seccion = new SeccionModel();
        seccion.setSeccionNombre("Nombre");
        seccion.setSeccionId(1L);
        secciones.add(seccion);

        when(seccionesService.getSeccionesList(1L,null)).thenReturn(secciones);

        mockMvc.perform(get("/secciones")
                .param("distritoId", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].seccionNombre").value("Nombre"));
    }
}
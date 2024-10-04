package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.CargosDistritosDTO;
import ar.edu.utn.frc.tup.lc.iv.models.CargoModel;
import ar.edu.utn.frc.tup.lc.iv.models.DistritoModel;
import ar.edu.utn.frc.tup.lc.iv.services.CargosService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CargosControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CargosService cargosService;
    @Test
    public void getCargosByDistritoTest() throws Exception{
        DistritoModel distritoModel = new DistritoModel(1L, "Cordoba");
        CargoModel cargoModel = new CargoModel(1L, "Cargo en Cordoba");
        List<CargoModel> cargoModels = new ArrayList<>();
        cargoModels.add(cargoModel);

        CargosDistritosDTO cargosDistritosDTO = new CargosDistritosDTO();
        cargosDistritosDTO.setDistrito(distritoModel);
        cargosDistritosDTO.setCargos(cargoModels);

        when(cargosService.getCargosListWithDistrito(1L)).thenReturn(cargosDistritosDTO);
        mockMvc.perform(get("/cargos")
                        .param("idDistrito", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distrito.distritoNombre").value("Cordoba"))
                .andExpect(jsonPath("$.cargos[0].cargoNombre").value("Cargo en Cordoba"));

    }
    @Test
    public void getCargosByDistritoTestNoSucess() throws Exception{

        when(cargosService.getCargosListWithDistrito(1L))
                .thenThrow(new RuntimeException("Distrito no encontrado"));
        mockMvc.perform(get("/cargos")
                        .param("idDistrito", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Distrito no encontrado"));

    }

}
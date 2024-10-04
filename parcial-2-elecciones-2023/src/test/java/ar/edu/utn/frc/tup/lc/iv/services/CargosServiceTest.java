package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.CargosDistritosDTO;
import ar.edu.utn.frc.tup.lc.iv.models.CargoModel;
import ar.edu.utn.frc.tup.lc.iv.models.DistritoModel;
import ar.edu.utn.frc.tup.lc.iv.records.CargoRecord;
import ar.edu.utn.frc.tup.lc.iv.records.DistritoRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CargosServiceTest {


    @SpyBean
    private CargosService cargosService;

    @MockBean
    private HttpClient httpClient;
    @MockBean(name="mergerMapper")
    private ModelMapper modelMapper;
    @Test
    void getCargosListWithDistritoTest()  {

        List<DistritoRecord> distritoRecordList = new ArrayList<>();
        DistritoRecord distritoRecord = new DistritoRecord(1L,"Cordoba");
        distritoRecordList.add(distritoRecord);
        ResponseEntity<List<DistritoRecord>> distritosExpected = new ResponseEntity<>(distritoRecordList, HttpStatus.OK);
        when(httpClient.get(
                eq("http://api-gateway:8081/api-externa/distritos?distritoId={idDistrito}"),
                any(ParameterizedTypeReference.class),
                eq(1L)
        )).thenReturn(distritosExpected);
        List<CargoRecord> cargoRecordsList = new ArrayList<>();
        CargoRecord cargoRecord = new CargoRecord(1L,"Cargo en cordoba","1");
        cargoRecordsList.add(cargoRecord);
        ResponseEntity<List<CargoRecord>> cargosRecordExpected = ResponseEntity.ok(cargoRecordsList);
        when(httpClient.get(
                eq("http://api-gateway:8081/api-externa/cargos?distritoId={idDistrito}"),
                any(ParameterizedTypeReference.class),
                eq(1L)
        )).thenReturn(cargosRecordExpected);
        DistritoModel distritoModel = new DistritoModel(1L,"Cordoba");
        CargoModel cargoModel = new CargoModel(1L,"Cargo en cordoba");
        when(modelMapper.map(any(DistritoRecord.class), eq(DistritoModel.class))).thenReturn(distritoModel);
        when(modelMapper.map(any(CargoRecord.class), eq(CargoModel.class))).thenReturn(cargoModel);
        CargosDistritosDTO cargosDistritosDTOExpected = new CargosDistritosDTO();
        cargosDistritosDTOExpected.setDistrito(distritoModel);
        List<CargoModel> cargoModelsExpected = new ArrayList<>();
        cargoModelsExpected.add(cargoModel);
        cargosDistritosDTOExpected.setCargos(cargoModelsExpected);
        CargosDistritosDTO cargosDistritosDTOActual = cargosService.getCargosListWithDistrito(1L);

        Assertions.assertEquals(cargosDistritosDTOExpected.getCargos().get(0).getCargoNombre(),cargosDistritosDTOActual.getCargos().get(0).getCargoNombre());




    }
}
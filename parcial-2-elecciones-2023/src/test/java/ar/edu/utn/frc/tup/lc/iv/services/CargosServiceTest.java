package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.records.CargoRecord;
import ar.edu.utn.frc.tup.lc.iv.records.DistritoRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class CargosServiceTest {


    @SpyBean
    private CargosService cargosService;

    @MockBean
    private HttpClient httpClient;
    @Test
    void getCargosListWithDistritoTest()  {

        List<DistritoRecord> distritoRecordList = new ArrayList<>();
        DistritoRecord distritoRecord = new DistritoRecord(1L,"Cordoba");
        distritoRecordList.add(distritoRecord);
        ResponseEntity<List<DistritoRecord>> distritosExpected = ResponseEntity.ok(distritoRecordList);
        when(httpClient.get(
                eq("http://api-gateway:8081/api-externa/distritos?distritoId={idDistrito}"),
                any(ParameterizedTypeReference.class)
        )).thenReturn(distritosExpected);
        List<CargoRecord> cargoRecordsList = new ArrayList<>();
        CargoRecord cargoRecord = new CargoRecord(1L,"Cargo en cordoba","1");
        cargoRecordsList.add(cargoRecord);
        ResponseEntity<List<CargoRecord>> cargosRecordExpected = ResponseEntity.ok(cargoRecordsList);
        when(httpClient.get(
                eq("http://api-gateway:8081/api-externa/cargos?distritoId={idDistrito}"),
                any(ParameterizedTypeReference.class)
        )).thenReturn(cargosRecordExpected);



    }
}
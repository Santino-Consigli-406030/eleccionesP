package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.CargosDistritosDTO;
import ar.edu.utn.frc.tup.lc.iv.models.CargoModel;
import ar.edu.utn.frc.tup.lc.iv.models.CargoPrueba;
import ar.edu.utn.frc.tup.lc.iv.models.DistritoModel;
import ar.edu.utn.frc.tup.lc.iv.records.CargoRecord;
import ar.edu.utn.frc.tup.lc.iv.records.DistritoRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargosService {

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private ModelMapper modelMapper;
    public CargosDistritosDTO getCargosListWithDistrito(Long idDistrito) {

        ResponseEntity<List<DistritoRecord>> distritoRecords = httpClient.get("http://api-gateway:8081/api-externa/distritos?distritoId={idDistrito}", new ParameterizedTypeReference<List<DistritoRecord>>() {
        },idDistrito);
        DistritoRecord distritos = distritoRecords.getBody().get(0);

        ResponseEntity<List<CargoRecord>> cargosById = httpClient.get("http://api-gateway:8081/api-externa/cargos?distritoId={idDistrito}", new ParameterizedTypeReference<List<CargoRecord>>() {
        },idDistrito);
        List<CargoRecord> cargoRecords = cargosById.getBody();

        DistritoModel distritoModel = modelMapper.map(distritos, DistritoModel.class);
        List<CargoModel> cargoModels = new ArrayList<>();
        assert cargoRecords != null;
        for(CargoRecord cargoRecord : cargoRecords)
        {
            CargoModel cargoModel = modelMapper.map(cargoRecord, CargoModel.class);
            cargoModels.add(cargoModel);
        }
        CargosDistritosDTO cargosDistritosDTO = new CargosDistritosDTO();
        cargosDistritosDTO.setDistrito(distritoModel);
        cargosDistritosDTO.setCargos(cargoModels);
        return cargosDistritosDTO;

    }
}

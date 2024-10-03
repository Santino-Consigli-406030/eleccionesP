package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.models.SeccionModel;
import ar.edu.utn.frc.tup.lc.iv.records.SeccionRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeccionesService {
    @Autowired
    HttpClient httpClient;
    @Autowired
    ModelMapper modelMapper;
    public List<SeccionModel> getSeccionesList(Long distritoId, Long seccionId) {

        ResponseEntity<List<SeccionRecord>> secciones;
        if(seccionId==null)
        {
            secciones = httpClient.get("http://eleccionesApiExterna:8080/secciones?distritoId={distritoId}", new ParameterizedTypeReference<List<SeccionRecord>>() {
            },distritoId);
        }
        else {
            secciones = httpClient.get("http://eleccionesApiExterna:8080/secciones?distritoId={distritoId}&seccionId={seccionId}", new ParameterizedTypeReference<List<SeccionRecord>>() {
            },distritoId,seccionId);
        }
        List<SeccionRecord> seccionRecords = secciones.getBody();
        List<SeccionModel> seccionModels = new ArrayList<>();

        assert seccionRecords != null;
        for(SeccionRecord seccionRecord: seccionRecords)
        {
            SeccionModel seccionModel = modelMapper.map(seccionRecord,SeccionModel.class);
            seccionModels.add(seccionModel);
        }
        return seccionModels;

    }
}

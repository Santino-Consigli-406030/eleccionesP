package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.models.DistritoModel;
import ar.edu.utn.frc.tup.lc.iv.records.DistritoRecord;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistritoService {

    @Autowired
    HttpClient httpClient;

    @Autowired
    ModelMapper modelMapper;

    @CircuitBreaker(name = "default", fallbackMethod = "getDistritosFallback")
    public List<DistritoModel> getDistritosList(String nombre) {

        ResponseEntity<List<DistritoRecord>> distritos;
       if(nombre==null)
       {
          distritos = httpClient.get("http://api-gateway:8081/api-externa/distritos", new ParameterizedTypeReference<List<DistritoRecord>>(){});
       }
       else {
           distritos = httpClient.get("http://api-gateway:8081/api-externa/distritos?distritoNombre={nombre}", new ParameterizedTypeReference<List<DistritoRecord>>(){},nombre);
       }
        return getDistritoModels(distritos);
    }
    public List<DistritoModel> getDistritosFallback(String nombre, Throwable throwable) {
        List<DistritoModel> distritoModels = new ArrayList<>();
        DistritoModel buenosAires = new DistritoModel();
        buenosAires.setDistritoNombre("Buenos Aires");
        buenosAires.setDistritoId(1L);
        distritoModels.add(buenosAires);

        DistritoModel caba = new DistritoModel();
        caba.setDistritoNombre("CABA");
        caba.setDistritoId(2L);
        distritoModels.add(caba);

        DistritoModel cordoba = new DistritoModel();
        cordoba.setDistritoNombre("Córdoba");
        cordoba.setDistritoId(3L);
        distritoModels.add(cordoba);

        DistritoModel mendoza = new DistritoModel();
        mendoza.setDistritoNombre("Mendoza");
        mendoza.setDistritoId(4L);
        distritoModels.add(mendoza);

        DistritoModel santaFe = new DistritoModel();
        santaFe.setDistritoNombre("Santa Fe");
        santaFe.setDistritoId(5L);
        distritoModels.add(santaFe);

        DistritoModel tucuman = new DistritoModel();
        tucuman.setDistritoNombre("Tucumán");
        tucuman.setDistritoId(6L);
        distritoModels.add(tucuman);

        DistritoModel entreRios = new DistritoModel();
        entreRios.setDistritoNombre("Entre Ríos");
        entreRios.setDistritoId(7L);
        distritoModels.add(entreRios);

        DistritoModel neuquen = new DistritoModel();
        neuquen.setDistritoNombre("Neuquén");
        neuquen.setDistritoId(8L);
        distritoModels.add(neuquen);

        DistritoModel salta = new DistritoModel();
        salta.setDistritoNombre("Salta");
        salta.setDistritoId(9L);
        distritoModels.add(salta);

        DistritoModel chaco = new DistritoModel();
        chaco.setDistritoNombre("Chaco");
        chaco.setDistritoId(10L);
        distritoModels.add(chaco);

        return distritoModels;
    }


    public List<DistritoModel> getDistritoModels(ResponseEntity<List<DistritoRecord>> distritos) {
        List<DistritoModel> distritoModels = new ArrayList<>();
        List<DistritoRecord> distritoRecords = distritos.getBody();
        assert distritoRecords != null;
        for(DistritoRecord distritoRecord : distritoRecords)
        {
            DistritoModel distritoModel = new DistritoModel();
            distritoModel.setDistritoId(distritoRecord.distritoId());
            distritoModel.setDistritoNombre(distritoRecord.distritoNombre());
            distritoModels.add(distritoModel);
        }
        return distritoModels;
    }
}

package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ResultadosDTO;
import ar.edu.utn.frc.tup.lc.iv.models.ResultadoModel;
import ar.edu.utn.frc.tup.lc.iv.records.ResultadoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Service
public class ResultadosService {
    @Autowired
    HttpClient httpClient;
    public ResultadosDTO getResultadosList(Long distritoId, Long seccionId) {
        Double votosTotales= 0.0;
        ResultadosDTO resultadosDTO = new ResultadosDTO();
        resultadosDTO.setDistrito("Cordoba");
        resultadosDTO.setSeccion("Union");
        List<ResultadoRecord> resultadoRecords= getResultados(distritoId,seccionId);
        Map<String,Integer> resultadoGroups = new HashMap<>();

        assert resultadoRecords != null;
        for(ResultadoRecord resultadoRecord : resultadoRecords)
        {
            String agrupacionNameOrType = resultadoRecord.agrupacionNombre();
            Integer votos = resultadoRecord.votosCantidad();

            if (resultadoGroups.containsKey(agrupacionNameOrType)) {
                resultadoGroups.put(agrupacionNameOrType, resultadoGroups.get(agrupacionNameOrType) + votos);
            } else {
                resultadoGroups.put(agrupacionNameOrType, votos);
            };

            String voteType = resultadoRecord.votosTipo();
            Integer votosOther = resultadoRecord.votosCantidad();
            if (resultadoGroups.containsKey(voteType)) {
                resultadoGroups.put(voteType, resultadoGroups.get(voteType) + votosOther);
            } else if(!Objects.equals(voteType, "POSITIVO") && !Objects.equals(voteType, "RECURRIDO") && !Objects.equals(voteType,"")) {

                resultadoGroups.put(voteType, votosOther);
            };
            votosTotales=votosTotales+resultadoRecord.votosCantidad();
        }
        List<ResultadoModel> resultadoModels = getResultadosModel(resultadoGroups,votosTotales);
        int orden =0;
        for(ResultadoModel resultadoModel : resultadoModels)
        {
            orden=orden+1;
            resultadoModel.setOrden(orden);
        }
        resultadosDTO.setResultados(resultadoModels);
        return  resultadosDTO;
    }

    public List<ResultadoModel> getResultadosModel(Map<String, Integer> resultadoGroups, Double votosTotales) {
        List<ResultadoModel> resultadoModels = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : resultadoGroups.entrySet() )
        {
            ResultadoModel resultadoModel = new ResultadoModel();
            resultadoModel.setNombre(entry.getKey());
            resultadoModel.setVotos(entry.getValue());
            resultadoModel.setPorcentaje((entry.getValue() * 1.0) / votosTotales);
            resultadoModels.add(resultadoModel);
        }
        resultadoModels.sort((resultadoModel1, resultadoModel2) ->
                Integer.compare(resultadoModel2.getVotos(), resultadoModel1.getVotos())
        );
        return resultadoModels;
    }

    public List<ResultadoRecord> getResultados(Long distritoId, Long seccionId) {
        ResponseEntity<List<ResultadoRecord>> response;
        if(!distritoId.equals(4L))
        {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"The api extern doesn't have results");
        }
        response = httpClient.get("http://eleccionesApiExterna:8080/resultados?seccionId={seccionId}&distritoId={distritoId}",
                new ParameterizedTypeReference<List<ResultadoRecord>>() {
                }, seccionId, distritoId);
        return response.getBody();
    }
}

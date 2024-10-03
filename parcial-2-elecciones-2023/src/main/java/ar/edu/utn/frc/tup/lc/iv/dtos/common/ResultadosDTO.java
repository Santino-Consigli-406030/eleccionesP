package ar.edu.utn.frc.tup.lc.iv.dtos.common;

import ar.edu.utn.frc.tup.lc.iv.models.ResultadoModel;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
public class ResultadosDTO {

    String distrito;

    String seccion;

    List<ResultadoModel> resultados;
}

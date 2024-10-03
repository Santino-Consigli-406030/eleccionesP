package ar.edu.utn.frc.tup.lc.iv.dtos.common;

import ar.edu.utn.frc.tup.lc.iv.models.CargoModel;
import ar.edu.utn.frc.tup.lc.iv.models.DistritoModel;
import lombok.Data;

import java.util.List;

@Data
public class CargosDistritosDTO {

    DistritoModel distrito;

    List<CargoModel> cargos ;
}

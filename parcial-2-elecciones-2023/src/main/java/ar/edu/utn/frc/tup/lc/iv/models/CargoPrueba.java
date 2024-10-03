package ar.edu.utn.frc.tup.lc.iv.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoPrueba {
     Long cargoId;
     String cargoNombre;
    Long distritoId;

}

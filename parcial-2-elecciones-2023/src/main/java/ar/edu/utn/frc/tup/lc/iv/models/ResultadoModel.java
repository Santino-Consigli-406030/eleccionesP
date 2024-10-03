package ar.edu.utn.frc.tup.lc.iv.models;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ResultadoModel {

    Integer orden;

    public String nombre;

    Integer votos;

    @NumberFormat(pattern = "0.0000")
    Double porcentaje;
}

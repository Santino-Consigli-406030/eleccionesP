package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ResultadosDTO;
import ar.edu.utn.frc.tup.lc.iv.models.SeccionModel;
import ar.edu.utn.frc.tup.lc.iv.services.ResultadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultadosController {

    @Autowired
    private ResultadosService resultadosService;
    @GetMapping("/resultados")
    public ResultadosDTO getResultadosBySeccionAndDistrito(@RequestParam(value = "seccionId", required = true) Long seccionId, @RequestParam(value = "distritoId",required = false) Long distritoId)
    {

        return resultadosService.getResultadosList(distritoId,seccionId);
    }
}

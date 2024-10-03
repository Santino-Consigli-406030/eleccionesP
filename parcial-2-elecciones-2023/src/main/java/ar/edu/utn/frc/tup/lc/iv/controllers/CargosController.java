package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.CargosDistritosDTO;
import ar.edu.utn.frc.tup.lc.iv.services.CargosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CargosController {


    @Autowired
    private CargosService cargosService;
    @GetMapping("/cargos")
    public CargosDistritosDTO getCargosByDistrito(@RequestParam Long idDistrito)
    {
        return cargosService.getCargosListWithDistrito(idDistrito);
    }

}

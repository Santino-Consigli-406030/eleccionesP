package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.models.SeccionModel;
import ar.edu.utn.frc.tup.lc.iv.services.SeccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeccionesController {
    @Autowired
    private SeccionesService seccionesService;
    @GetMapping("/secciones")
    public List<SeccionModel> getSeccionesByDistritoAndSeccion(@RequestParam(value = "distritoId", required = true) Long distritoId, @RequestParam(value = "seccionId",required = false) Long seccionId)
    {

        return seccionesService.getSeccionesList(distritoId,seccionId);
    }
}

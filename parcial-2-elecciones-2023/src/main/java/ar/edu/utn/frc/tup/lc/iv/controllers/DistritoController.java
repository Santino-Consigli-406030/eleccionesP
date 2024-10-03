package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.models.DistritoModel;
import ar.edu.utn.frc.tup.lc.iv.records.DistritoRecord;
import ar.edu.utn.frc.tup.lc.iv.services.DistritoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/distritos")
public class DistritoController {


    @Autowired
    private DistritoService distritoService;


     @GetMapping()
    public List<DistritoModel> getDistritoByNameOrAll( @RequestParam(value = "nombre", required = false) String nombre)
     {

         return distritoService.getDistritosList(nombre);
     }



}

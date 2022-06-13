package com.clinicaOdontologica.clinicaOdontologica.Controller;

import com.clinicaOdontologica.clinicaOdontologica.DAO.OdonotologoIDAO;
import com.clinicaOdontologica.clinicaOdontologica.Model.Odontologo;
import com.clinicaOdontologica.clinicaOdontologica.Services.OdonotologoService;
import com.clinicaOdontologica.clinicaOdontologica.Services.ServiceException.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdonotologoService odontologoService = new OdonotologoService(new OdonotologoIDAO());

    @GetMapping
    public ResponseEntity<List<Odontologo>> getTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @PostMapping("register")
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @PutMapping()
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response = null;

        if (odontologo.getID() != null && odontologoService.buscarOdontologo(odontologo.getID()) != null) {
            try {
                response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
            } catch (ServiceException ex) {
                response = ResponseEntity.badRequest().body(null);
            }
        }
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;

    }
}

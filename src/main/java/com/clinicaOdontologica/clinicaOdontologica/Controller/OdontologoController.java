package com.clinicaOdontologica.clinicaOdontologica.Controller;

import com.clinicaOdontologica.clinicaOdontologica.DAO.OdontologoDAO;
import com.clinicaOdontologica.clinicaOdontologica.Model.Odontologo;
import com.clinicaOdontologica.clinicaOdontologica.Services.OdontologoService;
import com.clinicaOdontologica.clinicaOdontologica.Services.ServiceException.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDAO());

    @GetMapping
    public ResponseEntity<List<Odontologo>> getTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> getOdontologo(@PathVariable("id") Integer id){
        return ResponseEntity.ok(odontologoService.buscarOdontologo(id));
    }
    @PostMapping("/register")
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity respuesta = ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo){

        ResponseEntity<Odontologo> response = null;

        if (odontologo.getID() != null && odontologoService.buscarOdontologo(odontologo.getID()) != null) {
            try {
                response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
            } catch (ServiceException ex) {
                response = ResponseEntity.badRequest().body(null);
            }
        }
        else{
            response = ResponseEntity.badRequest().body(odontologo);
        }

        return response;

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOdontologo(@PathVariable("id") Integer id){
        if(odontologoService.buscarOdontologo(id) != null){
            odontologoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

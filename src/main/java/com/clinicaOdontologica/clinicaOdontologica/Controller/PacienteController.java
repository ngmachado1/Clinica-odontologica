package com.clinicaOdontologica.clinicaOdontologica.Controller;


import com.clinicaOdontologica.clinicaOdontologica.DAO.PacienteIDAO;
import com.clinicaOdontologica.clinicaOdontologica.Model.Paciente;
import com.clinicaOdontologica.clinicaOdontologica.Services.PacienteService;
import com.clinicaOdontologica.clinicaOdontologica.Services.ServiceException.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    /**
     * El servicio a usar por parte del controller
     */
    private PacienteService pacienteService = new PacienteService(new PacienteIDAO());


    /**
     * Metodo engargado de implementar el Enpoint para consultar todos los Pacientes.
     */
    @GetMapping
    public ResponseEntity<List<Object>> getTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PostMapping("registrar")
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {

        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));

    }

    @PutMapping()
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response = null;

        if (paciente.getId() != null && pacienteService.buscarPaciente(paciente.getId()) != null) {
            try {
                response = ResponseEntity.ok(pacienteService.actualizar(paciente));
            } catch (ServiceException ex) {
                response = ResponseEntity.badRequest().body(null);
            }
        }
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }
}

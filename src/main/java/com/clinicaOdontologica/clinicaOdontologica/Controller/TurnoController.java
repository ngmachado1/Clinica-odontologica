package com.clinicaOdontologica.clinicaOdontologica.Controller;

import com.clinicaOdontologica.clinicaOdontologica.DAO.OdontologoDAO;
import com.clinicaOdontologica.clinicaOdontologica.DAO.PacienteDAO;
import com.clinicaOdontologica.clinicaOdontologica.DAO.TurnoDAO;
import com.clinicaOdontologica.clinicaOdontologica.Model.Odontologo;
import com.clinicaOdontologica.clinicaOdontologica.Model.Paciente;
import com.clinicaOdontologica.clinicaOdontologica.Model.Turno;
import com.clinicaOdontologica.clinicaOdontologica.Services.OdontologoService;
import com.clinicaOdontologica.clinicaOdontologica.Services.PacienteService;
import com.clinicaOdontologica.clinicaOdontologica.Services.ServiceException.ServiceException;
import com.clinicaOdontologica.clinicaOdontologica.Services.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private TurnoService turnoService = new TurnoService(new TurnoDAO());
    private PacienteService pacienteService = new PacienteService(new PacienteDAO());
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDAO());
    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurno(@PathVariable("id") Integer id){
        return ResponseEntity.ok(turnoService.buscarTurno(id));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {

        ResponseEntity<Turno> respuesta;

        Paciente p = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Odontologo o = odontologoService.buscarOdontologo(turno.getOdontologo().getID());

        if(p != null && o != null){
            turnoService.registrarTurno(turno);
            respuesta = ResponseEntity.ok(turno);
        }else{
            respuesta = ResponseEntity.badRequest().body(null);
        }

        return respuesta;
    }
    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){

        ResponseEntity<Turno> response = null;

        if (turno.getId() != null && turnoService.buscarTurno(turno.getId()) != null) {
            try {
                response = ResponseEntity.ok(turnoService.actualizarTurno(turno));
            } catch (ServiceException ex) {
                response = ResponseEntity.badRequest().body(null);
            }
        }
        else{
            response = ResponseEntity.badRequest().body(turno);
        }

        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Turno> borrarTurno(@PathVariable("id") Integer id){
        if(turnoService.buscarTurno(id) != null){
            turnoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

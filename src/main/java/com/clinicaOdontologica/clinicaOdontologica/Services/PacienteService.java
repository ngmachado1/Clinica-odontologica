package com.clinicaOdontologica.clinicaOdontologica.Services;

import com.clinicaOdontologica.clinicaOdontologica.DAO.DomicilioIDAO;
import com.clinicaOdontologica.clinicaOdontologica.DAO.IDao;

import com.clinicaOdontologica.clinicaOdontologica.DAO.PacienteIDAO;
import com.clinicaOdontologica.clinicaOdontologica.Model.Domicilio;
import com.clinicaOdontologica.clinicaOdontologica.Model.Paciente;
import com.clinicaOdontologica.clinicaOdontologica.Services.ServiceException.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private IDao<Paciente> pacienteIDao;
    private DomicilioIDAO domicilioIDAO = new DomicilioIDAO();

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public IDao<Paciente> getPacienteIDao() {
        return pacienteIDao;
    }

    public void setPacienteIDao(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }


    public Paciente guardarPaciente(Paciente p){
        return pacienteIDao.crear(p);
    }

    public Paciente buscarPaciente(int id){
        return pacienteIDao.buscar(id);
    }

    public List<Object> buscarTodos(){
        List<Paciente> pacientes = pacienteIDao.buscarTodos();
        List<Object> o = new ArrayList();
        for(Paciente p: pacientes){
            Domicilio d = domicilioIDAO.buscar(p.getDomicilio_id());
            o.add(p);
            o.add(d);
        }
        return o;
    }

    public void eliminar(int id){
        pacienteIDao.eliminar(id);
    }

    public Paciente actualizar(Paciente p) throws ServiceException {
        return pacienteIDao.actualizar(p);
    }
}

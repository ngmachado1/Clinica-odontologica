package com.clinicaOdontologica.clinicaOdontologica.Services;

import com.clinicaOdontologica.clinicaOdontologica.DAO.IDao;
import com.clinicaOdontologica.clinicaOdontologica.Model.Turno;
import com.clinicaOdontologica.clinicaOdontologica.Services.ServiceException.ServiceException;

import java.util.List;

public class TurnoService {
    private IDao<Turno> turnoDao;

    public TurnoService(IDao<Turno> turnoDao) {
        this.turnoDao = turnoDao;
    }

    public Turno registrarTurno(Turno turno){
        return turnoDao.crear(turno);
    }
    public List<Turno> listar(){
        return turnoDao.buscarTodos();
    }

    public Turno buscarTurno(int id){
        return turnoDao.buscar(id);
    }

    public Turno actualizarTurno(Turno turno) throws ServiceException {
        return turnoDao.actualizar(turno);
    }
    public void eliminar(Integer id){
        turnoDao.eliminar(id);
    }
}

package com.clinicaOdontologica.clinicaOdontologica.Services;

import com.clinicaOdontologica.clinicaOdontologica.DAO.IDao;
import com.clinicaOdontologica.clinicaOdontologica.Model.Odontologo;
import com.clinicaOdontologica.clinicaOdontologica.Services.ServiceException.ServiceException;

import java.util.List;

public class OdonotologoService{
    private IDao<Odontologo> odontologoIDao;

    public OdonotologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public IDao<Odontologo> getOdontologoIDao() {
        return odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }



    public Odontologo guardarOdontologo(Odontologo o){
        return odontologoIDao.crear(o);
    }
    public Odontologo buscarOdontologo(int id){
        return odontologoIDao.buscar(id);
    }
    public List<Odontologo> buscarTodos(){
        return odontologoIDao.buscarTodos();
    }
    public void eliminar(Integer id){
        odontologoIDao.eliminar(id);
    }
    public Odontologo actualizar(Odontologo o) throws ServiceException {
        return odontologoIDao.actualizar(o);

    }
}

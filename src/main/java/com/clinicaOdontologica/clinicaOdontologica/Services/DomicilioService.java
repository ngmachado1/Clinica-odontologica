package com.clinicaOdontologica.clinicaOdontologica.Services;

import com.clinicaOdontologica.clinicaOdontologica.DAO.IDao;
import com.clinicaOdontologica.clinicaOdontologica.Model.Domicilio;

import java.util.List;

public class DomicilioService {
    private IDao<Domicilio> domicilioIDao;

    public IDao<Domicilio> getDomicilioIDao() {
        return domicilioIDao;
    }

    public void setDomicilioIDao(IDao<Domicilio> domicilioIDao) {
        this.domicilioIDao = domicilioIDao;
    }

    public Domicilio guardarDomicilio(Domicilio d){
        return domicilioIDao.crear(d);
    }
    public Domicilio buscarDomicilio(int id){
        return domicilioIDao.buscar(id);
    }

    public List<Domicilio> buscarTodos(){
        return domicilioIDao.buscarTodos();
    }
    public void eliminar(int id){
        domicilioIDao.eliminar(id);
    }
    public Domicilio actualizar(Domicilio d){
        return domicilioIDao.actualizar(d);
    }
}

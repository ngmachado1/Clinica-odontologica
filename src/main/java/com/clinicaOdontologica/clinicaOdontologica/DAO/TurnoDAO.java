package com.clinicaOdontologica.clinicaOdontologica.DAO;

import com.clinicaOdontologica.clinicaOdontologica.DAO.Util.JdbcConnection;
import com.clinicaOdontologica.clinicaOdontologica.DAO.Util.Utils;
import com.clinicaOdontologica.clinicaOdontologica.Model.Odontologo;
import com.clinicaOdontologica.clinicaOdontologica.Model.Paciente;
import com.clinicaOdontologica.clinicaOdontologica.Model.Turno;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TurnoDAO implements IDao<Turno> {
    private JdbcConnection jdbc = new JdbcConnection();
    private Logger logger = Logger.getLogger(PacienteDAO.class);
    private PacienteDAO pacienteDao = new PacienteDAO();
    private OdontologoDAO odontologoDAO = new OdontologoDAO();
    private Utils utilDate = new Utils();

    @Override
    public Turno buscar(int id) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;
        Turno t = null;
        Paciente p = null;
        Odontologo o = null;
        try(Connection connection  = jdbc.connectionOnDB()){




            preparedStatement = connection.prepareStatement("SELECT * FROM turno where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                int idTurno = res.getInt("id");
                int idOdontologo = res.getInt("id_odontologo");
                int idPaciente = res.getInt("id_paciente");
                Date fechaTurno = res.getDate("fecha_turno");

                p = pacienteDao.buscar(idPaciente);
                o = odontologoDAO.buscar(idOdontologo);
                t = new Turno();
                t.setId(idTurno);
                t.setOdontologo(o);
                t.setPaciente(p);
                t.setDate(utilDate.convertToLocalDateViaSqlDate(fechaTurno));
            }
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    @Override
    public List<Turno> buscarTodos() {
        return null;
    }

    @Override
    public Turno crear(Turno turno) {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Turno actualizar(Turno turno) {
        return null;
    }

}

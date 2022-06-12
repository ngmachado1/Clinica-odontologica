package com.clinicaOdontologica.clinicaOdontologica.DAO;

import com.clinicaOdontologica.clinicaOdontologica.DAO.Util.JdbcConnection;
import com.clinicaOdontologica.clinicaOdontologica.Model.Odontologo;
import com.clinicaOdontologica.clinicaOdontologica.Model.Paciente;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PacienteIDAO implements IDao<Paciente> {
    private JdbcConnection jdbc = new JdbcConnection();
    private Logger logger = Logger.getLogger(PacienteIDAO.class);
    @Override
    public Paciente buscar(int id) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;
        Paciente p = null;
        try(Connection connection  = jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("SELECT * FROM pacientes where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                int ID = res.getInt("id");
                String nombre = res.getString("nombre");
                String apellido = res.getString("apellido");
                int domicilio_id = res.getInt("domicilio_id");
                p = new Paciente();
                p.setId(ID);
                p.setNombre(nombre);
                p.setApellido(apellido);
                p.setDomicilio_id(domicilio_id);
            }
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    @Override
    public List buscarTodos() {
        jdbc.setDriver();
        PreparedStatement preparedStatement =  null;
        List<Paciente> pacientes = new ArrayList();
        try(Connection connection =  jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("SELECT * FROM pacientes");
            ResultSet res =  preparedStatement.executeQuery();

            while(res.next()){
                int ID = res.getInt("id");
                String nombre = res.getString("nombre");
                String apellido = res.getString("apellido");
                int domicilio_id = res.getInt("domicilio_id");
                Paciente p = new Paciente();
                p.setId(ID);
                p.setNombre(nombre);
                p.setApellido(apellido);
                p.setDomicilio_id(domicilio_id);

                pacientes.add(p);
            }

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pacientes;
    }


    @Override
    public Paciente crear(Paciente p) {

        jdbc.setDriver();
        PreparedStatement preparedStatement =  null;
        try(Connection connection = jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("INSERT INTO pacientes (nombre, apellido, domicilio_id) VALUES(?, ?, ?)");
            preparedStatement.setString(1, p.getNombre());
            preparedStatement.setString(2, p.getApellido());
            preparedStatement.setInt(3, p.getDomicilio_id());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    @Override
    public void eliminar(int id) {

        jdbc.setDriver();
        PreparedStatement preparedStatement = null;
        DomicilioIDAO domicilioIDAO = new DomicilioIDAO();

        try(Connection connection  = jdbc.connectionOnDB()) {
            //buscar el paciente
            preparedStatement = connection.prepareStatement("SELECT * FROM pacientes where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            //obtener el id del domicilio
            Integer domicilio_id = null;
            while(res.next()){
                domicilio_id = res.getInt("domicilio_id");
            }
            //eliminar el paciente
            preparedStatement = connection.prepareStatement("DELETE FROM pacientes where id = ?");
            preparedStatement.setInt(1, id);

            //eliminar el domicilio
            domicilioIDAO.eliminar(domicilio_id);

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Paciente actualizar(Paciente p) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;

        // 2 - Conectar y Crear el Statement
        try(Connection connection = jdbc.connectionOnDB()) {
            preparedStatement = connection.prepareStatement("UPDATE pacientes SET apellido = ?, nombre = ?, domicilio_id = ? WHERE id = ?");
            preparedStatement.setInt(4, p.getId());

            preparedStatement.setString(1, p.getApellido());
            preparedStatement.setString(2, p.getNombre());
            preparedStatement.setInt(3, p.getDomicilio_id());

            // 3 - Ejecutar el statement

            preparedStatement.executeUpdate();


            logger.info("Se editó al odontólogo con id: " + p.getId());

        }catch (SQLException e){
            logger.debug("Ha ocurrido un error al editar el odontologo: "+ p.getId());
        }

        return p;
    }
}

package com.clinicaOdontologica.clinicaOdontologica.DAO;

import com.clinicaOdontologica.clinicaOdontologica.DAO.Util.JdbcConnection;
import com.clinicaOdontologica.clinicaOdontologica.Model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OdonotologoIDAO implements IDao<Odontologo>{
    private Logger logger = Logger.getLogger(OdonotologoIDAO.class);
    private JdbcConnection jdbc = new JdbcConnection();

    @Override
    public Odontologo buscar(int id) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;
        Odontologo o = null;
        try(Connection connection  = jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                int idOdontologo = res.getInt("id");
                String apellido = res.getString("apellido");
                String matricula = res.getString("numero_matricula");
                String nombre = res.getString("nombre");
                o = new Odontologo();
                o.setID(idOdontologo);
                o.setMatricula(matricula);
                o.setApellido(apellido);
                o.setNombre(nombre);
            }
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return o;
    }

    @Override
    public List buscarTodos() {
        jdbc.setDriver();
        PreparedStatement preparedStatement =  null;
        List<Odontologo> odontologos = new ArrayList();
        try(Connection connection =  jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
            ResultSet res =  preparedStatement.executeQuery();

            while(res.next()){
                int idOdontologo = res.getInt("id");
                String apellido = res.getString("apellido");
                String matricula = res.getString("numero_matricula");
                String nombre = res.getString("nombre");
                Odontologo o = new Odontologo();
                o.setID(idOdontologo);
                o.setMatricula(matricula);
                o.setApellido(apellido);
                o.setNombre(nombre);

                odontologos.add(o);
            }

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return odontologos;
    }

    @Override
    public Odontologo crear(Odontologo o) {
        jdbc.setDriver();
        PreparedStatement preparedStatement =  null;
        try(Connection connection = jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos (apellido, numero_matricula, nombre) VALUES(?, ?, ?)");
            preparedStatement.setString(1, o.getApellido());
            preparedStatement.setString(2, o.getMatricula());
            preparedStatement.setString(3, o.getNombre());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return o;
    }

    @Override
    public void eliminar(int id) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;

        try(Connection connection  = jdbc.connectionOnDB()) {
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public Odontologo actualizar(Odontologo o) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;

        // 2 - Conectar y Crear el Statement
        try(Connection connection = jdbc.connectionOnDB()) {
            preparedStatement = connection.prepareStatement("UPDATE odontologos SET apellido = ?, nombre = ?, numero_matricula = ? WHERE id = ?");
            preparedStatement.setInt(4, o.getID());

            preparedStatement.setString(1, o.getApellido());
            preparedStatement.setString(2, o.getNombre());
            preparedStatement.setString(3, o.getMatricula());


            // 3 - Ejecutar el statement

            preparedStatement.executeUpdate();
            preparedStatement.close();

            logger.info("Se editó al odontólogo con id: " + o.getID());

        }catch (SQLException  e){
            logger.debug("Ha ocurrido un error al editar el odontologo: "+ o.getID());
        }

        return o;
    }
}

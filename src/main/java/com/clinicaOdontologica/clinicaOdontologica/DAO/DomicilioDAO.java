package com.clinicaOdontologica.clinicaOdontologica.DAO;

import com.clinicaOdontologica.clinicaOdontologica.DAO.Util.JdbcConnection;
import com.clinicaOdontologica.clinicaOdontologica.Model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDAO implements IDao<Domicilio>{
    private Logger logger = Logger.getLogger(OdontologoDAO.class);
    private JdbcConnection jdbc = new JdbcConnection();

    @Override
    public Domicilio buscar(int id) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;
        Domicilio d = null;
        try(Connection connection  = jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                int ID = res.getInt("id");
                String localidad = res.getString("localidad");
                String calle = res.getString("calle");
                String numero = res.getString("numero");
                String provincia = res.getString("provincia");
                d = new Domicilio();
                d.setId(ID);
                d.setCalle(calle);
                d.setNumero(numero);
                d.setLocalidad(localidad);
                d.setProvincia(provincia);
            }
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return d;

    }

    @Override
    public List buscarTodos() {
        jdbc.setDriver();
        PreparedStatement preparedStatement =  null;
        List<Domicilio> domicilios = new ArrayList();
        try(Connection connection =  jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("SELECT * FROM domicilios");
            ResultSet res =  preparedStatement.executeQuery();

            while(res.next()){
                int ID = res.getInt("id");
                String localidad = res.getString("localidad");
                String calle = res.getString("calle");
                String numero = res.getString("numero");
                String provincia = res.getString("provincia");
                Domicilio d = new Domicilio();
                d.setId(ID);
                d.setCalle(calle);
                d.setNumero(numero);
                d.setLocalidad(localidad);
                d.setProvincia(provincia);

                domicilios.add(d);
            }

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return domicilios;
    }

    @Override
    public Domicilio crear(Domicilio d) {
        jdbc.setDriver();
        PreparedStatement preparedStatement =  null;
        try(Connection connection = jdbc.connectionOnDB()){
            preparedStatement = connection.prepareStatement("INSERT INTO domicilios (calle, numero, localidad, provincia) VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, d.getCalle());
            preparedStatement.setString(2, d.getNumero());
            preparedStatement.setString(3, d.getLocalidad());
            preparedStatement.setString(3, d.getProvincia());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return d;
    }

    @Override
    public void eliminar(int id) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;

        try(Connection connection  = jdbc.connectionOnDB()) {
            preparedStatement = connection.prepareStatement("DELETE FROM domicilios where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Domicilio actualizar(Domicilio d) {
        jdbc.setDriver();
        PreparedStatement preparedStatement = null;

        // 2 - Conectar y Crear el Statement
        try(Connection connection = jdbc.connectionOnDB()) {
            preparedStatement = connection.prepareStatement("UPDATE pacientes SET calle = ?, numero = ?, localidad = ?, provincia = ? WHERE id = ?");
            preparedStatement.setInt(5, d.getId());

            preparedStatement.setString(1, d.getCalle());
            preparedStatement.setString(2, d.getNumero());
            preparedStatement.setString(3, d.getLocalidad());
            preparedStatement.setString(4, d.getProvincia());

            // 3 - Ejecutar el statement
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("Se editó al odontólogo con id: " + d.getId());

        }catch (SQLException e){
            logger.debug("Ha ocurrido un error al editar el odontologo: "+ d.getId());
        }
        return d;
    }
}

package com.clinicaOdontologica.clinicaOdontologica.DAO.Util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public String DB_JDBC_DRIVER;
    public String DB_URL;
    public String DB_USER;
    public String DB_PASS;
    public static Logger logger = Logger.getLogger(JdbcConnection.class);

    public JdbcConnection(String DB_JDBC_DRIVER, String DB_URL, String DB_USER, String DB_PASS){
        logger.debug("instanciando configuracion JDBC");
        this.DB_JDBC_DRIVER = DB_JDBC_DRIVER;
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASS = DB_PASS;
    }

    public JdbcConnection(){
        this.DB_JDBC_DRIVER = "org.h2.Driver";
        this.DB_URL = "jdbc:h2:~/db_odonto;INIT=RUNSCRIPT FROM 'create.sql'";
        this.DB_USER = "sa";
        this.DB_PASS = "";
    }

    public Connection connectionOnDB(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void setDriver() {
        try {
            Class.forName(DB_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Error: unable to load driver class" + e.getMessage());
            System.exit(1);
        }

    }
}

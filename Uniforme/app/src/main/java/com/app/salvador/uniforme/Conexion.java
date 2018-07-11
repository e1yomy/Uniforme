package com.app.salvador.uniforme;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Conexion{
    private String query;

    Connection conexion = null;

    //'' Variables para manejo de errores:
    Boolean ErrorHubo;
    String ErrorMensaje;
    String ErrorOrigen;
    Integer ErrorNumero;
    Exception ErrorExcepcion;

    public Boolean getErrorHubo() {
        return ErrorHubo;
    }

    public void setErrorHubo(Boolean errorHubo) {
        ErrorHubo = errorHubo;
    }

    public String getErrorMensaje() {
        return ErrorMensaje;
    }

    public void setErrorMensaje(String errorMensaje) {
        ErrorMensaje = errorMensaje;
    }

    public String getErrorOrigen() {
        return ErrorOrigen;
    }

    public void setErrorOrigen(String errorOrigen) {
        ErrorOrigen = errorOrigen;
    }

    public Integer getErrorNumero() {
        return ErrorNumero;
    }

    public void setErrorNumero(Integer errorNumeor) {
        ErrorNumero = errorNumeor;
    }

    public Exception getErrorExcepcion() {
        return ErrorExcepcion;
    }

    public void setErrorExcepcion(Exception errorExcepcion) {
        ErrorExcepcion = errorExcepcion;
    }


    public Conexion() {
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ConnectionURL=null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://"+Principal.preferences.getString("BDservidor","")+
                    ";databaseName="+Principal.preferences.getString("BDnombre","") +
                    ";user="+Principal.preferences.getString("BDusuario","")+
                    ";password="+Principal.preferences.getString("BDcontrasena","") +";";
            conexion = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException e) {
            setErrorExcepcion(e);
            setErrorHubo(true);
            setErrorMensaje(e.getMessage());
            setErrorNumero(e.hashCode());
            setErrorOrigen(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            setErrorExcepcion(e);
            setErrorHubo(true);
            setErrorMensaje(e.getMessage());
            setErrorNumero(e.hashCode());
            setErrorOrigen(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    public ResultSet EjecutarQuery(String sql )
    {
        setErrorHubo(false);
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            setErrorExcepcion(e);
            setErrorHubo(true);
            setErrorMensaje(e.getMessage());
            setErrorNumero(e.hashCode());
            setErrorOrigen(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return rs;

    }
    public String Prueba() {
        String coffeeName = "";
        try {
            Statement stmt = null;
            query = "select * from Lotes";
            stmt = conexion.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                coffeeName += rs.getString(3);
            }
            System.out.println(coffeeName);

        } catch (Exception e) {
            coffeeName = e.getMessage();
        }
        return coffeeName;
    }

}

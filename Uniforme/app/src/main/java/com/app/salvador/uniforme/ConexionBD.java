package com.app.salvador.uniforme;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Debug;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD extends AppCompatActivity {

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_bd);

         preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final TextInputEditText txtServer = (TextInputEditText)  findViewById(R.id.txtServer);
        final TextInputEditText txtBaseDatos = (TextInputEditText)  findViewById(R.id.txtBaseDatos);
        final TextInputEditText txtUsuario = (TextInputEditText)  findViewById(R.id.txtUsuario);
        final TextInputEditText txtContrasena = (TextInputEditText)  findViewById(R.id.txtContrasena );
        FloatingActionButton fabGuardar= (FloatingActionButton)findViewById(R.id.fabGuardar);
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit()
                        .putString("BDservidor", txtServer.getText().toString() )
                        .putString("BDnombre", txtBaseDatos.getText().toString() )
                        .putString("BDusuario", txtUsuario.getText().toString() )
                        .putString("BDcontrasena", txtContrasena.getText().toString() )
                        .commit();
                Conexion c =new Conexion();
                Toast.makeText(getApplicationContext(),c.Prueba(),Toast.LENGTH_LONG).show();
/*
                ip= txtServer.getText().toString();
                un=txtUsuario.getText().toString();
                pass=txtContrasena.getText().toString();
                db= txtBaseDatos.getText().toString();
                BD bd = new BD();
                bd.execute("");
                */
                finish();

            }

        });

    }
    Connection conn;
    String un,pass,db,ip;
    class BD extends AsyncTask<String,String,String> {

        String var;
        @Override
        protected String doInBackground(String... strings) {
            Debug.waitForDebugger();

            conn=connectionclass(un,pass,db,ip);
var="ConnectionClass simon";
                try {
                    Statement st = null;
                    if (conn != null) {
                        st = conn.createStatement();
                    }

                    ResultSet rs=st.executeQuery("select*from Lotes");
                    if (rs.next())
                        var =rs.getString(3);
                    else
                        var="nel";
                } catch (SQLException e) {
                    e.printStackTrace();
                }



            return var;
        }

    }

    public Connection connectionclass(String user,String password,String database,String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL=null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://"+server+";databaseName="+database + ";user="+user+";password="+password +";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection ;

    }
}

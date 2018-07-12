package com.app.salvador.uniforme;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
    @SuppressLint("RestrictedApi")
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
        FloatingActionButton fabVolver= (FloatingActionButton)findViewById(R.id.fabVolver);
        fabVolver.setVisibility(getIntent().getIntExtra(("volver"),0));

        //fabVolver.setVisibility(if(getIntent().getBooleanExtra("volver"),false)? 0 : 4);
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
                if (c.getErrorHubo())
                {
                    Toast.makeText(getApplicationContext(),c.getErrorMensaje(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    setResult(1);
                    finish();
                }

            }

        });

    }
    /*
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
    */
}

package com.app.salvador.uniforme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static final int RESULTADO=1;
    static SharedPreferences preferences ;
    public Integer pantalla=0;
    static final int PANTALLA_SERVIDOR=1;
    static final int PANTALLA_SERVIDOR2=2;
    static final int PANTALLA_INICIAR_SESION=3;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (preferences.getString("BDservidor","Vacio").equals( "Vacio"))
        {
            preferences.edit().putBoolean("SesionIniciada",false).apply();
            pantalla=PANTALLA_SERVIDOR;
            startActivityForResult(new Intent(getBaseContext(),ConexionBD.class ).putExtra("volver",4),RESULTADO);
        }
        else
        {
            VelificarSesion();

            TextView tv = (TextView) findViewById(R.id.textoejemplo ) ;
            tv.setText( preferences.getString("BDservidor","Vacio")+"\n");
            tv.setText(tv.getText() +preferences.getString("BDnombre","Vacio")+"\n");
            tv.setText(tv.getText() +preferences.getString("BDusuario","Vacio")+"\n");
            tv.setText(tv.getText() +preferences.getString("BDcontrasena","Vacio")+"\n");
        }



    }

    private void VelificarSesion() {
        if (preferences.getBoolean("SesionIniciada",false))
        {
            startActivity(new Intent(getBaseContext(),Login.class));
        }
        else
        {

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId())
        {
            case R.id.servidor:
                pantalla=pantalla=PANTALLA_SERVIDOR2;

                startActivity(new Intent(getBaseContext(),ConexionBD.class ).putExtra("volver",0));
                break;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (item.getItemId())
        {
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        switch (pantalla)
        {
            case PANTALLA_SERVIDOR:
                if (requestCode == RESULTADO) {
                    // Make sure the request was successful
                    TextView tv = (TextView) findViewById(R.id.textoejemplo ) ;
                    tv.setText( preferences.getString("BDservidor","Vacio")+"\n");
                    tv.setText(tv.getText() +preferences.getString("BDnombre","Vacio")+"\n");
                    tv.setText(tv.getText() +preferences.getString("BDusuario","Vacio")+"\n");
                    tv.setText(tv.getText() +preferences.getString("BDcontrasena","Vacio")+"\n");
                }
                break;
        }

    }
}

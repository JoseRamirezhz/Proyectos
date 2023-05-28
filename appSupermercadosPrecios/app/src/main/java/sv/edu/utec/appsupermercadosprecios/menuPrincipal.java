package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._2_activity_menu_principal);
    }

    public void irAListaSuper(View v) {
        Intent i = new Intent(getApplicationContext(),_3_listaSupermercados.class);
        startActivity(i);

    }

    public void irARegistro(View v) {
        Intent i = new Intent(getApplicationContext(),_6_crearUsuario.class);
        startActivity(i);

    }
}
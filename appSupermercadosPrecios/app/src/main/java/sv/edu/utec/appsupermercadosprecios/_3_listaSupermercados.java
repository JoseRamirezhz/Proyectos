package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class _3_listaSupermercados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3_lista_supermercados);
    }

    public void irAPrecios(View v) {
        Intent i = new Intent(getApplicationContext(),_4_listaPrecios.class);
        startActivity(i);

    }
}
package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.checkerframework.common.subtyping.qual.Bottom;

public class _3_listaSupermercados extends AppCompatActivity {
    Button btnDolarC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3_lista_supermercados);
        btnDolarC = findViewById(R.id.btnDolarcity);

        btnDolarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAProductosDolar();
            }
        });

    }

    public void irAPrecios(View v) {
        Intent i = new Intent(getApplicationContext(),_4_listaPrecios.class);
        startActivity(i);

    }

    public void irAProductosDolar() {
        Intent i = new Intent(getApplicationContext(),ListadoProductosTiendas.class);
        startActivity(i);
    }
}
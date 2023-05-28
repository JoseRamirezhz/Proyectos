package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIngresar);
    }

        public void irAMenu(View v) {
            Intent i = new Intent(getApplicationContext(),menuPrincipal.class);
            startActivity(i);

        }

    public void irARegistro(View v) {
        Intent i = new Intent(getApplicationContext(),_6_crearUsuario.class);
        startActivity(i);

    }

}
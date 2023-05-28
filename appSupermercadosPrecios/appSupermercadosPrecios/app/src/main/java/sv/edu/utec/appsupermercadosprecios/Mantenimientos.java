package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Mantenimientos extends AppCompatActivity {

    Button  btnMtoSuper, btnMtoProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimientos);

        btnMtoSuper = findViewById(R.id.btnSuperMercado);
        btnMtoProd = findViewById(R.id.btnProductos);

        btnMtoSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //Toast.makeText(Mantenimientos.this,"¡Session Cerrada!", Toast.LENGTH_SHORT).show();
                irMtoSuper();
            }
        });

        btnMtoProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //Toast.makeText(Mantenimientos.this,"¡Session Cerrada!", Toast.LENGTH_SHORT).show();
                irMtoProductos();
            }
        });
    }

    public  void irMtoSuper(){
        Intent i = new Intent(getApplicationContext(),Mantenimiento_Productos.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Evita que se creen nuevas actividades
        startActivity(i);
    }

    public  void irMtoProductos(){
        //Intent i = new Intent(getApplicationContext(),Mantenimiento_Productos.class);
        Intent i = new Intent(getApplicationContext(),MantenimientosProductos.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Evita que se creen nuevas actividades
        startActivity(i);
    }

}
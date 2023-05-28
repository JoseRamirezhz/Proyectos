package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class menuPrincipal extends AppCompatActivity {

    Button btbCerrarSession, btnManto, btnSuper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_menu_principal);

        btbCerrarSession = findViewById(R.id.btnCerrarSesion);
        btnSuper = findViewById(R.id.btnSuper);
        btnManto = findViewById(R.id.btnConfig);

        btbCerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //Toast.makeText(menuPrincipal.this,"¡Session Cerrada!", Toast.LENGTH_SHORT).show();
                goLogin();
            }
        });

        btnSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //Toast.makeText(menuPrincipal.this,"¡Session Cerrada!", Toast.LENGTH_SHORT).show();
                irSuper();
            }
        });

        btnManto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //Toast.makeText(menuPrincipal.this,"¡Session Cerrada!", Toast.LENGTH_SHORT).show();
                irMantenimientos();
            }
        });

    }

    // android:onClick="irAListaSuper"  btnSuper
    public void irAListaSuper(View v) {
        Intent i = new Intent(getApplicationContext(),_3_listaSupermercados.class);
        startActivity(i);

    }

    //android:onClick="irARegistro"  btnConfig
    public void irARegistro(View v) {
        Intent i = new Intent(getApplicationContext(),_6_crearUsuario.class);
        startActivity(i);

    }

    public void goLogin() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Evita que se creen nuevas actividades
        startActivity(i);
    }

    public void irMantenimientos() {
        Intent i = new Intent(getApplicationContext(),Mantenimientos.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Evita que se creen nuevas actividades
        startActivity(i);
    }

    public  void irSuper(){
        Intent i = new Intent(getApplicationContext(),_3_listaSupermercados.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Evita que se creen nuevas actividades
        startActivity(i);
    }


}
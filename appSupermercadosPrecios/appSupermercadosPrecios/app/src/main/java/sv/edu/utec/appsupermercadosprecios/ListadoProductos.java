package sv.edu.utec.appsupermercadosprecios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.utec.appsupermercadosprecios.modelos.Producto;

public class ListadoProductos extends AppCompatActivity {

    private List<Producto> listaProductos = new ArrayList<Producto>();
    ArrayAdapter<Producto> arrayAdapter;

    ListView listvProducto;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos);
        inicializarFirebase();

        listvProducto = findViewById(R.id.lvDatosProductos);

        listarDatos();
    }

    private void listarDatos() {
        databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProductos.clear();
                for(DataSnapshot objSnap : snapshot.getChildren()){
                    Producto p = objSnap.getValue(Producto.class);
                    listaProductos.add(p);

                    arrayAdapter = new ArrayAdapter<Producto>(ListadoProductos.this, android.R.layout.simple_list_item_1, listaProductos);
                    listvProducto.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


}
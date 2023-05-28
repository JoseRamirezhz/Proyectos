package sv.edu.utec.appsupermercadosprecios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.utec.appsupermercadosprecios.Adapter.ProductosAdapter;
import sv.edu.utec.appsupermercadosprecios.modelos.Producto;
import sv.edu.utec.appsupermercadosprecios.modelos.Productos;

public class MantenimientosProductos extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<Productos> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimientos_productos);

        fab = findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MantenimientosProductos.this,UploadActivity.class);
                startActivity(intent);
            }
        });
    }
}
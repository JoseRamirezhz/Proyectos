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
import sv.edu.utec.appsupermercadosprecios.modelos.Productos;

public class ListadoProductosTiendas extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<Productos> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos_tiendas);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoProductosTiendas.this,UploadActivity.class);
                startActivity(intent);
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListadoProductosTiendas.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(ListadoProductosTiendas.this);
        builder.setCancelable(false);
        builder.setView(R.layout.propress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        ProductosAdapter adapter = new ProductosAdapter(ListadoProductosTiendas.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Productos");
        dialog.show();;

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    Productos dataProd = itemSnapshot.getValue(Productos.class);
                    dataList.add(dataProd);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

    }
}
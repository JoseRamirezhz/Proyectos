package sv.edu.utec.appsupermercadosprecios.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sv.edu.utec.appsupermercadosprecios.Adapter.GaleriaAdapter;
import sv.edu.utec.appsupermercadosprecios.R;
import sv.edu.utec.appsupermercadosprecios.modelos.Producto;

public class VerGaleriaActivity extends AppCompatActivity {

    RecyclerView rv_galeria;
    GaleriaAdapter adapter;
    ArrayList<Producto> galeriaArrayList;
    LinearLayoutManager mlayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_galeria);

        mlayoutManager = new LinearLayoutManager(this);
        mlayoutManager.setReverseLayout(true);
        mlayoutManager.setStackFromEnd(true);
        rv_galeria = findViewById(R.id.rv);

        galeriaArrayList = new ArrayList<>();;
        adapter = new GaleriaAdapter(galeriaArrayList,this);
        rv_galeria.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Fotos subidas");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    galeriaArrayList.removeAll(galeriaArrayList);
                    for(DataSnapshot snaps: snapshot.getChildren()){
                        Producto gal = snaps.getValue(Producto.class);
                        galeriaArrayList.add(gal);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }// fin del oncreate


}
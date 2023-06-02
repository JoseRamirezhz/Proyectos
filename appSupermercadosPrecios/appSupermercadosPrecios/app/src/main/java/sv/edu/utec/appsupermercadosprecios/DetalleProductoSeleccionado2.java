package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetalleProductoSeleccionado2 extends AppCompatActivity {

    TextView tvNomT, tvNomD,tvCodD,tvDesD,tvPreD;
    FloatingActionButton deleteButton;

    String key = "";
    String img_url = "";
    ImageView imgProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto_seleccionado2);

        tvNomT = findViewById(R.id.tvNomProd2);
        tvNomD = findViewById(R.id.tvNomProdD2);
        tvCodD = findViewById(R.id.tvCodProd2);
        tvDesD = findViewById(R.id.tvDescProd2);
        tvPreD = findViewById(R.id.tvPrecProd2);
        imgProd = findViewById(R.id.imgProd2);
        //deleteButton = findViewById(R.id.deleteButton2);



        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            tvNomT.setText(bundle.getString("Nombre2"));
            tvNomD.setText(bundle.getString("Nombre"));
            tvCodD.setText(bundle.getString("Codigo"));
            tvDesD.setText(bundle.getString("Descripcion"));
            tvPreD.setText(bundle.getString("Precio"));
            //key = bundle.getString("key");
            //img_url = bundle.getString("Imagen");
            Glide.with(this).load(bundle.getString("Imagen")).into(imgProd);
        }

/*
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Productos");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(img_url);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(tvCodD.getText().toString()).removeValue();
                        Toast.makeText(DetalleProductoSeleccionado2.this, "Producto Eliminado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MantenimientosProductos.class));
                        finish();
                    }
                });
            }
        });*/
    }
}
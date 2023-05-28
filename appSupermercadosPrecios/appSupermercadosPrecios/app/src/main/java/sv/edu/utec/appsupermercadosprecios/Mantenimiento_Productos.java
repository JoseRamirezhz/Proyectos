package sv.edu.utec.appsupermercadosprecios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import sv.edu.utec.appsupermercadosprecios.modelos.Producto;

public class Mantenimiento_Productos extends AppCompatActivity {

    ImageView foto;
    Button btnGuarda, btnSeleccionar, btnVerGal;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;
    EditText edtCodP,edtNomP,edtDescP,edtPrecP;
    private List<Producto> listaProductos = new ArrayList<Producto>();
    ArrayAdapter<Producto> arrayAdapter;
    ListView listvProducto;

    Bitmap thum_bitmap = null;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Producto productosSelected;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_productos);

        foto = findViewById(R.id.img_foto);
        btnSeleccionar = findViewById(R.id.btnSelecFoto);
        btnVerGal = findViewById(R.id.btnGaleria);
        btnGuarda = findViewById(R.id.btnGuardar);
        edtCodP = findViewById(R.id.edtCodProducto);
        edtNomP = findViewById(R.id.edtNomProducto);
        edtDescP = findViewById(R.id.edtDescProducto);
        edtPrecP = findViewById(R.id.edtPrecioProducto);
        listvProducto = findViewById(R.id.lvProdMto);

        inicializarFirebase();

        btnSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnVerGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        listarDatos();
        listvProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productosSelected = (Producto) adapterView.getItemAtPosition(i);
                edtCodP.setText(productosSelected.getCod_producto());
                edtNomP.setText(productosSelected.getNom_producto());
                edtDescP.setText(productosSelected.getDesc_producto());
                edtPrecP.setText(productosSelected.getPrecio());
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true); incorrcta
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_productos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String codigo = edtCodP.getText().toString();
        String nombre = edtNomP.getText().toString();
        String descripcion = edtDescP.getText().toString();
        String precio = edtPrecP.getText().toString();

        switch (item.getItemId()){
            case R.id.add_icon:{
                if(codigo.equals("")||nombre.equals("")||descripcion.equals("")||precio.equals("")){
                    Validacion();
                }else {
                    Producto p = new Producto();
                    p.setUid(UUID.randomUUID().toString());
                    p.setCod_producto(codigo);
                    p.setNom_producto(nombre);
                    p.setDesc_producto(descripcion);
                    p.setPrecio(precio);
                    databaseReference.child("Producto").child(p.getUid()).setValue(p);
                    Toast.makeText(this, "Producto Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }
            case R.id.save_icono: {
                Producto p = new Producto();
                p.setUid(productosSelected.getUid());
                p.setCod_producto(edtCodP.getText().toString().trim());
                p.setNom_producto(edtNomP.getText().toString().trim());
                p.setDesc_producto(edtDescP.getText().toString().trim());
                p.setPrecio(edtPrecP.getText().toString().trim());
                databaseReference.child("Producto").child(p.getUid()).setValue(p);
                Toast.makeText(this, "Producto Actualizado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.delete_icono: {
                Producto p = new Producto();
                p.setUid(productosSelected.getUid());
                databaseReference.child("Producto").child(p.getUid()).removeValue();
                Toast.makeText(this, "Producto Eliminado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            default:break;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }

    private void limpiarCajas() {
        edtCodP.setText("");
        edtNomP.setText("");
        edtDescP.setText("");
        edtPrecP.setText("");
    }

    private void Validacion() {
        String codigo = edtCodP.getText().toString();
        String nombre = edtNomP.getText().toString();
        String descripcion = edtDescP.getText().toString();
        String precio = edtPrecP.getText().toString();

        if(codigo.equals("")){
            edtCodP.setError("Requerido");
        } else if (nombre.equals("")) {
            edtNomP.setError("Requerido");
        } else if (descripcion.equals("")) {
            edtDescP.setError("Requerido");
        } else if (precio.equals("")) {
            edtPrecP.setError("Requerido");
        }
    }

    private void listarDatos() {
        databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProductos.clear();
                for(DataSnapshot objSnap : snapshot.getChildren()){
                    Producto p = objSnap.getValue(Producto.class);
                    listaProductos.add(p);

                    arrayAdapter = new ArrayAdapter<Producto>(Mantenimiento_Productos.this, android.R.layout.simple_list_item_1, listaProductos);
                    listvProducto.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
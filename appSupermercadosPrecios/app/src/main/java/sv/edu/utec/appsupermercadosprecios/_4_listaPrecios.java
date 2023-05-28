package sv.edu.utec.appsupermercadosprecios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

// -------------------------------------------------------------------------
//AQUI ES DONDE SE DEBE LLENAR EL LIST VIEW CON LOS PRODUCTOS OBTENIDOS DESDE LA BASE DE DATOS
// -------------------------------------------------------------------------

public class _4_listaPrecios extends AppCompatActivity {
    RecyclerView recyclerViewProductos;
    adaptador_producto adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4_lista_precios);

        recyclerViewProductos = findViewById(R.id.rvProducto);
        //recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProductos.setLayoutManager(new GridLayoutManager(this,2)); //muestra el recycleview en grid de 2 columnas, el 2 es la cantidad de columnas
        adaptador = new adaptador_producto(this,getListado());
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "seleccionaste: " +
                        getListado().get(recyclerViewProductos.getChildAdapterPosition(view)).getNombreProducto(),
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),_5_listaProductosPrecioConEspecifico.class);
                i.putExtra("nombreProducto", getListado().get(recyclerViewProductos.getChildAdapterPosition(view)).getNombreProducto());
                i.putExtra("DescripcionProd", getListado().get(recyclerViewProductos.getChildAdapterPosition(view)).getDescripcionProduct());
                i.putExtra("PrecioProd", getListado().get(recyclerViewProductos.getChildAdapterPosition(view)).getPrecioProducto());
                i.putExtra("MedidaProd", getListado().get(recyclerViewProductos.getChildAdapterPosition(view)).getMedida());

                // Convertir la imagen a un ByteArray
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getListado().get(recyclerViewProductos.getChildAdapterPosition(view)).getImgProducto());
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                // agregar el ByteArray como un extra
                i.putExtra("imagenProd", byteArray);

                startActivity(i);
            }
        });

        recyclerViewProductos.setAdapter(adaptador);


        recyclerViewProductos.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {

                Toast.makeText(getApplicationContext(),"Selecionar",Toast.LENGTH_SHORT).show();

                return false;
            }
        });



    }


    private ArrayList<modelo_productos> getListado(){
        ArrayList<modelo_productos> modelos=new ArrayList<>();
        modelo_productos modelista = new modelo_productos();
        modelista.setNombreProducto("Fresas"); //para texto
        modelista.setPrecioProducto("$3.995"); //para texto
        modelista.setMedida("Libra"); //para texto
        modelista.setImgProducto(R.drawable.fresa128px); //para imagen
        modelista.setDescripcionProduct("Descripcion del producto 1"); //para texto
        modelos.add(modelista);


        modelista = new modelo_productos();
        modelista.setNombreProducto("Manzana"); //para texto
        modelista.setPrecioProducto("$2.99"); //para texto
        modelista.setMedida("x Unidad"); //para texto
        modelista.setImgProducto(R.drawable.apple128px); //para imagen
        modelista.setDescripcionProduct("Descripcion del producto 2"); //para texto
        modelos.add(modelista);

        modelista = new modelo_productos();
        modelista.setNombreProducto("Barra de Chocolate"); //para texto
        modelista.setPrecioProducto("$3.99"); //para texto
        modelista.setMedida("x Unidad"); //para texto
        modelista.setImgProducto(R.drawable.barra_chocolate128px); //para imagen
        modelista.setDescripcionProduct("Descripcion del producto 3"); //para texto
        modelos.add(modelista);

        modelista = new modelo_productos();
        modelista.setNombreProducto("Manzana Verde"); //para texto
        modelista.setPrecioProducto("$4.99"); //para texto
        modelista.setMedida("x Unidad"); //para texto
        modelista.setImgProducto(R.drawable.manzana_verde128px); //para imagen
        modelista.setDescripcionProduct("Descripcion del producto 4"); //para texto
        modelos.add(modelista);

        return modelos;
    }
}
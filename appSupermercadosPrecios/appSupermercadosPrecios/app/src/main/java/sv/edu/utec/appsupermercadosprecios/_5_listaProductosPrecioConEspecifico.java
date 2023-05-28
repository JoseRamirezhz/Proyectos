package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


// -------------------------------------------------------------------------
//AQUI ES DONDE OBTENGO LOS DATOS DEL ACTIVITY _4_listaPrecios
// -------------------------------------------------------------------------


public class _5_listaProductosPrecioConEspecifico extends AppCompatActivity {
TextView txtvTitulo, txtvDescripcion,txtvprecio,txtvMedida;
ImageView imgProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5_lista_productos_precio_con_especifico);

        imgProducto = findViewById(R.id.imgvProducto);
        txtvTitulo = findViewById(R.id.tvTitulo2);
        txtvDescripcion = findViewById(R.id.tvDescripcion);
        txtvprecio = findViewById(R.id.tvPrecio);
        txtvMedida = findViewById(R.id.tvMedida2);

        //recibo la informacion del intent del activity _4_listaPrecios
        Bundle bundle = getIntent().getExtras();
        String nombreprod = bundle.getString("nombreProducto");
        String DescripcionProd = bundle.getString("DescripcionProd");
        String PrecioProd = bundle.getString("PrecioProd");
        String imagenProd = bundle.getString("imagenProd");
        String MedidaProd = bundle.getString("MedidaProd");

        // Obtener el ByteArray del Intent
        byte[] byteArray = getIntent().getByteArrayExtra("imagenProd");
        // Convertir el ByteArray a un Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        // Mostrar el Bitmap en un ImageView

        imgProducto.setImageBitmap(bitmap);
        txtvTitulo.setText(nombreprod);
        txtvDescripcion.setText(DescripcionProd);
        txtvprecio.setText(PrecioProd);
        txtvMedida.setText(MedidaProd);
    }
}
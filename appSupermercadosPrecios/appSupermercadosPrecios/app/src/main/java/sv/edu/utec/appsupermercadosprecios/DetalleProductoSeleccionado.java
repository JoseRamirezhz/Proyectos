package sv.edu.utec.appsupermercadosprecios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetalleProductoSeleccionado extends AppCompatActivity {

    TextView tvNomT, tvNomD,tvCodD,tvDesD,tvPreD;
    ImageView imgProd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto_seleccionado);

        tvNomT = findViewById(R.id.tvNomProd);
        tvNomD = findViewById(R.id.tvNomProdD);
        tvCodD = findViewById(R.id.tvCodProd);
        tvDesD = findViewById(R.id.tvDescProd);
        tvPreD = findViewById(R.id.tvPrecProd);
        imgProd = findViewById(R.id.imgProd);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            tvNomT.setText(bundle.getString("Nombre2"));
            tvNomD.setText(bundle.getString("Nombre"));
            tvCodD.setText(bundle.getString("Codigo"));
            tvDesD.setText(bundle.getString("Descripcion"));
            tvPreD.setText(bundle.getString("Precio"));
            Glide.with(this).load(bundle.getString("Imagen")).into(imgProd);
        }
    }
}
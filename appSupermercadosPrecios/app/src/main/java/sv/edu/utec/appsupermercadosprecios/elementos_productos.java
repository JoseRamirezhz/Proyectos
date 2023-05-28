package sv.edu.utec.appsupermercadosprecios;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class elementos_productos extends RecyclerView.ViewHolder{//ViewHolder permite poner una clase sobre otra
    static ImageView imgProducto;
    static TextView tvTituloProducto,tvPrecioProducto,tvDescripcion1,tvMedida;
    public elementos_productos(@NonNull View itemView) {
            super(itemView);

            imgProducto = itemView.findViewById(R.id.imvPelicula);
            tvTituloProducto = itemView.findViewById(R.id.tvTitulo);
            tvPrecioProducto = itemView.findViewById(R.id.tvPrecioo);
            tvDescripcion1 = itemView.findViewById(R.id.tvDescripcion1);
            tvMedida = itemView.findViewById(R.id.tvMedida);
            }
}

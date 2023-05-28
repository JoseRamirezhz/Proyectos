package sv.edu.utec.appsupermercadosprecios.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import sv.edu.utec.appsupermercadosprecios.DetalleProductoSeleccionado;
import sv.edu.utec.appsupermercadosprecios.R;
import sv.edu.utec.appsupermercadosprecios.modelos.Productos;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosHolder>{

    private Context context;
    private List<Productos> dataList;

    public ProductosAdapter(Context context, List<Productos> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ProductosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_productos_items, parent, false);

        return new ProductosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getFoto()).into(holder.rectImage);
        holder.recTitle.setText(dataList.get(position).getNom_producto());
        holder.recNom.setText(dataList.get(position).getPrecio());
        holder.recDesc.setText(dataList.get(position).getDesc_producto());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalleProductoSeleccionado.class);
                intent.putExtra("Imagen", dataList.get(holder.getAdapterPosition()).getFoto());
                intent.putExtra("Codigo", dataList.get(holder.getAdapterPosition()).getCod_producto());
                intent.putExtra("Nombre", dataList.get(holder.getAdapterPosition()).getNom_producto());
                intent.putExtra("Nombre2", dataList.get(holder.getAdapterPosition()).getNom_producto());
                intent.putExtra("Descripcion", dataList.get(holder.getAdapterPosition()).getDesc_producto());
                intent.putExtra("Precio", dataList.get(holder.getAdapterPosition()).getPrecio());


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
class ProductosHolder extends RecyclerView.ViewHolder{

    ImageView rectImage;
    TextView recTitle,recNom,recDesc;
    CardView recCard;

    public ProductosHolder(@NonNull View itemView) {
        super(itemView);

        rectImage = itemView.findViewById(R.id.recImage);
        recCard =itemView.findViewById(R.id.recCard);
        recTitle = itemView.findViewById(R.id.recTitle);
        recNom = itemView.findViewById(R.id.recNom);
        recDesc = itemView.findViewById(R.id.recDesc);
    }
}
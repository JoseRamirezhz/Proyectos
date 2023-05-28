package sv.edu.utec.appsupermercadosprecios.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import sv.edu.utec.appsupermercadosprecios.R;
import sv.edu.utec.appsupermercadosprecios.modelos.Producto;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.FotosViewHolder>{

    List<Producto> galeriaList;
    Context context;

    public GaleriaAdapter(List<Producto> galeriaList, Context context) {
        this.galeriaList = galeriaList;
        this.context = context;
    }

    @NonNull
    @Override
    public GaleriaAdapter.FotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_galeria, parent, false);
        FotosViewHolder holder = new FotosViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaAdapter.FotosViewHolder holder, int position) {
        Producto gal = galeriaList.get(position);

        Picasso.with(context).load(gal.getFoto()).into(holder.img_fot, new Callback() {
            @Override
            public void onSuccess() { // ha sido cargada existosa
                holder.progressBar.setVisibility(View.GONE);
                holder.img_fot.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                Toast.makeText(context,"Tienes un error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return galeriaList.size();
    }

    public class FotosViewHolder extends RecyclerView.ViewHolder {
        TextView tv_titulo;
        ImageView img_fot;
        ProgressBar progressBar;
        public FotosViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_titulo = itemView.findViewById(R.id.tv_titulo);
            img_fot = itemView.findViewById(R.id.img_foto);
            progressBar = itemView.findViewById(R.id.p_bar_galeria);


        }
    }
}

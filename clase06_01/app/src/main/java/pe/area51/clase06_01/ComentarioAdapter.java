package pe.area51.clase06_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComentarioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Comentario> lista;

    public ComentarioAdapter(Context context, ArrayList<Comentario> lista) {
        this.context = context;
        this.lista = lista;
    }

    class ComentarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCuerpo, tvEmail;
        //CardView contenedor;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCuerpo = itemView.findViewById(R.id.tvCuerpo);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            //contenedor = itemView.findViewById(R.id.contenedor);
        }

        public void bind(int position) {
            Comentario comentario = lista.get(position);
            tvNombre.setText(comentario.getName());
            tvCuerpo.setText(comentario.getBody());
            tvEmail.setText(comentario.getEmail());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ComentarioViewHolder comentarioViewHolder = (ComentarioViewHolder) holder;
        comentarioViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

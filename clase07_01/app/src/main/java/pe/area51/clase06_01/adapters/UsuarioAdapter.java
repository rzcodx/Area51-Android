package pe.area51.clase06_01.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.area51.clase06_01.R;
import pe.area51.clase06_01.net.response.usuario.Usuario;
import pe.area51.clase06_01.activities.DetalleActivity;

public class UsuarioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Usuario> lista;

    public UsuarioAdapter(Context context, ArrayList<Usuario> lista) {
        this.context = context;
        this.lista = lista;
    }

    class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDireccion, tvCeluar;
        CardView contenedor;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvAddress);
            tvCeluar = itemView.findViewById(R.id.tvCelular);
            contenedor = itemView.findViewById(R.id.contenedor);
        }

        public void bind(int position) {
            Usuario usuario = lista.get(position);
            tvNombre.setText(usuario.getName());
            tvCeluar.setText(usuario.getPhone());
            tvDireccion.setText(usuario.getAddress().getCity());
            contenedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetalleActivity.class);
                    intent.putExtra("usuario", usuario);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UsuarioViewHolder usuarioViewHolder = (UsuarioViewHolder) holder;
        usuarioViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

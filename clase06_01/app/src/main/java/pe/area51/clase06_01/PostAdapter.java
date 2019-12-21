package pe.area51.clase06_01;

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

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Post> lista;

    public PostAdapter(Context context, ArrayList<Post> lista) {
        this.context = context;
        this.lista = lista;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvCuerpo;
        CardView contenedor;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvCuerpo = itemView.findViewById(R.id.tvCuerpo);
            contenedor = itemView.findViewById(R.id.contenedor);
        }

        public void bind(int position) {
            Post post = lista.get(position);
            tvTitulo.setText(post.getTitle());
            tvCuerpo.setText(post.getBody());
            contenedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ComentarioActivity.class);
                    intent.putExtra("post", post);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostViewHolder postViewHolder = (PostViewHolder) holder;
        postViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

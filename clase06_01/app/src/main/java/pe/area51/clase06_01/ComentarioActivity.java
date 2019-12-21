package pe.area51.clase06_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentarioActivity extends AppCompatActivity {
    private TextView tvTitulo, tvCuerpo;
    private RecyclerView rvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvCuerpo = findViewById(R.id.tvCuerpo);
        rvDatos = findViewById(R.id.rvDatos);

        RetrofitServicios servicios = RetrofitConfiguration.obtenerConfiguracion()
                .create(RetrofitServicios.class);

        Post post = getIntent().getParcelableExtra("post");

        tvTitulo.setText(post.getTitle());
        tvCuerpo.setText(post.getBody());

        int postId = getIntent().getIntExtra("postId", post.getId());

        Call<ArrayList<Comentario>> call = servicios.obtenerComentariosPorPost(postId);

        call.enqueue(new Callback<ArrayList<Comentario>>() {
            @Override
            public void onResponse(Call<ArrayList<Comentario>> call, Response<ArrayList<Comentario>> response) {
                Log.d("clase_06_comentarios", response.toString());

                ComentarioAdapter adapter = new ComentarioAdapter(ComentarioActivity.this, response.body());
                rvDatos.setLayoutManager(new LinearLayoutManager(ComentarioActivity.this));
                rvDatos.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Comentario>> call, Throwable t) {

            }
        });

    }
}

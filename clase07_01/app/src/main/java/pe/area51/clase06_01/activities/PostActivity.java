package pe.area51.clase06_01.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.RealmResults;
import pe.area51.clase06_01.Reusable;
import pe.area51.clase06_01.database.MetodoSQL;
import pe.area51.clase06_01.database.posts.PostEntidad;
import pe.area51.clase06_01.net.response.post.Post;
import pe.area51.clase06_01.adapters.PostAdapter;
import pe.area51.clase06_01.R;
import pe.area51.clase06_01.net.RetrofitConfiguration;
import pe.area51.clase06_01.net.RetrofitServicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    private RecyclerView rvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        rvDatos = findViewById(R.id.rvDatos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Posts");

        MetodoSQL metodoSQL = new MetodoSQL();

        int userId = getIntent().getIntExtra("id", 0);

        if (Reusable.isOnline()) {
            RetrofitServicios servicios = RetrofitConfiguration.obtenerConfiguracion()
                    .create(RetrofitServicios.class);

            Call<ArrayList<Post>> call = servicios.obtenerPostPorUsuario(userId);
            call.enqueue(new Callback<ArrayList<Post>>() {
                @Override
                public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                    ArrayList<Post> lista = response.body();

                    if (lista != null && lista.size() > 0) {
                        for (Post post : lista) {
                            PostEntidad entidad = new PostEntidad();
                            entidad.setUserId(userId);
                            entidad.setTitle(post.getTitle());
                            entidad.setBody(post.getBody());
                            metodoSQL.registrarPost(entidad);
                        }
                    }

                    PostAdapter adapter = new PostAdapter(PostActivity.this, response.body());
                    rvDatos.setLayoutManager(new LinearLayoutManager(PostActivity.this));
                    rvDatos.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                    Toast.makeText(PostActivity.this, "Error - " + t.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                }
            });
        } else {
            ArrayList<Post> lista = new ArrayList<>();
            RealmResults<PostEntidad> posts = metodoSQL.obtenerPosts(userId);

            if (posts != null && posts.size() > 0) {
                for (PostEntidad entidad : posts) {
                    Post post = new Post();
                    post.setUserId(entidad.getUserId());
                    post.setTitle(entidad.getTitle());
                    post.setBody(entidad.getBody());
                    lista.add(post);
                }
            }

            PostAdapter adapter = new PostAdapter(PostActivity.this, lista);
            rvDatos.setLayoutManager(new LinearLayoutManager(PostActivity.this));
            rvDatos.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

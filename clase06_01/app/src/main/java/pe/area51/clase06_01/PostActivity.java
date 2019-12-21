package pe.area51.clase06_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

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

        RetrofitServicios servicios = RetrofitConfiguration.obtenerConfiguracion()
                .create(RetrofitServicios.class);

        int userId = getIntent().getIntExtra("id", 0);

        Call<ArrayList<Post>> call = servicios.obtenerPostPorUsuario(userId);
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                Log.d("clase_06_posts", response.toString());

                PostAdapter adapter = new PostAdapter(PostActivity.this, response.body());
                rvDatos.setLayoutManager(new LinearLayoutManager(PostActivity.this));
                rvDatos.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        });
    }
}

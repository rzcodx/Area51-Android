package pe.area51.clase06_01.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import pe.area51.clase06_01.R;
import pe.area51.clase06_01.adapters.AlbumAdapter;
import pe.area51.clase06_01.net.RetrofitConfiguration;
import pe.area51.clase06_01.net.RetrofitServicios;
import pe.area51.clase06_01.net.response.album.Album;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        rvDatos = findViewById(R.id.rvDatos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Albumes");

        int usuarioId = getIntent().getIntExtra("id", 0);
        RetrofitServicios servicios = RetrofitConfiguration.obtenerConfiguracion()
                .create(RetrofitServicios.class);
        Call<ArrayList<Album>> call = servicios.obtenerAlbumPorUsuario(usuarioId);
        call.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                adapter = new AlbumAdapter(AlbumActivity.this, response.body());
                rvDatos.setLayoutManager(new LinearLayoutManager(AlbumActivity.this));
                rvDatos.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                Toast.makeText(AlbumActivity.this, "Error - " + t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
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

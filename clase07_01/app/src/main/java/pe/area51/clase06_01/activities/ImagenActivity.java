package pe.area51.clase06_01.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import pe.area51.clase06_01.R;
import pe.area51.clase06_01.adapters.ImagenAdapter;
import pe.area51.clase06_01.net.RetrofitConfiguration;
import pe.area51.clase06_01.net.RetrofitServicios;
import pe.area51.clase06_01.net.response.foto.Foto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagenActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private ImagenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        rvDatos = findViewById(R.id.rvDatos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Fotos");

        int albumId = getIntent().getIntExtra("albumId", 0);

        RetrofitServicios servicios = RetrofitConfiguration.obtenerConfiguracion()
                .create(RetrofitServicios.class);
        Call<ArrayList<Foto>> call = servicios.obtenerfotosPorAlbum(albumId);
        call.enqueue(new Callback<ArrayList<Foto>>() {
            @Override
            public void onResponse(Call<ArrayList<Foto>> call, Response<ArrayList<Foto>> response) {
                ArrayList<Foto> data = response.body();
                adapter = new ImagenAdapter(ImagenActivity.this, data);
                rvDatos.setLayoutManager(new GridLayoutManager(ImagenActivity.this, 2));
                rvDatos.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Foto>> call, Throwable t) {
                Toast.makeText(ImagenActivity.this, "Error - " + t.getMessage(), Toast.LENGTH_SHORT)
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

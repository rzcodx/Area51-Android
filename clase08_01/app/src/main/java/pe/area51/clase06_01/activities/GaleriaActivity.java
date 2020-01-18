package pe.area51.clase06_01.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import pe.area51.clase06_01.R;
import pe.area51.clase06_01.adapters.GaleriaAdapter;
import pe.area51.clase06_01.adapters.ImagenAdapter;
import pe.area51.clase06_01.net.response.foto.Foto;

public class GaleriaActivity extends AppCompatActivity {
    private GaleriaAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Galería");

        viewPager = findViewById(R.id.viewPager);
        adapter = new GaleriaAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //ArrayList<Foto> lista = getIntent().getParcelableArrayListExtra("lista");
        ArrayList<Foto> lista = ImagenAdapter.lista;

        adapter.agregarLista(lista);
        int position = getIntent().getIntExtra("position", 0);
        viewPager.setCurrentItem(position);
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

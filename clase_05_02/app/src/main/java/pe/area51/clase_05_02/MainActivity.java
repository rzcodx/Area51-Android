package pe.area51.clase_05_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SentenciaSQL sentenciaSQL = new SentenciaSQL();

//        Persona persona = new Persona();
//        persona.setNombre("Raul");
//        persona.setApellido("Falcon Najarro");
//        persona.setEdad("20");
//        persona.setDireccion("Av Lima");
//
//        sentenciaSQL.registrar(persona);

//        RealmResults<Persona> lista = sentenciaSQL.obtener();
//        for (Persona item : lista) {
//            sentenciaSQL.eliminar(item);
//        }

//        RealmResults<Persona> lista2 = sentenciaSQL.obtener();
//        for (Persona item : lista2) {
//            Log.d("tag_clase05_02", item.getId() + "\t" + item.getFullName());
//        }

        rvDatos = findViewById(R.id.rvDatos);
        fabAgregar = findViewById(R.id.fabAgregar);

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        SentenciaSQL sentenciaSQL = new SentenciaSQL();
        PersonaAdapter adapter = new PersonaAdapter(
                MainActivity.this, sentenciaSQL.obtener());
        rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        /*
         * También se puede usar con el GridLayoutManager, pasandole el contexto
         * y el número de columas que tendrá
         */
        //rvDatos.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        rvDatos.setAdapter(adapter);
    }
}

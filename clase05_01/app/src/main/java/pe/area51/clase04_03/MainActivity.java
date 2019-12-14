package pe.area51.clase04_03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SentenciaSQL sentenciaSQL = new SentenciaSQL(this);
        //sentenciaSQL.registrar("Raul 5", "Falcon 5", "20", "Calle 3");
        //sentenciaSQL.actualizar(4, "Raul 4", "Falcon 4", "20", "Calle 3");
        //sentenciaSQL.eliminar(5);

        /*
        for (Persona persona: sentenciaSQL.obtenerDatos()) {
            Log.d("clase04",
                    "Nombre: " + persona.getNombre() + "\tApellido: " + persona.getApellido());
        }
        */

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

        SentenciaSQL sentenciaSQL = new SentenciaSQL(MainActivity.this);
        PersonaAdapter adapter = new PersonaAdapter(
                MainActivity.this, sentenciaSQL.obtenerDatos());
        rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        /*
        * También se puede usar con el GridLayoutManager, pasandole el contexto
        * y el número de columas que tendrá
        */
        //rvDatos.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        rvDatos.setAdapter(adapter);
    }
}

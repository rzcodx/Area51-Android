package pe.area51.clase03_02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pe.area51.clase03_02.R;
import pe.area51.clase03_02.adapters.ListadoAdapter;
import pe.area51.clase03_02.models.Usuario;

public class ListadoActivity extends AppCompatActivity {
    private Button nuevo;
    private ListView lista;
    public static ArrayList<Usuario> listaUsuarios;
    private ListadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        nuevo = findViewById(R.id.btnNuevo);
        lista = findViewById(R.id.lvLista);

        listaUsuarios = new ArrayList<>();

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        ListadoActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ListadoAdapter(listaUsuarios, this);
        lista.setAdapter(adapter);
    }
}

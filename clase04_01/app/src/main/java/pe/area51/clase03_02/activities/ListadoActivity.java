package pe.area51.clase03_02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pe.area51.clase03_02.AndroidApplication;
import pe.area51.clase03_02.R;
import pe.area51.clase03_02.adapters.ListadoAdapter;
import pe.area51.clase03_02.models.Usuario;

public class ListadoActivity extends AppCompatActivity {
    private Button btnNuevo, btnCerrar;
    private ListView lvLista;
    private ListadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        btnNuevo = findViewById(R.id.btnNuevo);
        lvLista = findViewById(R.id.lvLista);
        btnCerrar = findViewById(R.id.btnCerrar);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        ListadoActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(
                        "clase04", MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(ListadoActivity.this,
                        MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ListadoAdapter(this, AndroidApplication.lista);
        lvLista.setAdapter(adapter);
    }
}

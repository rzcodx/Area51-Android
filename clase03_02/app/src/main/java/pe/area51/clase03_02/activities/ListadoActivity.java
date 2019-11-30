package pe.area51.clase03_02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pe.area51.clase03_02.R;

public class ListadoActivity extends AppCompatActivity {
    private Button nuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        nuevo = findViewById(R.id.btnNuevo);

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        ListadoActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}

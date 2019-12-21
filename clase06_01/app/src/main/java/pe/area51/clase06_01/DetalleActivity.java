package pe.area51.clase06_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {
    private TextView tvUsuario, tvNombre, tvEmail, tvAddress, tvCompany, tvWeb;
    private Button btnVerAlbumes, btnVerPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvUsuario = findViewById(R.id.tvUsuario);
        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);
        tvCompany = findViewById(R.id.tvCompany);
        tvWeb = findViewById(R.id.tvWeb);

        btnVerAlbumes = findViewById(R.id.btnVerAlbumes);
        btnVerPosts = findViewById(R.id.btnVerPosts);

        Usuario usuario = getIntent().getParcelableExtra("usuario");
        tvUsuario.setText(usuario.getUsername());
        tvNombre.setText(usuario.getName());
        tvEmail.setText(usuario.getEmail());
        tvAddress.setText(usuario.getAddress().getCity());
        tvCompany.setText(usuario.getCompany().getName());
        tvWeb.setText(usuario.getWebsite());

        btnVerPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleActivity.this, PostActivity.class);
                intent.putExtra("id", usuario.getId());
                startActivity(intent);
            }
        });

        btnVerAlbumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

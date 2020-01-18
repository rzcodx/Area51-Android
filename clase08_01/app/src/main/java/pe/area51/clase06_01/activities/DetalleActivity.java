package pe.area51.clase06_01.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pe.area51.clase06_01.R;
import pe.area51.clase06_01.net.response.usuario.Usuario;
import pub.devrel.easypermissions.EasyPermissions;

public class DetalleActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private TextView tvUsuario, tvNombre, tvEmail, tvAddress, tvCompany, tvWeb, tvPhone;
    private Button btnVerAlbumes, btnVerPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Detalle");

        tvUsuario = findViewById(R.id.tvUsuario);
        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);
        tvCompany = findViewById(R.id.tvCompany);
        tvWeb = findViewById(R.id.tvWeb);
        tvPhone = findViewById(R.id.tvPhone);

        btnVerAlbumes = findViewById(R.id.btnVerAlbumes);
        btnVerPosts = findViewById(R.id.btnVerPosts);

        Usuario usuario = getIntent().getParcelableExtra("usuario");
        tvUsuario.setText(usuario.getUsername());
        tvNombre.setText(usuario.getName());
        tvEmail.setText(usuario.getEmail());
        tvAddress.setText(usuario.getAddress().getCity());
        tvCompany.setText(usuario.getCompany().getName());
        tvWeb.setText(usuario.getWebsite());
        tvPhone.setText(usuario.getPhone());

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
                Intent intent = new Intent(DetalleActivity.this, AlbumActivity.class);
                intent.putExtra("id", usuario.getId());
                startActivity(intent);
            }
        });

        tvWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://" + tvWeb.getText().toString()));
                startActivity(intent);
            }
        });

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                //intent.setType("message/rfc822");
                intent.setData(Uri.parse("mailto:" + tvEmail.getText().toString()));
                startActivity(intent);
                //startActivity(Intent.createChooser(intent, "Email"));
            }
        });

        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodRequireCallPermission();
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

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private final int RC_CALL = 123;

    private void methodRequireCallPermission() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + tvPhone.getText().toString()));
            startActivity(intent);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.call_required), RC_CALL, perms);
        }
    }
}

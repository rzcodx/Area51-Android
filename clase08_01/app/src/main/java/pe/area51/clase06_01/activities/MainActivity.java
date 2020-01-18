package pe.area51.clase06_01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import io.realm.RealmResults;
import pe.area51.clase06_01.R;
import pe.area51.clase06_01.Reusable;
import pe.area51.clase06_01.database.usuarios.AddressEntidad;
import pe.area51.clase06_01.database.usuarios.CompanyEntidad;
import pe.area51.clase06_01.database.usuarios.GeoEntidad;
import pe.area51.clase06_01.database.MetodoSQL;
import pe.area51.clase06_01.database.usuarios.UsuarioEntidad;
import pe.area51.clase06_01.net.RetrofitConfiguration;
import pe.area51.clase06_01.net.RetrofitServicios;
import pe.area51.clase06_01.net.response.usuario.Address;
import pe.area51.clase06_01.net.response.usuario.Company;
import pe.area51.clase06_01.net.response.usuario.Geo;
import pe.area51.clase06_01.net.response.usuario.Usuario;
import pe.area51.clase06_01.adapters.UsuarioAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private EditText etBuscar;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvDatos = findViewById(R.id.rvDatos);
        etBuscar = findViewById(R.id.etBuscar);
        btnBuscar = findViewById(R.id.btnBuscar);

        MetodoSQL metodoSQL = new MetodoSQL();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoBuscar = etBuscar.getText().toString();

                ArrayList<Usuario> lista = new ArrayList<>();
                RealmResults<UsuarioEntidad> usuarios = metodoSQL.obtenerUsuarioPorNombre(textoBuscar);

                if (usuarios != null && usuarios.size() > 0) {
                    for (UsuarioEntidad entidad : usuarios) {
                        Geo geo = new Geo();
                        geo.setLat(entidad.getAddress().getGeo().getLat());
                        geo.setLng(entidad.getAddress().getGeo().getLng());

                        Address address = new Address();
                        address.setStreet(entidad.getAddress().getStreet());
                        address.setSuite(entidad.getAddress().getSuite());
                        address.setCity(entidad.getAddress().getSuite());
                        address.setZipcode(entidad.getAddress().getZipcode());
                        address.setGeo(geo);

                        Company company = new Company();
                        company.setName(entidad.getCompany().getName());
                        company.setCatchPhrase(entidad.getCompany().getCatchPhrase());
                        company.setBs(entidad.getCompany().getBs());

                        Usuario usuario = new Usuario();
                        usuario.setName(entidad.getName());
                        usuario.setUsername(entidad.getUsername());
                        usuario.setEmail(entidad.getEmail());
                        usuario.setPhone(entidad.getPhone());
                        usuario.setWebsite(entidad.getWebsite());
                        usuario.setAddress(address);
                        usuario.setCompany(company);

                        lista.add(usuario);
                    }
                }

                UsuarioAdapter adapter = new UsuarioAdapter(MainActivity.this, lista);
                rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvDatos.setAdapter(adapter);

            }
        });

        if (Reusable.isOnline()) {
            RetrofitServicios servicios = RetrofitConfiguration.obtenerConfiguracion()
                    .create(RetrofitServicios.class);

            Call<ArrayList<Usuario>> call = servicios.obtenerUsuarios();

            call.enqueue(new Callback<ArrayList<Usuario>>() {
                @Override
                public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                    ArrayList<Usuario> datos = response.body();
                    if (datos != null && datos.size() > 0) {
                        metodoSQL.eliminarUsuarios();
                        for (Usuario usuario : datos) {
                            GeoEntidad geoEntidad = new GeoEntidad();
                            geoEntidad.setLat(usuario.getAddress().getGeo().getLat());
                            geoEntidad.setLng(usuario.getAddress().getGeo().getLng());

                            AddressEntidad addressEntidad = new AddressEntidad();
                            addressEntidad.setStreet(usuario.getAddress().getStreet());
                            addressEntidad.setSuite(usuario.getAddress().getSuite());
                            addressEntidad.setCity(usuario.getAddress().getCity());
                            addressEntidad.setZipcode(usuario.getAddress().getZipcode());
                            addressEntidad.setGeo(geoEntidad);

                            CompanyEntidad companyEntidad = new CompanyEntidad();
                            companyEntidad.setName(usuario.getCompany().getName());
                            companyEntidad.setCatchPhrase(usuario.getCompany().getCatchPhrase());
                            companyEntidad.setBs(usuario.getCompany().getBs());

                            UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
                            usuarioEntidad.setIdData(usuario.getId());
                            usuarioEntidad.setName(usuario.getName());
                            usuarioEntidad.setUsername(usuario.getUsername());
                            usuarioEntidad.setEmail(usuario.getEmail());
                            usuarioEntidad.setPhone(usuario.getPhone());
                            usuarioEntidad.setWebsite(usuario.getWebsite());
                            usuarioEntidad.setAddress(addressEntidad);
                            usuarioEntidad.setCompany(companyEntidad);

                            metodoSQL.registrarUsuario(usuarioEntidad);
                        }
                    }

                    UsuarioAdapter adapter = new UsuarioAdapter(MainActivity.this, response.body());
                    rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvDatos.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {

                }
            });
        } else {
            ArrayList<Usuario> lista = new ArrayList<>();
            RealmResults<UsuarioEntidad> usuarios = metodoSQL.obtenerUsuarios();

            if (usuarios != null && usuarios.size() > 0) {
                for (UsuarioEntidad entidad : usuarios) {
                    Geo geo = new Geo();
                    geo.setLat(entidad.getAddress().getGeo().getLat());
                    geo.setLng(entidad.getAddress().getGeo().getLng());

                    Address address = new Address();
                    address.setStreet(entidad.getAddress().getStreet());
                    address.setSuite(entidad.getAddress().getSuite());
                    address.setCity(entidad.getAddress().getSuite());
                    address.setZipcode(entidad.getAddress().getZipcode());
                    address.setGeo(geo);

                    Company company = new Company();
                    company.setName(entidad.getCompany().getName());
                    company.setCatchPhrase(entidad.getCompany().getCatchPhrase());
                    company.setBs(entidad.getCompany().getBs());

                    Usuario usuario = new Usuario();
                    usuario.setName(entidad.getName());
                    usuario.setUsername(entidad.getUsername());
                    usuario.setEmail(entidad.getEmail());
                    usuario.setPhone(entidad.getPhone());
                    usuario.setWebsite(entidad.getWebsite());
                    usuario.setAddress(address);
                    usuario.setCompany(company);

                    lista.add(usuario);
                }
            }

            UsuarioAdapter adapter = new UsuarioAdapter(MainActivity.this, lista);
            rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rvDatos.setAdapter(adapter);
        }
    }
}

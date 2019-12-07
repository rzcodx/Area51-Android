package pe.area51.clase03_02;

import android.app.Application;

import java.util.ArrayList;

import pe.area51.clase03_02.models.Usuario;

public class AndroidApplication extends Application {
    public static ArrayList<Usuario> lista;

    @Override
    public void onCreate() {
        super.onCreate();

        lista = new ArrayList<>();
        if (lista.isEmpty()) {
            Usuario obj = new Usuario();
            obj.setUsuario("admin");
            obj.setNombre("Admin");
            obj.setPassword("1234");
            lista.add(obj);
        }
    }
}

package pe.area51.clase04_02;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class AndroidApplication extends Application {
    public static ArrayList<Tipo> listaTipo;
    public static ArrayList<Imagen> listaImagen;
    public static ArrayList<Producto> listaProducto;

    @Override
    public void onCreate() {
        super.onCreate();

        listaTipo = new ArrayList<>();
        listaImagen = new ArrayList<>();
        listaProducto = new ArrayList<>();

        Fresco.initialize(this);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

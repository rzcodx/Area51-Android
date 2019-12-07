package pe.area51.clase03_02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pe.area51.clase03_02.R;
import pe.area51.clase03_02.models.Usuario;

public class ListadoAdapter extends BaseAdapter {
    private ArrayList<Usuario> lista;
    private Context context;

    public ListadoAdapter(ArrayList<Usuario> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_usuario, viewGroup, false);

        TextView nombre = view.findViewById(R.id.tvNombre);
        TextView usuario = view.findViewById(R.id.tvUsuario);

        Usuario obj = (Usuario) getItem(position);

        nombre.setText(obj.getNombre());
        usuario.setText(obj.getUsuario());

        return view;
    }
}

package pe.area51.clase03_02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pe.area51.clase03_02.R;
import pe.area51.clase03_02.models.Usuario;

public class ListadoAdapter extends ArrayAdapter {
    private ArrayList<Usuario> lista;
    private Context context;

    public ListadoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Usuario> objects) {
        super(context, resource, objects);

        this.context = context;
        this.lista = objects;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_usuario, parent, false);

        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvUsuario = convertView.findViewById(R.id.tvUsuario);

        return  convertView;
    }
}

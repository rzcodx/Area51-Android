package pe.area51.clase02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.area51.clase02.models.Producto;
import pe.area51.clase02.R;

public class ListadoAdapter extends BaseAdapter {
    private ArrayList<Producto> lista;
    private Context context; // Para saber en que pantalla nos ubicamos

    public ListadoAdapter(ArrayList<Producto> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_listado, viewGroup, false);

        TextView categoria = view.findViewById(R.id.tvCategoria);
        TextView nombre = view.findViewById(R.id.tvNombre);
        TextView descripcion = view.findViewById(R.id.tvDescripcion);
        TextView publico = view.findViewById(R.id.tvPublico);

        Producto obj = (Producto) getItem(i);

        categoria.setText(obj.getCategoria());
        nombre.setText(obj.getNombre());
        descripcion.setText(obj.getDescripcion());
        publico.setText(String.valueOf(obj.isPublico()));

        /*
            VISIBLE     -> Visible
            INVISIBLE   -> Invisible
            GONE        -> Invisible pero sin ocupar espacio
         */
        if (!obj.isAccept()) {
            descripcion.setVisibility(View.GONE);
            publico.setVisibility(View.GONE);
        }

        return view;
    }

    public void eliminarObjecto(Producto obj) {
        lista.remove(obj);
        // Indicando al adaptara que han habido cambios en el listado, y por lo tanto que lo refresque
        notifyDataSetChanged();
    }
}

package com.example.contactos.Adaptador;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.contactos.R;
import com.example.contactos.Usuario;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Usuario> implements View.OnCreateContextMenuListener {

    private ArrayList<Usuario> miLista;
    private Context context;
    private int resourceLayout;

    public ListAdapter(Context context, int resource, ArrayList<Usuario> objects) {
        super(context, resource, objects);
        this.miLista = objects;
        this.context = context;
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        String nombre = getItem(position).getNombre();
        String telefono = getItem(position).getTelefono();
        String grupo = getItem(position).getGrupo();
        if (view == null)
            view = LayoutInflater.from(context).inflate(resourceLayout,null);


        TextView txtNombre = view.findViewById(R.id.txtNombre);
        TextView txtTelefono = view.findViewById(R.id.txtTelefono);
        TextView gp = view.findViewById(R.id.txtGrupo);
        txtNombre.setText(nombre.toUpperCase());


        txtNombre.setText(nombre);
        txtTelefono.setText(telefono);
        gp.setText(grupo);


        return view;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Selecciona una acción");


    }
}

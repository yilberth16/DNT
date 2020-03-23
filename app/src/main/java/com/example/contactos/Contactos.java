package com.example.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.contactos.Adaptador.ListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Contactos extends AppCompatActivity {
    private ListView miLista;
    ListAdapter mAdapter;
    String nombre;
    TextView txtNombre;
    Usuario usuario;
    ArrayList<Usuario> listUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        txtNombre = findViewById(R.id.txtNombre);

        nombre = getIntent().getStringExtra("nombre");
        String telefono = getIntent().getStringExtra("telefono");
        String grupo = getIntent().getStringExtra("grupo");
        usuario = getIntent().getParcelableExtra("usuario");
        //listUsuario = new ArrayList<>();
        final ArrayList<Usuario> listUsuario = getIntent().getParcelableArrayListExtra("lista");
        TextView txtOrdenarApellido = findViewById(R.id.txtOrdenarApellido);
        TextView txtOrdenarGrupo = findViewById(R.id.txtOrdenarGrupo);
        TextView txtEliminarTodos = findViewById(R.id.txtEliminar);
        TextView txtInvertir = findViewById(R.id.txtInvertir);


        miLista = findViewById(R.id.listContacto);
        usuario = new Usuario();
        usuario.setNombre(nombre);
       /* usuario.setTelefono(telefono);
        usuario.setGrupo(grupo);*/




        mAdapter = new ListAdapter(Contactos.this,R.layout.contactos_items,listUsuario);
        miLista.setAdapter(mAdapter);
        registerForContextMenu(miLista);
        txtOrdenarApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(listUsuario, new Comparator<Usuario>() {
                    @Override
                    public int compare(Usuario o1, Usuario o2) {
                        return o1.getNombre().compareTo(o2.getNombre());
                    }
                });
                mAdapter.notifyDataSetChanged();
            }
        });

        txtOrdenarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(listUsuario, new Comparator<Usuario>() {
                    @Override
                    public int compare(Usuario o1, Usuario o2) {
                        return o1.getGrupo().compareTo(o2.getGrupo());
                    }
                });
                mAdapter.notifyDataSetChanged();
            }
        });

        txtInvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(listUsuario);

                mAdapter.notifyDataSetChanged();
            }
        });

        txtEliminarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listUsuario.clear();
                mAdapter.notifyDataSetChanged();

            }
        });



    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {



                return super.onContextItemSelected(item);



    }
}

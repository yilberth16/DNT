package com.example.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText edtTelefono, edtNombre;
    Spinner grupo;
    Button btnGuardar;
    String nombre,telefono, group;
    Usuario mi_usuario;
    String doc;
    ArrayList<Usuario> listaUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtTelefono = (EditText)findViewById(R.id.edtTelefono);
        grupo =(Spinner)findViewById(R.id.spinner_seleccion_grupo);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);

        listaUsuario = new ArrayList<>();

        registerForContextMenu(edtTelefono);
        registerForContextMenu(edtNombre);
        grupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edtNombre.getText().toString();
                telefono = edtTelefono.getText().toString();
                group = grupo.getSelectedItem().toString();

                if (nombre.isEmpty() || telefono.isEmpty() ){
                    Toast.makeText(MainActivity.this, "Te falto llenar un campo", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Se guardo correctamente", Toast.LENGTH_SHORT).show();
                    mi_usuario = new Usuario();
                    mi_usuario.setNombre(nombre);
                    mi_usuario.setGrupo(group);
                    mi_usuario.setTelefono(telefono);
                    listaUsuario.add(mi_usuario);

                    /*Intent intent = new Intent(MainActivity.this,Registro.class);
                    intent.putExtra("lista",mi_usuario);
                    startActivity(intent);*/


                }
            }
        });

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Escoge una opcion");

        getMenuInflater().inflate(R.menu.menu_telefono,menu);
        getMenuInflater().inflate(R.menu.menu_contextual,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
        if (id== R.id.opcion){
            Random rnd = new Random();
            int array[] = new int[30];
            for(int i = 0; i < array.length; i++)
                edtTelefono.setText("300"+rnd.nextInt(9000000-(-30)+5));

       }
        else if (id==R.id.opcion2){
            Random rnd = new Random();
            int array[] = new int[30];
            for(int i = 0; i < array.length; i++)
            edtTelefono.setText("310"+rnd.nextInt(9000000-(-30)+5));
        }
        else if (id==R.id.opcion3){
            Random rnd = new Random();
            int array[] = new int[30];
            for(int i = 0; i < array.length; i++)
                edtTelefono.setText("320"+rnd.nextInt(9000000-(-30)+5));

        }
        else if(id==R.id.convertir){
            String edt = edtNombre.getText().toString();
            if (edt.equals(edt.toLowerCase()))
            edtNombre.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        }
        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.limpiar){
            edtNombre.setText("");
            edtTelefono.setText("");
        }
        else if(id == R.id.ver_contactos){
            Intent intent = new Intent(MainActivity.this,Contactos.class);
            intent.putExtra("nombre",nombre);
            intent.putExtra("telefono",telefono);
            intent.putExtra("grupo",group);
            intent.putExtra("usuario",mi_usuario);
            intent.putExtra("lista",listaUsuario);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        doc =(String)grupo.toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
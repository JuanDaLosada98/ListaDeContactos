package com.example.estudiante.listadecontactos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private ListView lv_contactos;
    ContactoAdapter customAdapter;
    EditText et_contacto;
    EditText et_telefono;
    Button btn_agregar;
    Switch s_genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_contacto = findViewById(R.id.et_contacto);
        et_telefono = findViewById(R.id.et_telefono);
        btn_agregar = findViewById(R.id.btn_agre);
        s_genero = findViewById(R.id.s_genero);
        lv_contactos = findViewById(R.id.lv_contactos);
        customAdapter = new ContactoAdapter(this);
        lv_contactos.setAdapter(customAdapter);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contacto = et_contacto.getText().toString();
                String telefono = et_telefono.getText().toString();
                Contacto newContacto = new Contacto(contacto,telefono);
                customAdapter.agregarContacto(newContacto);
                et_contacto.setText("");
                et_telefono.setText("");
            }
        });

    }
}

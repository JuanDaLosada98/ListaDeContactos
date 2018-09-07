package com.example.estudiante.listadecontactos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter{

    ArrayList<Contacto> contactos;
    Activity activity;


    public ContactoAdapter(Activity activity){
        this.activity = activity;
        contactos = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int i) {
        return contactos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = activity.getLayoutInflater();

        View renglon = inflater.inflate(R.layout.renglon, null, false);
        TextView item_contacto = renglon.findViewById(R.id.item_contacto);
        final TextView item_telefono = renglon.findViewById(R.id.item_telefono);
        Button llamar = renglon.findViewById(R.id.item_llamar);
        Button eliminar = renglon.findViewById(R.id.btn_eliminar);
        item_contacto.setText(contactos.get(i).getNombre());
        item_telefono.setText(contactos.get(i).getTelefono());
        ImageView imagen = renglon.findViewById(R.id.iv_imagen);

        if(contactos.get(i).getGenero().equals("masculino")){
            imagen.setImageResource(R.drawable.masculino);
        } else {
            imagen.setImageResource(R.drawable.femenino);
        }


        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noticias.remove(position);
                //notifyDataSetChanged();

                final int request_call = 1;
                String number = item_telefono.getText().toString();
                Intent llamar = new Intent(Intent.ACTION_CALL);
                llamar.setData(Uri.parse("tel:"+number));

                if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},request_call);

                    } else {
                        activity.startActivity(llamar);
                    }
                }



                //ir a otra actividad
                //Intent intento = new Intent(activity, NoticiaView.class);
                //activity.startActivity(intento);

                // Intent i = new Intent(MainActivity.this,NoticiaView.class);
                // startActivity(i);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactos.remove(i);
                notifyDataSetChanged();
            }
        });

        return renglon;
    }

    public void agregarContacto(Contacto contacto){
        contactos.add(contacto);
        notifyDataSetChanged();
    }
}

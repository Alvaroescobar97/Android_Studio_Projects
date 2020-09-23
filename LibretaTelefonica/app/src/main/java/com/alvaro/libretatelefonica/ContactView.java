package com.alvaro.libretatelefonica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

//ViewModel ----> Modelo de la vista para representar un dato
public class ContactView extends RecyclerView.ViewHolder implements View.OnClickListener{

    //Objeto a representar
    private Contact contact;

    //Modelo de la vista
    private ConstraintLayout root;
    private TextView nombre;
    private TextView telefono;
    private Button contactcall;
    private Button contactwrite;
    private Button contactdelete;


    private OnContactItemAction listener;

    public ContactView(ConstraintLayout root) {
        super(root);
        this.root = root;
        nombre = root.findViewById(R.id.contactname);
        telefono = root.findViewById(R.id.contactphone);

        contactcall = root.findViewById(R.id.contactcall);
        contactdelete = root.findViewById(R.id.contactdelete);
        contactwrite = root.findViewById(R.id.contactwrite);

        contactcall.setOnClickListener(this);
        contactdelete.setOnClickListener(this);
        contactwrite.setOnClickListener(this);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getTelefono() {
        return telefono;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.contactwrite:
                String tel = "tel:" + contact.getTelefono();
                Intent write = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                root.getContext().startActivity(write);
                break;
            case R.id.contactcall:

                Toast.makeText(root.getContext(),contact.getNombre(),Toast.LENGTH_LONG).show();
                String dial = "tel:" + contact.getTelefono();
                Intent call = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
                root.getContext().startActivity(call);

                break;
            case R.id.contactdelete:
                if(listener !=null){
                    listener.onDeleteContact(this.contact);
                }

                break;
        }
    }

    //Para la inversion de control

    public void setListener(OnContactItemAction listener){
        this.listener = listener;
    }

    public void setContact(Contact contact) {
        this.contact=contact;
    }

    public interface OnContactItemAction{
        void onDeleteContact(Contact contact);
    }
}

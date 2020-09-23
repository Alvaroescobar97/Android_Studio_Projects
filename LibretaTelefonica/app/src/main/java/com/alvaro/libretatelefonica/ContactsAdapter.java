package com.alvaro.libretatelefonica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.UUID;

public class ContactsAdapter extends RecyclerView.Adapter<ContactView> implements ContactView.OnContactItemAction{

    private ArrayList<Contact> contacts;

    public ContactsAdapter() {
        contacts = new ArrayList<>();
        contacts.add(new Contact(UUID.randomUUID().toString(), "Pepe","3142967845"));
        contacts.add(new Contact(UUID.randomUUID().toString(), "Lucas","3154896512"));

    }

    public void addContact(Contact contact){
        contacts.add(contact);
        //Notifica que la informacion cambia y asi mismo los produce
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflater XML---> View
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View renglon = inflater.inflate(R.layout.contactrow, parent,false);
        ConstraintLayout renglonroot = (ConstraintLayout) renglon;
        ContactView contactView = new ContactView(renglonroot);
        contactView.setListener(this);
        return contactView;
    }

    //Donde se carga la informacion de cada uno de los renglones y con el position se sabe que elemento es
    @Override
    public void onBindViewHolder(@NonNull ContactView holder, int position) {
        holder.setContact(contacts.get(position));
    holder.getNombre().setText(contacts.get(position).getNombre());
    holder.getTelefono().setText(contacts.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    @Override
    public void onDeleteContact(Contact contact) {
    contacts.remove(contact);
    this.notifyDataSetChanged();
    }
}

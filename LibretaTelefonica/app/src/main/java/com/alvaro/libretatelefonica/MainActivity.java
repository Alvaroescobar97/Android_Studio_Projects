package com.alvaro.libretatelefonica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView contactsViewList;
    private LinearLayoutManager layoutManager;
    private ContactsAdapter adapter;

    private EditText nombreET;
    private EditText telefonoET;
    private Button agregarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsViewList = findViewById(R.id.contactsViewList);
        nombreET = findViewById(R.id.nombreET);
        telefonoET = findViewById(R.id.telefonoET);
        agregarBtn = findViewById(R.id.agregarBtn);
        agregarBtn.setOnClickListener(this);

        contactsViewList.setHasFixedSize(true);

        //Creando el Manager de la lista
        layoutManager = new LinearLayoutManager(this);
        contactsViewList.setLayoutManager(layoutManager);

        //Creando el Adaptador de la lista
        adapter = new ContactsAdapter();
        contactsViewList.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.agregarBtn:
                Contact contact = new Contact(UUID.randomUUID().toString(), nombreET.getText().toString(), telefonoET.getText().toString());
                adapter.addContact(contact);
                break;
        }
    }
}
package com.alvaro.sem2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListItemFragment extends Fragment implements NewItemFragment.OnTaskAddedListener{

    //State
    private ArrayList<String> tasks;

    private TextView listaTareas;

    public ListItemFragment() {
        // Required empty public constructor
        tasks = new ArrayList<>();
    }


    public static ListItemFragment newInstance() {
        ListItemFragment fragment = new ListItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_list_item, container, false);
        listaTareas=root.findViewById(R.id.listaTareas);

        listaTareas.setText("");
        for (int i=0;i<tasks.size();i++){
            String task = tasks.get(i);
            listaTareas.append(task + "\n");
        }

        return root;
    }

    @Override
    public void onTaskAdded(String task){
        tasks.add(task);
        //Prohibido usar Views, UI Thread
    }
    //Todas las vistas se destruyen pero quedan los objetos
}
package com.example.uas_10119113;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_home, container, false);

        DatabaseHelper db = new DatabaseHelper(fragment.getContext());
        List<Notes> notes = db.getNotes();
        RecyclerView recycleView = fragment.findViewById(R.id.noteList);
        recycleView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        NoteIndexAdapter adapter = new NoteIndexAdapter(fragment.getContext(), notes);

        recycleView.setAdapter(adapter);

        FloatingActionButton addNote = fragment.findViewById(R.id.btn_add);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                toAddNote();
            }
        });

        return fragment;
    }

    public void toAddNote()
    {
        Intent i = new Intent(getActivity(), AddNote.class);
        startActivity(i);
    }
}

//NIM : 10119113
//Nama : Dafa Rizky Fahreza
//Kelas : IF3
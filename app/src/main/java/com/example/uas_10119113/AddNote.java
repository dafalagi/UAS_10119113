package com.example.uas_10119113;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteBody, noteCategory;
    Calendar calendar;
    String today, now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.addNoteToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        noteTitle = findViewById(R.id.noteTitle);
        noteBody = findViewById(R.id.noteBody);
        noteCategory = findViewById(R.id.noteCategory);

        calendar = Calendar.getInstance();
        today = calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+
                calendar.get(Calendar.YEAR);
        now = pad(calendar.get(Calendar.HOUR))+":"+pad(calendar.get(Calendar.MINUTE));
    }

    private String pad(int i)
    {
        if(i < 10)
            return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btn_save)
        {
            Notes note = new Notes(noteTitle.getText().toString(), noteBody.getText().toString(),
                    noteCategory.getText().toString(), today, now);
            DatabaseHelper db = new DatabaseHelper(this);

            db.addNote(note);
            Toast.makeText(this, "Tersimpan!", Toast.LENGTH_SHORT).show();
            toMain();
        }

        if(item.getItemId() == R.id.btn_delete)
        {
            noteTitle = findViewById(R.id.noteTitle);
            noteBody = findViewById(R.id.noteBody);
            noteCategory = findViewById(R.id.noteCategory);

            noteTitle.setText("");
            noteBody.setText("");
            noteCategory.setText("");

            Toast.makeText(this, "Tidak tersimpan!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void toMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

//NIM : 10119113
//Nama : Dafa Rizky Fahreza
//Kelas : IF3
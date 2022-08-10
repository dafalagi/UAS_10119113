package com.example.uas_10119113;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class EditNote extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteBody, noteCategory;
    Calendar calendar;
    String today, now;
    DatabaseHelper db;
    Notes note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        toolbar = findViewById(R.id.editNoteToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Note");
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

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        db = new DatabaseHelper(this);
        note = db.getNote(id);

        noteTitle.setText(note.getTitle());
        noteBody.setText(note.getBody());
        noteCategory.setText(note.getCategory());
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(EditNote.this);
            builder.setTitle("Simpan");
            builder.setMessage("Apakah anda yakin ingin menyimpan perubahan?");
            builder.setPositiveButton("Ya", (new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db = new DatabaseHelper(EditNote.this);

                    note.setTitle(noteTitle.getText().toString());
                    note.setBody(noteBody.getText().toString());
                    note.setCategory(noteCategory.getText().toString());
                    note.setDate(today);
                    note.setTime(now);

                    db.editNote(note);
                    Toast.makeText(EditNote.this, "Tersimpan!", Toast.LENGTH_SHORT).show();
                    toDetail();
                    finish();
                }
            }));

            builder.setNegativeButton("Tidak", (new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }));

            AlertDialog dialog = builder.create();
            dialog.show();
        }else
        {
            Toast.makeText(this, "Error! Gagal Tersimpan!", Toast.LENGTH_SHORT).show();
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

    public void toDetail()
    {
        Intent intent = new Intent(getApplicationContext(), NoteDetails.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("id", note.getId());
        startActivity(intent);
    }
}

//NIM : 10119113
//Nama : Dafa Rizky Fahreza
//Kelas : IF3
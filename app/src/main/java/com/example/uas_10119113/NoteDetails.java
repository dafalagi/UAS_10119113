package com.example.uas_10119113;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteDetails extends AppCompatActivity {

    TextView title, category, body, date;
    DatabaseHelper db;
    Notes note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Note Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        title = findViewById(R.id.titleDetails);
        category = findViewById(R.id.categoryDetails);
        body = findViewById(R.id.bodyDetails);
        date = findViewById(R.id.dateDetails);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);

        db = new DatabaseHelper(this);
        note = db.getNote(id);

        title.setText(note.getTitle());
        category.setText(note.getCategory());
        body.setText(note.getBody());
        date.setText(note.getDate());
        body.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(NoteDetails.this);
                builder.setTitle("Hapus");
                builder.setMessage("Apakah anda yakin ingin menghapus catatan ini?");
                builder.setPositiveButton("Ya", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteNote(id);
                        Toast.makeText(NoteDetails.this, "Terhapus!", Toast.LENGTH_SHORT).show();
                        toMain();
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
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btn_edit)
        {
            Intent intent = new Intent(this, EditNote.class);
            intent.putExtra("id", note.getId());
            startActivity(intent);
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
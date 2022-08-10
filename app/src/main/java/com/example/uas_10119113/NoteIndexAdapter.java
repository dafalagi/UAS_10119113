package com.example.uas_10119113;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteIndexAdapter extends RecyclerView.Adapter<NoteIndexAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Notes> notes;

    public NoteIndexAdapter(Context context, List<Notes> notes)
    {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteIndexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_notes_index, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteIndexAdapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();

        holder.title.setText(title);
        holder.date.setText(date);
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, date, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            date = itemView.findViewById(R.id.notesDate);
            time = itemView.findViewById(R.id.notesTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), NoteDetails.class);
                    intent.putExtra("id", notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

//NIM : 10119113
//Nama : Dafa Rizky Fahreza
//Kelas : IF3
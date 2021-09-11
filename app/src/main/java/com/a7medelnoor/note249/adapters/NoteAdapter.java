package com.a7medelnoor.note249.adapters;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a7medelnoor.note249.R;
import com.a7medelnoor.note249.entities.Note;
import com.a7medelnoor.note249.listeners.Listeners;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private final List<Note> notes;
    private Listeners noteListeners;

    public NoteAdapter(List<Note> notes, Listeners noteListeners) {
        this.notes = notes;
        this.noteListeners = noteListeners;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_contianer_note,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, @SuppressLint("RecyclerView") final int position) {
        noteViewHolder.setNote(notes.get(position));
        noteViewHolder.layoutNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteListeners.onNoteClicked(notes.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textSubTitle, textDateTime;
        LinearLayout layoutNote;
        RoundedImageView imageViewNote;
        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSubTitle = itemView.findViewById(R.id.textSubtitle);
            textDateTime = itemView.findViewById(R.id.textDateTime);
            layoutNote = itemView.findViewById(R.id.layout_note);
            imageViewNote = itemView.findViewById(R.id.imageNote);

        }

        void setNote(Note note) {
            textTitle.setText(note.getTitle());
            if (note.getSubtitle().trim().isEmpty()) {
                textSubTitle.setVisibility(View.GONE);
            } else {
                textSubTitle.setText(note.getSubtitle());
            }
            textDateTime.setText(note.getDataTime());

            GradientDrawable gradientDrawable = (GradientDrawable) layoutNote.getBackground();
            if (note.getColor() != null){
                gradientDrawable.setColor(Color.parseColor(note.getColor()));
            }else {
                gradientDrawable.setColor(Color.parseColor("#333333"));
            }
            if (note.getImagePath() != null){
                imageViewNote.setImageBitmap(BitmapFactory.decodeFile(note.getImagePath()));
                imageViewNote.setVisibility(View.VISIBLE);
            }else {
                imageViewNote.setVisibility(View.GONE);
            }
        }
    }
}

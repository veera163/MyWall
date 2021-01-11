package com.frazen.edaftar.Adopters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frazen.edaftar.Activity.ViewLetterAndNotesActivity;
import com.frazen.edaftar.Model.LettersAndNotes;
import com.frazen.edaftar.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class LettersAndNotesAdopter extends RecyclerView.Adapter<LettersAndNotesAdopter.LettersAndNotesHolder>{

    private final Context context;
    private final List<LettersAndNotes> lettersAndNotesList;
    private final Activity activity;

    public LettersAndNotesAdopter(Context context, List<LettersAndNotes> lettersAndNotesList, Activity activity) {
        this.context = context;
        this.lettersAndNotesList = lettersAndNotesList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LettersAndNotesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.lettersandnotes_list, viewGroup, false);
        return new LettersAndNotesAdopter.LettersAndNotesHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LettersAndNotesHolder holder, int position) {

        holder.txt_id.setText(lettersAndNotesList.get(position).getLonNo());
        holder.txt_name.setText(lettersAndNotesList.get(position).getPetitionerName());
        holder.txt_endorse_officer.setText(lettersAndNotesList.get(position).getSentToOfficer());
        holder.txt_dept.setText(lettersAndNotesList.get(position).getDepartment());
        holder.txt_status.setText(lettersAndNotesList.get(position).getStatus());

        holder.card_view.setOnClickListener(view -> {

            Intent intent= new Intent(context, ViewLetterAndNotesActivity.class);
            intent.putExtra("serialNo", lettersAndNotesList.get(position).getSerialNo());
            context.startActivity(intent);
            activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });
    }

    @Override
    public int getItemCount() {
        return lettersAndNotesList.size();
    }


    public static class LettersAndNotesHolder extends RecyclerView.ViewHolder {

        public CardView card_view;
        public TextView txt_id,txt_name,txt_status,txt_endorse_officer,txt_dept;
        public LettersAndNotesHolder(View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_dept=itemView.findViewById(R.id.txt_department);
            txt_endorse_officer=itemView.findViewById(R.id.txt_endorse_officer);
            txt_status=itemView.findViewById(R.id.txt_status);

        }
    }
}

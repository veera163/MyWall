package com.frazen.edaftar.Adopters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.frazen.edaftar.Activity.UpdateEndorsementActivity;
import com.frazen.edaftar.Model.EndorsementDetails;
import com.frazen.edaftar.R;

import java.util.List;

public class EndorsementListAdopter extends RecyclerView.Adapter<EndorsementListAdopter.EndorsementHolder>{

    private final Context context;
    private final List<EndorsementDetails> endorsementDetails;
    private final Activity activity;

    public EndorsementListAdopter(Context context, List<EndorsementDetails> endorsementDetails, FragmentActivity activity) {
        this.context = context;
        this.endorsementDetails = endorsementDetails;
        this.activity=activity;
    }

    @NonNull
    @Override
    public EndorsementHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.endorse_list, viewGroup, false);
        return new EndorsementListAdopter.EndorsementHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull EndorsementHolder holder, int position) {

        holder.txt_id.setText(endorsementDetails.get(position).getEndorsementNo());
        holder.txt_name.setText(endorsementDetails.get(position).getPetitionerName());
        holder.txt_endorse_officer.setText(endorsementDetails.get(position).getEndorsedOfficer());
        holder.txt_dept.setText(endorsementDetails.get(position).getDepartment());
        holder.txt_status.setText(endorsementDetails.get(position).getStatus());

        holder.card_view.setOnClickListener(view -> {
            Intent intent= new Intent(context, UpdateEndorsementActivity.class);
            intent.putExtra("serialNo", endorsementDetails.get(position).getSerialNo());
            context.startActivity(intent);
            activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });

    }

    @Override
    public int getItemCount() {
        return endorsementDetails.size();
    }

    public static class EndorsementHolder extends RecyclerView.ViewHolder {

        public CardView card_view;
        public TextView txt_id,txt_name,txt_status,txt_endorse_officer,txt_dept;
        public EndorsementHolder(View itemView) {
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

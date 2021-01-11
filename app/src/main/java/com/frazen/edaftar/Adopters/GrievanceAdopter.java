package com.frazen.edaftar.Adopters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frazen.edaftar.Activity.ViewGrivanceActivity;
import com.frazen.edaftar.Model.Grievance;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class GrievanceAdopter extends RecyclerView.Adapter<GrievanceAdopter.GrievanceHolder>{

    private final Context context;
    private final List<Grievance> grievanceList;
    private final Activity activity;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;
    public GrievanceAdopter(Context context, List<Grievance> grievanceLis, FragmentActivity activity) {
        this.context = context;
        this.grievanceList = grievanceLis;
        this.activity=activity;
    }

    @NonNull
    @Override
    public GrievanceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.grievance_list, viewGroup, false);
        return new GrievanceAdopter.GrievanceHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull GrievanceHolder holder, int position) {

        try {
            holder.txt_id.setText(grievanceList.get(position).getTgId());
            holder.txt_title.setText(grievanceList.get(position).getTitle());
            if(grievanceList.get(position).getCreateDate()!=null && !grievanceList.get(position).getCreateDate().isEmpty()) {
                date=formatter.parse(grievanceList.get(position).getCreateDate());
                holder.txt_date.setText(Utility.formatDate(date,SELECTED_DATE_DISPLAY_FORMAT));
            }
            holder.txt_status.setText(grievanceList.get(position).getStatus());
            holder.txt_beneficiary.setText(grievanceList.get(position).getPerson());

            holder.card_view.setOnClickListener(view -> {
                Intent intent= new Intent(context, ViewGrivanceActivity.class);
                intent.putExtra("serialNo", grievanceList.get(position).getTgId());
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return grievanceList.size();
    }


    public static class  GrievanceHolder extends RecyclerView.ViewHolder {

        public CardView card_view;
        public TextView txt_id,txt_title,txt_date,txt_status,txt_beneficiary;
        public GrievanceHolder(View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_status=itemView.findViewById(R.id.txt_status);
            txt_beneficiary=itemView.findViewById(R.id.txt_beneficiary);

        }
    }
}

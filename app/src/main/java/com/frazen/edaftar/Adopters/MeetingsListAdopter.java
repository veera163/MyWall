package com.frazen.edaftar.Adopters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.frazen.edaftar.Model.SelectDayMeeting;
import com.frazen.edaftar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MeetingsListAdopter extends RecyclerView.Adapter<MeetingsListAdopter.MeetingsHolder>{

    private final Context context;
    private final List<SelectDayMeeting> selectDayMeetings;

    public MeetingsListAdopter(Context context, List<SelectDayMeeting> selectDayMeetings) {
        this.context = context;
        this.selectDayMeetings = selectDayMeetings;
    }
    @NonNull
    @Override
    public MeetingsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.meeting_list, viewGroup, false);
        return new MeetingsHolder(listItem);
    }
    @Override
    public void onBindViewHolder(@NonNull MeetingsHolder holder, int position) {

        String start=selectDayMeetings.get(position).getDate()+" "+selectDayMeetings.get(position).getStartTime();
        String end=selectDayMeetings.get(position).getDate()+" "+selectDayMeetings.get(position).getEndTime();
        String inputPattern = "yyyy-MM-dd hh:mm:ss";
        String outputPattern = "dd-MM-yyyy hh:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date sdate,edate;

        try {
            holder.txt_title.setText(selectDayMeetings.get(position).getTitle());
            holder.txt_status.setText(selectDayMeetings.get(position).getStatus());
            if(selectDayMeetings.get(position).getStatus().equals("Attended") || selectDayMeetings.get(position).getStatus().equals("Committed")){
                holder.txt_status.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
            else if(selectDayMeetings.get(position).getStatus().equals("Rescheduled") || selectDayMeetings.get(position).getStatus().equals("Scheduled")){
                holder.txt_status.setTextColor(context.getResources().getColor(R.color.blue));
            }
            else {
                holder.txt_status.setTextColor(context.getResources().getColor(R.color.red));
            }
            sdate = inputFormat.parse(start);
            edate = inputFormat.parse(end);

            if (sdate != null) {
                holder.txt_start.setText(String.format("%s","Start : "+outputFormat.format(sdate)));
            }

            if (edate != null) {
                holder.txt_end.setText(String.format("%s","End : "+outputFormat.format(edate)));
            }

            holder.txt_location.setText(selectDayMeetings.get(position).getLocation());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.card_view.setOnClickListener(view -> {

           // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {

        return selectDayMeetings.size();
    }

    public static class MeetingsHolder extends RecyclerView.ViewHolder {

        public ImageView img_right;
        public CardView card_view;
        public TextView txt_title,txt_start,txt_end,txt_location,txt_status;
        public MeetingsHolder(View itemView) {
            super(itemView);
            img_right = itemView.findViewById(R.id.img_right);
            card_view = itemView.findViewById(R.id.card_view);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_start=itemView.findViewById(R.id.txt_start);
            txt_end=itemView.findViewById(R.id.txt_end);
            txt_location=itemView.findViewById(R.id.txt_location);
            txt_status=itemView.findViewById(R.id.txt_status);

        }
    }
}

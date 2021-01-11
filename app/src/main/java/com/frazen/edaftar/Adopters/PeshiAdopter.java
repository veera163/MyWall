package com.frazen.edaftar.Adopters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frazen.edaftar.Activity.ViewPeshiActivity;
import com.frazen.edaftar.Model.Peshi;
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

public class PeshiAdopter extends RecyclerView.Adapter<PeshiAdopter.PeshiHolder>{

    private final Context context;
    private final List<Peshi> peshiList;
    private final Activity activity;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date date;

    public PeshiAdopter(Context context,  List<Peshi> peshiList, FragmentActivity activity) {
        this.context = context;
        this.peshiList = peshiList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public PeshiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.peshi_list, viewGroup, false);
        return new PeshiAdopter.PeshiHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PeshiHolder holder, int position) {

        try {
            holder.txt_id.setText(peshiList.get(position).getFileNoString());
            holder.txt_title.setText(peshiList.get(position).getSubject());
            if(peshiList.get(position).getReceivedDate()!=null && !peshiList.get(position).getReceivedDate().isEmpty()){
                date = formatter.parse(peshiList.get(position).getReceivedDate());
                holder.txt_date.setText(Utility.formatDate(date,SELECTED_DATE_DISPLAY_FORMAT));
            }
            holder.txt_status.setText(peshiList.get(position).getStatus());
            holder.txt_department.setText(peshiList.get(position).getDepartment());
            holder.card_view.setOnClickListener(view -> {
                Intent intent= new Intent(context, ViewPeshiActivity.class);
                intent.putExtra("fileNo", peshiList.get(position).getFileNo());
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return peshiList.size();
    }


    public static class  PeshiHolder extends RecyclerView.ViewHolder {
        public CardView card_view;
        public TextView txt_id,txt_title,txt_date,txt_status,txt_department;
        public PeshiHolder(View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_department=itemView.findViewById(R.id.txt_department);
            txt_status=itemView.findViewById(R.id.txt_status);

        }
    }
}

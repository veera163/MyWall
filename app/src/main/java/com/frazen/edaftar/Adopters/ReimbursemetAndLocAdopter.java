package com.frazen.edaftar.Adopters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frazen.edaftar.Activity.ViewReimbrusementAndLocActivity;
import com.frazen.edaftar.Model.ReimbrusementAndLoc;
import com.frazen.edaftar.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ReimbursemetAndLocAdopter extends RecyclerView.Adapter<ReimbursemetAndLocAdopter.ReimbursemetHolder>{

    private final Context context;
    private final List<ReimbrusementAndLoc> reimbrusementList;
    private final Activity activity;

    public ReimbursemetAndLocAdopter(Context context, List<ReimbrusementAndLoc> reimbrusementList, FragmentActivity activity) {
        this.context = context;
        this.reimbrusementList = reimbrusementList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ReimbursemetHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.reimbrusement_list, viewGroup, false);
        return new ReimbursemetAndLocAdopter.ReimbursemetHolder(listItem);
    }
    @Override
    public void onBindViewHolder(@NonNull ReimbursemetHolder holder, int position) {

        String add1=reimbrusementList.get(position).getpAddress() != null ?reimbrusementList.get(position).getpAddress() : "";
        String add2=reimbrusementList.get(position).getHamlet() != null ?reimbrusementList.get(position).getHamlet() : "";
        String village=reimbrusementList.get(position).getVillage() != null ?reimbrusementList.get(position).getVillage() : "";
        String mandal=reimbrusementList.get(position).getMandal() != null ?reimbrusementList.get(position).getMandal() : "";
        String dist=reimbrusementList.get(position).getDistrict() != null ?reimbrusementList.get(position).getDistrict() : "";

        String address=add1+","+add2+","+village+","+mandal+","+dist;
        // String address=nearbyContacts.getAddress1().concat(" ,"+nearbyContacts.getAddress2()).concat(",").concat(nearbyContacts.getCity().concat(",").concat(nearbyContacts.getCounty().concat(",").concat(nearbyContacts.getCountry().concat(",")).concat(nearbyContacts.getZipPostalCode())));
        address= address.replaceAll(",,,"," ");
        address= address.replaceAll(",,",",");
        address= address.replaceFirst(",", "");

            holder.txt_id.setText(reimbrusementList.get(position).getFileNo());
            holder.txt_name.setText(reimbrusementList.get(position).getPatientName());
            if(reimbrusementList.get(position).getRelation()!=null && !reimbrusementList.get(position).getRelation().isEmpty()) {
                holder.txt_date.setText(String.format("%s %s", reimbrusementList.get(position).getRelation(), reimbrusementList.get(position).getRelativeName()));
            }
            else {
                holder.txt_date.setText(reimbrusementList.get(position).getRelativeName());
            }
            holder.txt_status.setText(reimbrusementList.get(position).getStatus());
            holder.txt_address.setText(address);
            holder.card_view.setOnClickListener(view -> {
                Intent intent= new Intent(context, ViewReimbrusementAndLocActivity.class);
                intent.putExtra("fileNo", reimbrusementList.get(position).getFileNo());
                intent.putExtra("type", reimbrusementList.get(position).getCmrfType());
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
            });
    }

    @Override
    public int getItemCount() {
        return reimbrusementList.size();
    }

    public static class  ReimbursemetHolder extends RecyclerView.ViewHolder {

        public CardView card_view;
        public TextView txt_id,txt_name,txt_date,txt_status,txt_address;
        public ReimbursemetHolder(View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_address=itemView.findViewById(R.id.txt_address);
            txt_status=itemView.findViewById(R.id.txt_status);

        }
    }
}

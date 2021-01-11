package com.frazen.edaftar.Adopters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frazen.edaftar.Model.SanctionedDetail;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SanctionedAdopter extends RecyclerView.Adapter<SanctionedAdopter.SanctionedHolder>{

    private final Context context;
    private final List<SanctionedDetail> sanctionedDetails;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date date;

    public SanctionedAdopter(Context context, List<SanctionedDetail> sanctionedDetails) {
        this.context = context;
        this.sanctionedDetails = sanctionedDetails;
    }

    @NonNull
    @Override
    public SanctionedHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.sanctioned_list, viewGroup, false);
        return new SanctionedAdopter.SanctionedHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SanctionedHolder holder, int position) {
        try {
            holder.txt_number.setText(sanctionedDetails.get(position).getSanctionedNo());
            holder.txt_type.setText(sanctionedDetails.get(position).getAdditionalFund());
            if(sanctionedDetails.get(position).getChequeDate()!=null && !sanctionedDetails.get(position).getChequeDate().isEmpty()) {
                date = formatter.parse(sanctionedDetails.get(position).getChequeDate());
                holder.txt_date.setText(Utility.formatDate(date,SELECTED_DATE_DISPLAY_FORMAT));
            }
            holder.txt_cheque_no.setText(sanctionedDetails.get(position).getChequeNo());
            holder.txt_treatment_amount.setText(String.valueOf(sanctionedDetails.get(position).getSanctionedAmt()));
            holder.txt_remarks.setText(sanctionedDetails.get(position).getSanctionRemarks());
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {

        return sanctionedDetails.size();
    }

    public static class  SanctionedHolder extends RecyclerView.ViewHolder {
        public CardView card_view;
        public TextView txt_number,txt_type,txt_cheque_no,txt_treatment_amount,txt_date,txt_remarks;
        public SanctionedHolder(View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            txt_number=itemView.findViewById(R.id.txt_number);
            txt_type=itemView.findViewById(R.id.txt_type);
            txt_cheque_no=itemView.findViewById(R.id.txt_cheque_no);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_remarks=itemView.findViewById(R.id.txt_remarks);
            txt_treatment_amount=itemView.findViewById(R.id.txt_treatment_amount);

        }
    }
}

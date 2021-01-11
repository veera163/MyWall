package com.frazen.edaftar.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Activity.MainActivity;
import com.frazen.edaftar.Activity.ReimbrusementAndLocActivity;
import com.frazen.edaftar.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LocFragment extends Fragment {
    BarChart barChart;
    TextView txt_pending,txt_sanctioned,txt_rejected,txt_total;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_loc, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.barchart);
        txt_pending = view.findViewById(R.id.txt_pending);
        txt_sanctioned = view.findViewById(R.id.txt_sanctioned);
        txt_rejected = view.findViewById(R.id.txt_rejected);
        txt_total = view.findViewById(R.id.txt_total);

        txt_pending.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), ReimbrusementAndLocActivity.class);
            intent.putExtra("type","reimbursement");
            intent.putExtra("value","Pending");
            intent.putExtra("sub","LOC");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });
        txt_sanctioned.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), ReimbrusementAndLocActivity.class);
            intent.putExtra("type","reimbursement");
            intent.putExtra("value","Sanctioned");
            intent.putExtra("sub","LOC");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });
        txt_rejected.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), ReimbrusementAndLocActivity.class);
            intent.putExtra("type","reimbursement");
            intent.putExtra("value","Rejected");
            intent.putExtra("sub","LOC");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });
        txt_total.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), ReimbrusementAndLocActivity.class);
            intent.putExtra("type","reimbursement");
            intent.putExtra("value","all");
            intent.putExtra("sub","LOC");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });

        if(MainActivity.dashBoardData!=null){
            txt_pending.setText(String.format(getString(R.string.txt_pending), MainActivity.dashBoardData.getTotalLOCPending()));
            txt_sanctioned.setText(String.format(getString(R.string.txt_sanctioned), MainActivity.dashBoardData.getTotalLOCSanctioned()));
            txt_rejected.setText(String.format(getString(R.string.txt_rejected), MainActivity.dashBoardData.getTotalLOCRejected()));
            txt_total.setText(String.format(getString(R.string.txt_total), MainActivity.dashBoardData.getTotalLOC()));

            //add colors to dataset
            ArrayList<Integer> colors = new ArrayList<>();
            colors.add(Color.rgb(119,3,119));
            colors.add(Color.rgb(0,128,0));
            colors.add(Color.rgb(255,0,0));
            ArrayList<BarEntry> entries = new ArrayList<>();
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLOCPending(), 0));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLOCSanctioned(), 1));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLOCRejected(), 2));
            BarDataSet bardataset = new BarDataSet(entries, "Cells");
            ArrayList<String> labels = new ArrayList<String>();
            labels.add("Pending");
            labels.add("Sanctioned");
            labels.add("Rejected");

            BarData data = new BarData(labels, bardataset);
            barChart.setData(data); // set the data and list of labels into chart
            // barChart.setDescription("Set Bar Chart Description Here");  // set the description
            bardataset.setColors(colors);
            // barChart.setMin
            barChart.animateY(4000);
            barChart.setDescription("");
            XAxis xAxis = barChart.getXAxis();
            xAxis.setSpaceBetweenLabels(1);
            barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                    Log.e("veera",labels.get(e.getXIndex()));
                    Intent intent= new Intent(getContext(), ReimbrusementAndLocActivity.class);
                    intent.putExtra("type","reimbursement");
                    intent.putExtra("value",labels.get(e.getXIndex()));
                    intent.putExtra("sub","LOC");
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }

                @Override
                public void onNothingSelected() {

                }
            });


        }else {

            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }

    }
}

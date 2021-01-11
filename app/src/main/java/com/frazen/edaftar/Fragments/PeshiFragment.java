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
import com.frazen.edaftar.Activity.PeshiActivity;
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

public class PeshiFragment extends Fragment {
    BarChart barChart;
    TextView txt_total,txt_pending,txt_approved,txt_lie,txt_return;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_peshi, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.barchart);
        txt_pending = view.findViewById(R.id.txt_pending);
        txt_approved = view.findViewById(R.id.txt_approved);
        txt_lie = view.findViewById(R.id.txt_lie);
        txt_return = view.findViewById(R.id.txt_return);
        txt_total = view.findViewById(R.id.txt_total);

        txt_pending.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), PeshiActivity.class);
            intent.putExtra("type","peshi");
            intent.putExtra("value","Under Process");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });
        txt_approved.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), PeshiActivity.class);
            intent.putExtra("type","peshi");
            intent.putExtra("value","Approved");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });
        txt_lie.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), PeshiActivity.class);
            intent.putExtra("type","peshi");
            intent.putExtra("value","Lie Over");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_return.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), PeshiActivity.class);
            intent.putExtra("type","peshi");
            intent.putExtra("value","Returned");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_total.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), PeshiActivity.class);
            intent.putExtra("type","peshi");
            intent.putExtra("value","all");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });

        if(MainActivity.dashBoardData!=null){
            txt_pending.setText(String.format(getString(R.string.txt_process), MainActivity.dashBoardData.getTotalPeshiFilesPending()));
            txt_approved.setText(String.format(getString(R.string.txt_approved), MainActivity.dashBoardData.getTotalPeshiFilesApproved()));
            txt_lie.setText(String.format(getString(R.string.txt_lie), MainActivity.dashBoardData.getTotalPeshiFilesLieOver()));
            txt_return.setText(String.format(getString(R.string.txt_return), MainActivity.dashBoardData.getTotalPeshiFilesReturned()));
            txt_total.setText(String.format(getString(R.string.txt_total), MainActivity.dashBoardData.getTotalPeshiFiles()));

            //add colors to dataset
            ArrayList<Integer> colors = new ArrayList<>();
            colors.add(Color.rgb(119,3,119));
            colors.add(Color.rgb(0,128,0));
            colors.add(Color.rgb(100,255,255));
            colors.add(Color.rgb(255,0,0));
            ArrayList<BarEntry> entries = new ArrayList<>();

            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalPeshiFilesPending(), 0));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalPeshiFilesApproved(), 1));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalPeshiFilesLieOver(), 2));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalPeshiFilesReturned(), 3));
            BarDataSet bardataset = new BarDataSet(entries, "Cells");
            ArrayList<String> labels = new ArrayList<String>();
            labels.add("Under Process");
            labels.add("Approved");
            labels.add("Lie Over");
            labels.add("Returned");

            BarData data = new BarData(labels, bardataset);
            barChart.setData(data); // set the data and list of labels into chart
            // barChart.setDescription("Set Bar Chart Description Here");  // set the description
            bardataset.setColors(colors);
            // barChart.setMin
            barChart.animateY(4000);
            barChart.setDescription("");
            XAxis xAxis = barChart.getXAxis();
            xAxis.setSpaceBetweenLabels(1);;
            barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                    Log.e("veera",labels.get(e.getXIndex()));
                    Intent intent= new Intent(getContext(), PeshiActivity.class);
                    intent.putExtra("type","peshi");
                    intent.putExtra("value",labels.get(e.getXIndex()));
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

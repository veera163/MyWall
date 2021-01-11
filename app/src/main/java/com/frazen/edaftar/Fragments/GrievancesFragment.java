package com.frazen.edaftar.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.frazen.edaftar.Activity.GrievanceActivity;
import com.frazen.edaftar.Activity.MainActivity;
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

public class GrievancesFragment extends Fragment {
    BarChart barChart;
    TextView txt_process,txt_sloved,txt_closed,txt_total;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_grievances, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        barChart = view.findViewById(R.id.barchart);
        txt_process = view.findViewById(R.id.txt_process);
        txt_sloved = view.findViewById(R.id.txt_sloved);
        txt_closed = view.findViewById(R.id.txt_closed);
        txt_total = view.findViewById(R.id.txt_total);
        txt_process.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), GrievanceActivity.class);
            intent.putExtra("type","grievance");
            intent.putExtra("value","Under Process");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_sloved.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), GrievanceActivity.class);
            intent.putExtra("type","grievance");
            intent.putExtra("value","Solved");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_closed.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), GrievanceActivity.class);
            intent.putExtra("type","grievance");
            intent.putExtra("value","Closed");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_total.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), GrievanceActivity.class);
            intent.putExtra("type","grievance");
            intent.putExtra("value","all");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });

        if(MainActivity.dashBoardData!=null){

            txt_process.setText(String.format(getString(R.string.txt_process), MainActivity.dashBoardData.getNoOfInProgress()));
            txt_sloved.setText(String.format(getString(R.string.txt_solved), MainActivity.dashBoardData.getNoOfSolved()));
            txt_closed.setText(String.format(getString(R.string.txt_closed), MainActivity.dashBoardData.getNoOnHold()));
            txt_total.setText(String.format(getString(R.string.txt_total), MainActivity.dashBoardData.getNoOfGrievances()));

            //add colors to dataset
            ArrayList<Integer> colors = new ArrayList<>();
           // colors.add(Color.rgb(0,0,255));
            colors.add(Color.rgb(119,3,119));
            colors.add(Color.rgb(0,128,0));
            colors.add(Color.rgb(255,0,0));

            ArrayList<BarEntry> entries = new ArrayList<>();
            entries.add(new BarEntry(MainActivity.dashBoardData.getNoOfInProgress(), 0));
            entries.add(new BarEntry(MainActivity.dashBoardData.getNoOfSolved(), 1));
            entries.add(new BarEntry(MainActivity.dashBoardData.getNoOnHold(), 2));
            BarDataSet bardataset = new BarDataSet(entries, "Cells");

            ArrayList<String> labels = new ArrayList<String>();
            labels.add("Under Process");
            labels.add("Solved");
            labels.add("Closed");

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
                    Intent intent= new Intent(getContext(), GrievanceActivity.class);
                    intent.putExtra("type","grievance");
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

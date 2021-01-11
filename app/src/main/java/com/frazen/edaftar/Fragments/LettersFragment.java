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

import com.frazen.edaftar.Activity.LettersAndNotesActivity;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LettersFragment extends Fragment {
    BarChart barChart;
    TextView txt_total,txt_new,txt_pending,txt_dispatched,txt_hold,txt_lie;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_letters, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.barchart);
        txt_pending = view.findViewById(R.id.txt_pending);
        txt_new = view.findViewById(R.id.txt_new);
        txt_lie = view.findViewById(R.id.txt_lie);
        txt_dispatched = view.findViewById(R.id.txt_dispatched);
        txt_hold = view.findViewById(R.id.txt_hold);
        txt_total = view.findViewById(R.id.txt_total);

        txt_pending.setOnClickListener(view1 -> {

            Intent intent= new Intent(getContext(), LettersAndNotesActivity.class);
            intent.putExtra("type","letter");
            intent.putExtra("value","Pending");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_new.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), LettersAndNotesActivity.class);
            intent.putExtra("type","letter");
            intent.putExtra("value","New");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_lie.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), LettersAndNotesActivity.class);
            intent.putExtra("type","letter");
            intent.putExtra("value","Lie Over");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_dispatched.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), LettersAndNotesActivity.class);
            intent.putExtra("type","letter");
            intent.putExtra("value","Dispatched");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_hold.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), LettersAndNotesActivity.class);
            intent.putExtra("type","letter");
            intent.putExtra("value","Hold");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        txt_total.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(), LettersAndNotesActivity.class);
            intent.putExtra("type","letter");
            intent.putExtra("value","all");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });

        if(MainActivity.dashBoardData!=null){
            txt_total.setText(String.format(getString(R.string.txt_total), MainActivity.dashBoardData.getTotalLettersAndNotes()));
            txt_new.setText(String.format(getString(R.string.txt_new), MainActivity.dashBoardData.getTotalLettersAndNotesNew()));
            txt_pending.setText(String.format(getString(R.string.txt_pending), MainActivity.dashBoardData.getTotalLettersAndNotesPending()));
            txt_dispatched.setText(String.format(getString(R.string.txt_dispatched), MainActivity.dashBoardData.getTotalLettersAndNotesDispatched()));
            txt_hold.setText(String.format(getString(R.string.txt_hold), MainActivity.dashBoardData.getTotalLettersAndNotesHold()));
            txt_lie.setText(String.format(getString(R.string.txt_lie), MainActivity.dashBoardData.getTotalLettersAndNotesLieOver()));

            //add colors to dataset
            ArrayList<Integer> colors = new ArrayList<>();
          //  colors.add(Color.rgb(0,0,255));
            colors.add(Color.rgb(135,206,235));
            colors.add(Color.rgb(119,3,119));
            colors.add(Color.rgb(0,128,0));
            colors.add(Color.rgb(255,165,0));
            colors.add(Color.rgb(255,0,0));
            ArrayList<BarEntry> entries = new ArrayList<>();

            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLettersAndNotesNew(), 0));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLettersAndNotesPending(), 1));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLettersAndNotesDispatched(), 2));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLettersAndNotesHold(), 3));
            entries.add(new BarEntry(MainActivity.dashBoardData.getTotalLettersAndNotesLieOver(), 4));

            BarDataSet bardataset = new BarDataSet(entries, "Cells");
            ArrayList<String> labels = new ArrayList<String>();
            labels.add("New");
            labels.add("Pending");
            labels.add("Dispatched");
            labels.add("Hold");
            labels.add("Lie Over");

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
                    Intent intent= new Intent(getContext(), LettersAndNotesActivity.class);
                    intent.putExtra("type","letter");
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

package com.frazen.edaftar.Adopters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.frazen.edaftar.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class SpinnerAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final int textViewResourceId;
    private ArrayList<String> Strings;

    public SpinnerAdapter(Context context, int textViewResourceId, ArrayList<String> Strings) {
        super(context, textViewResourceId, Strings);

        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.Strings = Strings;
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void setStrings(ArrayList<String> Strings) {
        this.Strings = Strings;
    }

    private static class SpinnerViewHolder {
        TextView textView;
    }

    @Override
    public int getCount() {
        return Strings.size();
    }

    @Override
    public String getItem(int position) {
        return Strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup viewGroup) {
        try {
            SpinnerViewHolder spinnerViewHolder;

            if (convertView == null) {
                convertView = View.inflate(context, textViewResourceId, null);

                spinnerViewHolder = new SpinnerViewHolder();

                spinnerViewHolder.textView = convertView.findViewById(R.id.spinner_row_text);

                convertView.setTag(spinnerViewHolder);

            } else {
                spinnerViewHolder = (SpinnerViewHolder) convertView.getTag();
            }

            spinnerViewHolder.textView.setText(getItem(position));

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert convertView != null;
        return convertView;
    }
}

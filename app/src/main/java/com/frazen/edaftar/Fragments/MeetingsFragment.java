package com.frazen.edaftar.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frazen.edaftar.Activity.CreateMeetingActivity;
import com.frazen.edaftar.Adopters.MeetingsListAdopter;
import com.frazen.edaftar.Listeners.ClickListener;
import com.frazen.edaftar.Listeners.RecyclerTouchListener;
import com.frazen.edaftar.Model.SelectDayMeeting;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import noman.weekcalendar.WeekCalendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MeetingsFragment extends Fragment implements View.OnClickListener {

    ImageView custom_date_icon,calendar_left_nav_arrow,calendar_right_nav_arrow;
    TextView selected_date_text_view;
    private String selectedDateStr = "", selectedDateDisplayStr = "";
    public static final String SERVER_DATE_FORMAT = "y-MM-dd";
    public static final String CALENDAR_DATE_DISPLAY_FORMAT = "E MMMM dd, y";

    private Date currentDate, selectedDate;
    private WeekCalendar week_calendar;
    RadioButton rb_calendar_day_view,rb_calendar_week_view;
    private int DAY_VIEW = 0;
    private int WEEK_VIEW = 1;
    private int currentOperation = DAY_VIEW; // 0 - Day View & 1 - Week View
    private ProgressDialog progressDialog;
   public static List<SelectDayMeeting> selectDayMeetings;
    private RecyclerView meetings_list_view;
    MeetingsListAdopter listAdopter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_meetings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Objects.requireNonNull(getActivity()).setTitle("Meetings");
        selectDayMeetings= new ArrayList<>();
        currentDate = new Date();
        custom_date_icon = view.findViewById(R.id.custom_date_icon);
        calendar_left_nav_arrow = view.findViewById(R.id.calendar_left_nav_arrow);
        calendar_right_nav_arrow = view.findViewById(R.id.calendar_right_nav_arrow);

        week_calendar = view.findViewById(R.id.week_calendar);
        selected_date_text_view = view.findViewById(R.id.calendar_selected_date_text_view);

        rb_calendar_day_view= view.findViewById(R.id.rb_calendar_day_view);
        rb_calendar_week_view= view.findViewById(R.id.rb_calendar_week_view);
        rb_calendar_day_view.setOnClickListener(this);
        rb_calendar_week_view.setOnClickListener(this);
        calendar_left_nav_arrow.setOnClickListener(this::loadPastEvents);
        calendar_right_nav_arrow.setOnClickListener(this::loadFutureEvents);

        meetings_list_view = view.findViewById(R.id.meetings_list_view);
        meetings_list_view.setHasFixedSize(true);
        meetings_list_view.addItemDecoration(new DividerItemDecoration(meetings_list_view.getContext(), DividerItemDecoration.VERTICAL));
        meetings_list_view.setAdapter(listAdopter);
        meetings_list_view.setLayoutManager(new LinearLayoutManager(getContext()));

        meetings_list_view.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), meetings_list_view, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent= new Intent(getContext(), CreateMeetingActivity.class);
                intent.putExtra("SelectedCalendarDate",selectedDateStr);
                intent.putExtra("CALENDAR_EVENT_EDITING", true);
                intent.putExtra("CALENDAR_EVENT_ID", selectDayMeetings.get(position).getId());
                intent.putExtra("position",position);
               // intent.putExtra("CALENDAR_EVENT_DETAILS",selectDayMeeting);
                Objects.requireNonNull(getActivity()).startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent= new Intent(getContext(), CreateMeetingActivity.class);
            intent.putExtra("SelectedCalendarDate",selectedDateStr);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });

        custom_date_icon.setOnClickListener(this::showDatePicker);
        showTodayEvents();

    }
    private void showDatePicker(View view) {
        try {
            String preSelectedDate = selectedDateStr;
            if (selectedDateStr.isEmpty()) {
                preSelectedDate = formatDate(new Date(), SERVER_DATE_FORMAT);
            }
            String[] dateValues = preSelectedDate.split("-");
            DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()), android.R.style.Theme_DeviceDefault_Light_Dialog, (datePicker, year, monthOfYear, dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                selectedDate = calendar.getTime();
                DateTime dateTime = new DateTime(selectedDate);
                week_calendar.setSelectedDate(dateTime);

               getEvents(selectedDate);

            }, Integer.parseInt(dateValues[0]), Integer.parseInt(dateValues[1]) - 1, Integer.parseInt(dateValues[2]));

            datePickerDialog.setCancelable(false);
            datePickerDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getEvents(Date date) {

     //   resetValues();
        selectedDate = date;
        selectedDateDisplayStr = formatDate(date, CALENDAR_DATE_DISPLAY_FORMAT);
        selectedDateStr = formatDate(date, SERVER_DATE_FORMAT);
        selected_date_text_view.setText(selectedDateDisplayStr);
        getMeetingData(selectedDateStr);
    }

    private void getMeetingData(String selectedDateStr) {

        String url = AppServerService.baseURL+"meeting/day/"+selectedDateStr;
        progressDialog = ProgressDialog.show(getContext(), "Please wait.",
                "Service Loading ...!", true);
        Call<List<SelectDayMeeting>> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getDayMeetings(url);
        listCall.enqueue(new Callback<List<SelectDayMeeting>>() {
            @Override
            public void onResponse(@NonNull Call<List<SelectDayMeeting>> call, @NonNull Response<List<SelectDayMeeting>> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();

                    if(response.body()!=null && !response.body().isEmpty()){
                        selectDayMeetings= response.body();
                        selected_date_text_view.setText(String.format("%s (%d Events)", selectedDateDisplayStr, selectDayMeetings.size()));
                        int s=selectDayMeetings.size();
                        listAdopter = new MeetingsListAdopter(getContext(),selectDayMeetings);
                        meetings_list_view.setHasFixedSize(true);
                        meetings_list_view.setItemViewCacheSize(s);
                        meetings_list_view.setDrawingCacheEnabled(true);
                        meetings_list_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        meetings_list_view.setAdapter(listAdopter);
                    }else {
                        meetings_list_view.setAdapter(null);
                        Toast.makeText(getContext(), "No Records Found", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    progressDialog.dismiss();
                    if(response.code()==401){
                        Toast.makeText(getContext(), "Session Timeout. Please Login : "+ response.code(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Meetings : "+ response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SelectDayMeeting>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void resetValues() {

        selectedDateStr = "";

        if (selectDayMeetings != null) {
            selectDayMeetings.clear();
            selectDayMeetings = null;
        }

        meetings_list_view.setAdapter(null);

    }

    public static String formatDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public void showTodayEvents() {
        week_calendar.setVisibility(View.GONE);

        getEvents(currentDate);
    }

    public void showWeeklyEvents() {
        week_calendar.setVisibility(View.VISIBLE);
        week_calendar.reset();
        week_calendar.setOnDateClickListener(dateTime -> {
            Date selectedWeekDate = dateTime.toDate();
            getEvents(selectedWeekDate);
        });
        //We are loading Today's date events by default when we switch to week view.
        getEvents(currentDate);
    }

    public void loadPastEvents(View view) {
        try {
            if (currentOperation == DAY_VIEW) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selectedDate);
                calendar.add(Calendar.DATE, -1);

                getEvents(calendar.getTime());
            } else if (currentOperation == WEEK_VIEW) {
                week_calendar.moveToPreviousWeek();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFutureEvents(View view) {
        try {
            if (currentOperation == DAY_VIEW) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selectedDate);
                calendar.add(Calendar.DATE, 1);

                getEvents(calendar.getTime());
            } else if (currentOperation == WEEK_VIEW) {
                week_calendar.moveToNextWeek();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Checking which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_calendar_day_view:
                if (checked)
                    currentOperation = DAY_VIEW;
                break;
            case R.id.rb_calendar_week_view:
                if (checked)
                    currentOperation = WEEK_VIEW;
                break;
        }

        if (currentOperation == DAY_VIEW) {
            showTodayEvents();
        } else {
            showWeeklyEvents();
        }
    }
}
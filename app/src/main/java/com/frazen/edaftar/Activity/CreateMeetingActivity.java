package com.frazen.edaftar.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.frazen.edaftar.Adopters.SpinnerAdapter;
import com.frazen.edaftar.Fragments.MeetingsFragment;
import com.frazen.edaftar.Model.InitMeetingDetails;
import com.frazen.edaftar.Model.MeetingInfo;
import com.frazen.edaftar.Model.NewCalendarEvent;
import com.frazen.edaftar.Model.Participant;
import com.frazen.edaftar.Model.SelectDayMeeting;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.Utility;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMeetingActivity extends AppCompatActivity {

    TextView ed_create,calendar_event_start_date, calendar_event_start_time, calendar_event_end_time;
    EditText ed_title,ed_address,ed_department,calendar_event_notes;
    private SimpleDateFormat dateParser, dateTimeParser;
    private String selectedCalendarDate, selectedCalendarEventId, serverDate,currentDate, preSelectedDate = "", preSelectedTime = "", currentTime = "",serverStarTime = "",serverendTime = "";
    private NewCalendarEvent newCalendarEvent = null;
    Button create_contact_reset_button,create_contact_save_button;
    private ProgressDialog progressDialog;
    Spinner sp_status,sp_meeting;
    ArrayList<String> status_array,meeting_array;
    private SpinnerAdapter meetcategoriesSpinnerAdapter,statuscategoriesSpinnerAdapter;
    String status_type,meeting_type,title,startDate,stratTime,endDate,endTime,address,department,createBy,notes;
    public static final String SERVER_DATE_FORMAT = "y-MM-dd";
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "MM/dd/y";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String CALENDAR_SELECTED_DATE_WITH_TIME_FORMAT = "y-MM-dd HH:mm";
    public static final String CALENDAR_NEW_EVENT_DATE_TIME_FORMAT = "MM/dd/y HH:mm";
  //  public static final String CALENDAR_EDIT_EVENT_DATE_TIME_FORMAT = "MM/dd/y hh:mm a";
    TextView txt_title;
    int meetingId,pos;
    private boolean isEventEditing = false;
    private SelectDayMeeting EventDetails = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        txt_title=findViewById(R.id.txt_title);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            selectedCalendarDate = bundle.getString("SelectedCalendarDate");
            isEventEditing = bundle.getBoolean("CALENDAR_EVENT_EDITING");
        }
        if (isEventEditing) {
            if (bundle != null) {
                selectedCalendarEventId = bundle.getString("CALENDAR_EVENT_ID");
                pos = bundle.getInt("position");

                if(MeetingsFragment.selectDayMeetings!=null && !MeetingsFragment.selectDayMeetings.isEmpty()){
                    EventDetails = MeetingsFragment.selectDayMeetings.get(pos);
                }
            }
        }
        currentDate = Utility.formatDate(new Date(),SELECTED_DATE_DISPLAY_FORMAT);
        serverDate = Utility.formatDate(new Date(),SERVER_DATE_FORMAT);

        dateParser = new SimpleDateFormat(CALENDAR_SELECTED_DATE_WITH_TIME_FORMAT);
        dateTimeParser = new SimpleDateFormat(CALENDAR_NEW_EVENT_DATE_TIME_FORMAT);

        newCalendarEvent = new NewCalendarEvent();

        calendar_event_start_date =  findViewById(R.id.calendar_event_start_date);
        calendar_event_start_time =  findViewById(R.id.calendar_event_start_time);
        calendar_event_end_time =  findViewById(R.id.calendar_event_end_time);
        ed_title=findViewById(R.id.ed_title);
        ed_address=findViewById(R.id.ed_address);
        ed_department=findViewById(R.id.ed_department);
        calendar_event_notes=findViewById(R.id.calendar_event_notes);
        ed_create=findViewById(R.id.ed_create);
        if(MyWall.getUSERNAME().isEmpty()){
            ed_create.setText(MyWall.getLoginId());
        }
        else {
            ed_create.setText(MyWall.getUSERNAME());

        }
        create_contact_reset_button=findViewById(R.id.create_contact_reset_button);
        create_contact_save_button=findViewById(R.id.create_contact_save_button);
        if (isEventEditing) {
            txt_title.setText(R.string.update_meeting);
            create_contact_save_button.setText(R.string.update);
        }
        else {
            txt_title.setText(R.string.create_meeting);
            create_contact_save_button.setText(R.string.save);
        }

        sp_meeting = findViewById(R.id.sp_meeting);
        sp_status = findViewById(R.id.sp_status);

        status_array= new ArrayList<>();
       // status_array.add("Select");
        statuscategoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, status_array);
        statuscategoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_status.setAdapter(statuscategoriesSpinnerAdapter);
        //  sp_status.setSelection(0, false);
        sp_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status_type = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        meeting_array= new ArrayList<>();
      //  meeting_array.add("Select");
        meetcategoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, meeting_array);
        meetcategoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_meeting.setAdapter(meetcategoriesSpinnerAdapter);
        //  sp_petitioner.setSelection(0, false);
        sp_meeting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meeting_type = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        create_contact_reset_button.setOnClickListener(view -> {
            onBackPressed();
        });
        create_contact_save_button.setOnClickListener(view -> {

                title=ed_title.getText().toString().trim();
                address=ed_address.getText().toString().trim();
                department=ed_department.getText().toString().trim();
                createBy=ed_create.getText().toString().trim();
                notes=calendar_event_notes.getText().toString().trim();

                boolean isValid = true;
                View focusView = null;
                ed_title.setError(null);
                if (TextUtils.isEmpty(title)) {
                    ed_title.setError(getString(R.string.error_field_required));
                    focusView = ed_title;
                    isValid = false;
                }
               /* else if (meeting_array.equals(meeting_array.get(0))) {
                    Toast.makeText(this, "Please Select Meeting Type", Toast.LENGTH_SHORT).show();
                    focusView =sp_meeting; ;
                    isValid = false;
                }
*/
                else  if (TextUtils.isEmpty(address)) {
                    ed_address.setError(getString(R.string.error_field_required));
                    focusView = ed_address;
                    isValid = false;
                }
                else  if (TextUtils.isEmpty(department)) {
                    ed_department.setError(getString(R.string.error_field_required));
                    focusView = ed_department;
                    isValid = false;
                }
               /* else if (status_type.equals(status_array.get(0))) {
                    Toast.makeText(this, "Please Select Status", Toast.LENGTH_SHORT).show();
                    focusView =sp_status; ;
                    isValid = false;
                }*/
                else  if (TextUtils.isEmpty(createBy)) {
                    ed_create.setError(getString(R.string.error_field_required));
                    focusView = ed_create;
                    isValid = false;
                }
                else  if (TextUtils.isEmpty(notes)) {
                    calendar_event_notes.setError(getString(R.string.error_field_required));
                    focusView = calendar_event_notes;
                    isValid = false;
                }

                if (isValid){
                    if (isEventEditing) {
                       // Toast.makeText(this, "Edit Meeting", Toast.LENGTH_SHORT).show();
                        UpdateMeeting(title,meeting_type,address,department,status_type,createBy,notes);
                    }
                    else {
                        createMeeting(title,meeting_type,address,department,status_type,createBy,notes);
                    }
                }
                else {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                }
        });

        calendar_event_start_date.setOnClickListener(this::showDatePicker);
        calendar_event_start_time.setOnClickListener(v ->
                showTimePicker(v, "startTime"));

        calendar_event_end_time.setOnClickListener(v ->
                showTimePicker(v,"endTime"));


        initMeetingDetails();
    }

    private void UpdateMeeting(String title, String meeting_type, String address, String department,
                               String status_type, String createBy, String notes) {
        List<Participant> participantList=new ArrayList<>();
          MeetingInfo meetingInfo= new MeetingInfo();
          meetingInfo.setAgenda(notes);
          meetingInfo.setCreatedBy(createBy);
          meetingInfo.setStatus(status_type);
          meetingInfo.setDepartment(department);
          meetingInfo.setLocation(address);
          meetingInfo.setType(meeting_type);
          meetingInfo.setTitle(title);
          meetingInfo.setId(EventDetails.getId());
          meetingInfo.setMeetingId(EventDetails.getMeetingId());
          meetingInfo.setDate(serverDate);
          meetingInfo.setParticipants(participantList);
          meetingInfo.setStartTime(serverStarTime);
          meetingInfo.setEndTime(serverendTime);
          Log.e("veera",meetingInfo.toString());
        progressDialog = ProgressDialog.show(CreateMeetingActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<Boolean> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).updateMeeting(meetingInfo);
        listCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Boolean aBoolean= response.body();
                        if(aBoolean){
                            Toast.makeText(CreateMeetingActivity.this, "Successfully Updated...", Toast.LENGTH_SHORT).show();
                            String time = meetingInfo.getDate() + " " + meetingInfo.getStartTime();
                            Log.e("veera",time);
                            SimpleDateFormat curFormater = new SimpleDateFormat("y-MM-dd HH:mm");
                            Date dateObj;
                            try {
                                dateObj = curFormater.parse(time);
                                Calendar calendar = Calendar.getInstance();
                                if (dateObj != null) {
                                    calendar.setTime(dateObj);
                                }
                                addReminderInCalendar(calendar.getTimeInMillis(),meetingInfo);
                                Intent home = new Intent(CreateMeetingActivity.this, MainActivity.class);
                                home.putExtra("type","meeting");
                                startActivity(home);
                                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(CreateMeetingActivity.this, " "+aBoolean, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(CreateMeetingActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(CreateMeetingActivity.this, "Update Meeting"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMeetingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateCalendarEventDetails(SelectDayMeeting eventDetails) {
        try {
            if (eventDetails != null) {

                newCalendarEvent.setEventDate(selectedCalendarDate);
                // We are adding Locale to resolve ParseException (Unparseable date error)
                DateFormat editEventDateTimeParser = new SimpleDateFormat(CALENDAR_SELECTED_DATE_WITH_TIME_FORMAT, Locale.getDefault());
                Date eventStartDateTime = editEventDateTimeParser.parse(eventDetails.getDate()+" "+eventDetails.getStartTime());
                Date eventEndDateTime = editEventDateTimeParser.parse(eventDetails.getDate()+ " "+eventDetails.getEndTime());

                // Updating Start Date in UI
                Calendar calendar = Calendar.getInstance();
                if (eventStartDateTime != null) {
                    calendar.setTime(eventStartDateTime);
                }

                newCalendarEvent.setStartDateObj(calendar.getTime());
                newCalendarEvent.setStartDate(Utility.formatDate(calendar.getTime(), SELECTED_DATE_DISPLAY_FORMAT));
                serverDate=Utility.formatDate(calendar.getTime(), SERVER_DATE_FORMAT);

                currentTime = String.format("%tH:%tM", calendar, calendar);
                serverStarTime= String.format("%tH:%tM", calendar, calendar);
                newCalendarEvent.setStartTime(currentTime);

                currentTime = String.format("%tI:%tM %tp", calendar, calendar, calendar);
                currentTime = currentTime.toUpperCase().replace(".", ""); // in order to convert a.m. to AM
                newCalendarEvent.setStartTimeText(currentTime);

                // Updating End Date in UI
                if (eventEndDateTime != null) {
                    calendar.setTime(eventEndDateTime);
                }

                newCalendarEvent.setEndDateObj(calendar.getTime());
                newCalendarEvent.setEndDate(Utility.formatDate(calendar.getTime(), SELECTED_DATE_DISPLAY_FORMAT));

                currentTime = String.format("%tH:%tM", calendar, calendar);
                serverendTime= String.format("%tH:%tM", calendar, calendar);
                newCalendarEvent.setEndTime(currentTime);
                currentTime = String.format("%tI:%tM %tp", calendar, calendar, calendar);
                currentTime = currentTime.toUpperCase().replace(".", ""); // in order to convert a.m. to AM
                newCalendarEvent.setEndTimeText(currentTime);

            }
            if (eventDetails != null) {
                updateUIWithLatestValues(newCalendarEvent,eventDetails);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void updateUIWithLatestValues(NewCalendarEvent newCalendarEvent, SelectDayMeeting eventDetails) {

        Log.e("veera",meeting_array.toString());

        calendar_event_start_date.setText(newCalendarEvent.getStartDate());
        calendar_event_start_time.setText(newCalendarEvent.getStartTimeText());
        calendar_event_end_time.setText(newCalendarEvent.getEndTimeText());
        ed_title.setText(eventDetails.getTitle());
        int spinnerPosition = meetcategoriesSpinnerAdapter.getPosition(eventDetails.getType());
        //set the default according to value
        sp_meeting.setSelection(spinnerPosition);
        calendar_event_notes.setText(eventDetails.getAgenda());
        ed_address.setText(eventDetails.getLocation());
        ed_create.setText(eventDetails.getCreatedBy());
        ed_department.setText(eventDetails.getDepartment());
        int spPosition = statuscategoriesSpinnerAdapter.getPosition(eventDetails.getStatus());
        //set the default according to value
        sp_status.setSelection(spPosition);
    }

    private void createMeeting(String title, String meeting_type, String address, String department,
                               String status_type, String createBy, String notes) {
        MeetingInfo meetingInfo= new MeetingInfo();
        meetingInfo.setTitle(title);
        meetingInfo.setType(meeting_type);
        meetingInfo.setMeetingId(meetingId);
        meetingInfo.setDate(serverDate);
        meetingInfo.setStartTime(serverStarTime);
        meetingInfo.setEndTime(serverendTime);
        meetingInfo.setLocation(address);
        meetingInfo.setDepartment(department);
        meetingInfo.setStatus(status_type);
        meetingInfo.setCreatedBy(createBy);
        meetingInfo.setAgenda(notes);
        Log.e("veera",meetingInfo.toString());
        progressDialog = ProgressDialog.show(CreateMeetingActivity.this, "Please wait.",
                "Service Loading ...!", true);

        Call<Boolean> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).createMeeting(meetingInfo);
        listCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Boolean aBoolean= response.body();
                        if(aBoolean){
                            Toast.makeText(CreateMeetingActivity.this, "Successfully Created...", Toast.LENGTH_SHORT).show();
                            String time = meetingInfo.getDate() + " " + meetingInfo.getStartTime();
                            Log.e("veera",time);
                            SimpleDateFormat curFormater = new SimpleDateFormat("y-MM-dd HH:mm");
                            Date dateObj;
                            try {
                                dateObj = curFormater.parse(time);
                                Calendar calendar = Calendar.getInstance();
                                if (dateObj != null) {
                                    calendar.setTime(dateObj);
                                }
                                addReminderInCalendar(calendar.getTimeInMillis(),meetingInfo);
                                Intent home = new Intent(CreateMeetingActivity.this, MainActivity.class);
                                home.putExtra("type","meeting");
                                startActivity(home);
                                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(CreateMeetingActivity.this, ""+ false, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(CreateMeetingActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(CreateMeetingActivity.this, "Create Meeting"+response.code(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMeetingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addReminderInCalendar(long timeInMillis, MeetingInfo meetingInfo) {

        String description=meetingInfo.getAgenda();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        Uri EVENTS_URI = Uri.parse(getCalendarUriBase(true) + "events");
        ContentResolver cr = getContentResolver();
        TimeZone timeZone = TimeZone.getDefault();
        /* Inserting an event in calendar. */
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events.TITLE,"E-daftar : "+meetingInfo.getTitle());
        values.put(CalendarContract.Events.DESCRIPTION, description);

        values.put(CalendarContract.Events.EVENT_LOCATION,meetingInfo.getLocation());
        values.put(CalendarContract.Events.EVENT_COLOR, Color.parseColor("#000000"));
        values.put(CalendarContract.Events.ALL_DAY, 0);
        // event starts at 11 minutes from now
        // values.put(CalendarContract.Events.DTSTART, calendar.getTimeInMillis() + 11 * 60 * 1000);

        values.put(CalendarContract.Events.DTSTART, calendar.getTimeInMillis());
        // ends 60 minutes from now
        values.put(CalendarContract.Events.DTEND, calendar.getTimeInMillis() + 60 * 60 * 1000);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
        values.put(CalendarContract.Events.HAS_ALARM, 1);

        Uri event = cr.insert(EVENTS_URI, values);
        // Display event id
        if (event != null) {
            Toast.makeText(getApplicationContext(), "Event added :: ID :: " + event.getLastPathSegment(), Toast.LENGTH_SHORT).show();
        }

        /* Event: Reminder(with alert) Adding reminder to event */

        Uri REMINDERS_URI = Uri.parse(getCalendarUriBase(true) + "reminders");
        values = new ContentValues();
        if (event != null) {
            values.put(CalendarContract.Reminders.EVENT_ID, Long.parseLong(Objects.requireNonNull(event.getLastPathSegment())));
        }
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        values.put(CalendarContract.Reminders.MINUTES, 30);
        cr.insert(REMINDERS_URI, values);
    }

    private String getCalendarUriBase(boolean eventUri) {
        Uri calendarURI = null;
        try {
            if (android.os.Build.VERSION.SDK_INT <= 7) {
                calendarURI = (eventUri) ? Uri.parse("content://calendar/") : Uri.parse("content://calendar/calendars");
            } else {
                calendarURI = (eventUri) ? Uri.parse("content://com.android.calendar/") : Uri
                        .parse("content://com.android.calendar/calendars");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendarURI.toString();
    }

    private void initMeetingDetails() {
        String url = String.format("%smeeting/init", AppServerService.baseURL);
        Log.e("veera",url);
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Service Loading ...!", true);
        Call<InitMeetingDetails> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getInitMeeting(url);
        listCall.enqueue(new Callback<InitMeetingDetails>() {
            @Override
            public void onResponse(@NonNull Call<InitMeetingDetails> call, @NonNull Response<InitMeetingDetails> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        InitMeetingDetails initMeetingDetails= response.body();
                        Log.e("veera",initMeetingDetails.toString());
                        meetingId=initMeetingDetails.getMeetingIdIdx();
                        if(initMeetingDetails.getStatusList()!=null && !initMeetingDetails.getStatusList().isEmpty()){
                            for (int i=0;i<initMeetingDetails.getStatusList().size();i++){
                                status_array.add(initMeetingDetails.getStatusList().get(i).getListItem());
                            }
                            statuscategoriesSpinnerAdapter = new SpinnerAdapter(CreateMeetingActivity.this, R.layout.spinner_row_item, status_array);
                            statuscategoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_status.setAdapter(statuscategoriesSpinnerAdapter);
                            String myString = "Scheduled"; //the value you want the position for
                            int spinnerPosition = statuscategoriesSpinnerAdapter.getPosition(myString);
                            //set the default according to value
                            sp_status.setSelection(spinnerPosition);
                        }

                        if(initMeetingDetails.getTypesList()!=null && !initMeetingDetails.getTypesList().isEmpty()){
                            for (int i=0;i<initMeetingDetails.getTypesList().size();i++){
                                meeting_array.add(initMeetingDetails.getTypesList().get(i).getListItem());
                            }
                            meetcategoriesSpinnerAdapter = new SpinnerAdapter(CreateMeetingActivity.this, R.layout.spinner_row_item, meeting_array);
                            meetcategoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_meeting.setAdapter(meetcategoriesSpinnerAdapter);
                            String myString = "Official"; //the value you want the position for
                            int spinnerPosition = meetcategoriesSpinnerAdapter.getPosition(myString);
                            //set the default according to value
                            sp_meeting.setSelection(spinnerPosition);
                        }

                        if (isEventEditing) {
                            updateCalendarEventDetails(EventDetails);
                        } else {
                            prePopulateDateTimeFields();
                        }

                    }
                    else {
                        Toast.makeText(CreateMeetingActivity.this,"No Data Found", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(CreateMeetingActivity.this," "+response.code(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(@NonNull Call<InitMeetingDetails> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMeetingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void prePopulateDateTimeFields() {
        try {
            Calendar calendar = Calendar.getInstance();

            Date selectedDate = dateParser.parse(String.format("%s %tH:%tM", selectedCalendarDate, calendar, calendar));
            calendar.setTime(selectedDate);

            // Rounding time to nearest 30 minutes.
            int mMinute = calendar.get(Calendar.MINUTE);
            if (mMinute < 30) {
                calendar.set(Calendar.MINUTE, 30);
            } else {
                calendar.add(Calendar.HOUR, 1);
                calendar.set(Calendar.MINUTE, 0);
            }

            newCalendarEvent.setStartDateObj(calendar.getTime());
            newCalendarEvent.setStartDate(Utility.formatDate(calendar.getTime(), SELECTED_DATE_DISPLAY_FORMAT));
            serverDate=Utility.formatDate(calendar.getTime(), SERVER_DATE_FORMAT);

            currentTime = String.format("%tH:%tM", calendar, calendar);
            serverStarTime= String.format("%tH:%tM", calendar, calendar);
            newCalendarEvent.setStartTime(currentTime);

            currentTime = String.format("%tI:%tM %tp", calendar, calendar, calendar);
            currentTime = currentTime.toUpperCase().replace(".", ""); // in order to convert a.m. to AM
            newCalendarEvent.setStartTimeText(currentTime);

            calendar_event_start_date.setText(newCalendarEvent.getStartDate());
            calendar_event_start_time.setText(newCalendarEvent.getStartTimeText());

            // Calculating End Time i.e. adding 1 Hour to Start Time.
            calendar.add(Calendar.HOUR_OF_DAY, 1);

            newCalendarEvent.setEndDateObj(calendar.getTime());
            newCalendarEvent.setEndDate(Utility.formatDate(calendar.getTime(), SELECTED_DATE_DISPLAY_FORMAT));
            serverDate=Utility.formatDate(calendar.getTime(), SERVER_DATE_FORMAT);

            currentTime = String.format("%tH:%tM", calendar, calendar);
            serverendTime= String.format("%tH:%tM", calendar, calendar);

            newCalendarEvent.setEndTime(currentTime);

            currentTime = String.format("%tI:%tM %tp", calendar, calendar, calendar);
            currentTime = currentTime.toUpperCase().replace(".", ""); // in order to convert a.m. to AM
            newCalendarEvent.setEndTimeText(currentTime);

            calendar_event_end_time.setText(newCalendarEvent.getEndTimeText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showTimePicker(View v, String type) {

        try {
            if (type.equals("startTime"))
                preSelectedTime = newCalendarEvent.getStartTime();
            else if (type.equals("endTime"))
                preSelectedTime = newCalendarEvent.getEndTime();
            if (preSelectedTime.isEmpty()) {
                Calendar calendar = Calendar.getInstance();
                preSelectedTime = Utility.formatDate(calendar.getTime(), TIME_FORMAT);
            }

            String[] timeValues = preSelectedTime.split(":");

            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateMeetingActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    try {
                        preSelectedTime = String.format("%02d:%02d", hourOfDay, minute);

                        String amPm = hourOfDay > 11 ? "PM" : "AM";

                        //Make the 24 hour time format to 12 hour time format
                        if (hourOfDay > 12)
                            hourOfDay = hourOfDay - 12;

                        String selectedTimeText = String.format("%02d:%02d %s", hourOfDay, minute, amPm);

                        if (type.equals("startTime")) {
                            calendar_event_start_time.setText(selectedTimeText);
                            newCalendarEvent.setStartTimeText(selectedTimeText);
                            newCalendarEvent.setStartTime(preSelectedTime);
                            serverStarTime= preSelectedTime;

                            Date startTimeDate = dateTimeParser.parse(newCalendarEvent.getStartDate() + " " + newCalendarEvent.getStartTime());
                            Date endTimeDate = dateTimeParser.parse(newCalendarEvent.getEndDate() + " " + newCalendarEvent.getEndTime());

                            if (startTimeDate != null && startTimeDate.after(endTimeDate)) {
                                calendar_event_end_time.setText(newCalendarEvent.getStartTimeText());
                                newCalendarEvent.setEndTimeText(newCalendarEvent.getStartTimeText());
                                newCalendarEvent.setEndTime(newCalendarEvent.getStartTime());
                            }

                        } else if (type.equals("endTime")) {
                            calendar_event_end_time.setText(selectedTimeText);
                            newCalendarEvent.setEndTimeText(selectedTimeText);
                            newCalendarEvent.setEndTime(preSelectedTime);
                            serverendTime= preSelectedTime;

                            Date startTimeDate = dateTimeParser.parse(newCalendarEvent.getStartDate() + " " + newCalendarEvent.getStartTime());
                            Date endTimeDate = dateTimeParser.parse(newCalendarEvent.getEndDate() + " " + newCalendarEvent.getEndTime());

                            if (startTimeDate != null && startTimeDate.after(endTimeDate)) {
                                calendar_event_start_time.setText(newCalendarEvent.getEndTimeText());
                                newCalendarEvent.setStartTimeText(newCalendarEvent.getEndTimeText());
                                newCalendarEvent.setStartTime(newCalendarEvent.getEndTime());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, Integer.parseInt(timeValues[0]), Integer.parseInt(timeValues[1]), false);

            timePickerDialog.setCancelable(false);
            timePickerDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDatePicker(View v) {
        try {
            preSelectedDate = newCalendarEvent.getEndDate();
            if (preSelectedDate.isEmpty()) {
                preSelectedDate = currentDate;
            }
            String[] dateValues = preSelectedDate.split("/");

            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateMeetingActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    preSelectedDate = Utility.formatDate(calendar.getTime(), SELECTED_DATE_DISPLAY_FORMAT);
                    serverDate=Utility.formatDate(calendar.getTime(), SERVER_DATE_FORMAT);
                        calendar_event_start_date.setText(preSelectedDate);
                        if (newCalendarEvent.getStartDateObj().after(newCalendarEvent.getEndDateObj())) {
                            // Calculating End Time i.e. adding 1 Hour to Start Time.
                            calendar.add(Calendar.HOUR_OF_DAY, 1);
                            newCalendarEvent.setEndDateObj(calendar.getTime());
                            newCalendarEvent.setEndDate(Utility.formatDate(calendar.getTime(), SELECTED_DATE_DISPLAY_FORMAT));
                            serverDate=Utility.formatDate(calendar.getTime(), SERVER_DATE_FORMAT);
                          // calendar_event_end_date.setText(newCalendarEvent.getEndDate());
                        }
                }
            }, Integer.parseInt(dateValues[2]), Integer.parseInt(dateValues[0]) - 1, Integer.parseInt(dateValues[1]));

            datePickerDialog.setCancelable(false);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      /*  Intent home = new Intent(getApplicationContext(), MainActivity.class);
        home.putExtra("type","meeting");
        startActivity(home);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);*/
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void closeActivity(View view) {
        onBackPressed();
    }
}

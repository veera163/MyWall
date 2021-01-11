package com.frazen.edaftar.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Model.EndorsementDetails;
import com.frazen.edaftar.Model.UpdateEndorse;
import com.frazen.edaftar.Model.ViewEndorsementDetails;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.Utility;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateEndorsementActivity extends AppCompatActivity {

    private EndorsementDetails endorsement = null;
    TextView txt_path, txt_endorse_num,txt_date,txt_status,txt_referenced_by,txt_reference_phone,txt_petitioner,txt_petitioner_name,txt_petitioner_phone,
            txt_department,txt_endorse_officer,txt_reply_received,txt_address,txt_hamlet,txt_district,txt_mandel,txt_village,txt_petitionDetails;
    EditText ed_remarks;
    private String selectedDateStr = "",path;
    Button create_contact_reset_button,create_contact_save_button;
    public static final String CALENDAR_SELECTED_DATE_WITH_TIME_FORMAT = "y-MM-dd HH:mm";
    private ProgressDialog progressDialog;
    int serialNo;
    CardView card_view;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_endorsement);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            serialNo=bundle.getInt("serialNo");
          //  endorsement = (EndorsementDetails) bundle.getSerializable("ENDORSEMENT_DETAILS");
        }
        txt_path=findViewById(R.id.path);
        card_view=findViewById(R.id.card_view);
        create_contact_reset_button=findViewById(R.id.create_contact_reset_button);
        create_contact_save_button=findViewById(R.id.create_contact_save_button);
        txt_endorse_num=findViewById(R.id.txt_endorse_num);
        txt_date=findViewById(R.id.txt_date);
        txt_status=findViewById(R.id.txt_status);
        txt_referenced_by=findViewById(R.id.txt_referenced_by);
        txt_reference_phone=findViewById(R.id.txt_reference_phone);
        txt_petitioner=findViewById(R.id.txt_petitioner);
        txt_petitioner_name=findViewById(R.id.txt_petitioner_name);
        txt_petitioner_phone=findViewById(R.id.txt_petitioner_phone);
        txt_department=findViewById(R.id.txt_department);
        txt_endorse_officer=findViewById(R.id.txt_endorse_officer);
        txt_reply_received=findViewById(R.id.txt_reply_received);
        txt_address=findViewById(R.id.txt_address);
        txt_hamlet=findViewById(R.id.txt_hamlet);
        txt_district=findViewById(R.id.txt_district);
        txt_mandel=findViewById(R.id.txt_mandel);
        txt_village=findViewById(R.id.txt_village);
        txt_petitionDetails=findViewById(R.id.txt_petitionDetails);
        ed_remarks=findViewById(R.id.ed_remarks);
        getViewData(serialNo);

        card_view.setOnClickListener(view -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                    Uri.parse(path));
            startActivity(viewIntent);
        });

        create_contact_reset_button.setOnClickListener(view -> {
            onBackPressed();
        });
        create_contact_save_button.setOnClickListener(view -> {
         String remarks=ed_remarks.getText().toString().trim();
            boolean isValid = true;
            View focusView = null;
            ed_remarks.setError(null);
            if (TextUtils.isEmpty(remarks)) {
                ed_remarks.setError(getString(R.string.error_field_required));
                focusView = ed_remarks;
                isValid = false;
            }
            if (isValid){
                selectedDateStr = formatDate(new Date(), CALENDAR_SELECTED_DATE_WITH_TIME_FORMAT);
                endorsement.setRemarks(remarks);
                updateDetails(endorsement);
            }
            else {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            }
        });
    }

    private void getViewData(int serialNo) {
        String url = AppServerService.baseURL+"endorsement/view/"+serialNo;
        Log.e("veera",url);

        progressDialog = ProgressDialog.show(UpdateEndorsementActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<ViewEndorsementDetails> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getEndorse(url);
        listCall.enqueue(new Callback<ViewEndorsementDetails>() {
            @Override
            public void onResponse(@NonNull Call<ViewEndorsementDetails> call, @NonNull Response<ViewEndorsementDetails> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        ViewEndorsementDetails viewEndorsementDetails= response.body();
                        if(viewEndorsementDetails.getEndorsement()!=null){
                            endorsement= viewEndorsementDetails.getEndorsement();
                            try {
                                txt_endorse_num.setText(endorsement.getEndorsementNo());
                                if(endorsement.getCreateDate()!=null && !endorsement.getCreateDate().isEmpty()){
                                    date=formatter.parse(endorsement.getCreateDate());
                                    txt_date.setText(Utility.formatDate(date,SELECTED_DATE_DISPLAY_FORMAT));
                                }
                                txt_status.setText(endorsement.getStatus());
                                txt_referenced_by.setText(endorsement.getReferredBy());
                                txt_reference_phone.setText(endorsement.getRefByPhone());
                                txt_petitioner.setText(endorsement.getPetitionerCategory());
                                txt_petitioner_name.setText(endorsement.getPetitionerName());
                                txt_petitioner_phone.setText(endorsement.getPetitionerMobile());
                                txt_department.setText(endorsement.getDepartment());
                                txt_endorse_officer.setText(endorsement.getEndorsedOfficer());
                                txt_reply_received.setText(endorsement.getReplyReceived());
                                txt_address.setText(endorsement.getAddress());
                                txt_hamlet.setText(endorsement.getHamlet());
                                txt_district.setText(endorsement.getDistrict());
                                txt_mandel.setText(endorsement.getMandal());
                                txt_village.setText(endorsement.getVillage());
                                txt_petitionDetails.setText(endorsement.getPetitionDetails());
                                ed_remarks.setText(endorsement.getRemarks());
                                if(viewEndorsementDetails.getUploadedFiles()!=null && !viewEndorsementDetails.getUploadedFiles().isEmpty()){
                                    card_view.setVisibility(View.VISIBLE);
                                    path= viewEndorsementDetails.getUploadedFiles().get(0).getFilePath()+"/"+viewEndorsementDetails.getUploadedFiles().get(0).getFileName();
                                    path = path.replaceAll(" ", "%20");
                                    txt_path.setText(viewEndorsementDetails.getUploadedFiles().get(0).getFileName());
                                    Log.e("veera",path);
                                }
                                else {
                                    card_view.setVisibility(View.GONE);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(UpdateEndorsementActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(UpdateEndorsementActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(UpdateEndorsementActivity.this, "Update Endorse : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ViewEndorsementDetails> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateEndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public static String formatDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    private void updateDetails(EndorsementDetails endorsement) {

        UpdateEndorse updateEndorse=new UpdateEndorse();
        updateEndorse.setEndorsement(endorsement);
        updateEndorse.setActTime(selectedDateStr);
        updateEndorse.setUsername(MyWall.getLoginId());
        Log.e("veera",updateEndorse.toString());
        progressDialog = ProgressDialog.show(UpdateEndorsementActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<Boolean> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).updateEndorse(updateEndorse);
        listCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Boolean aBoolean= response.body();
                        if(aBoolean){
                            Toast.makeText(UpdateEndorsementActivity.this, "Successfully Updated...", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(UpdateEndorsementActivity.this, MainActivity.class);
                            home.putExtra("type","endorse");
                            startActivity(home);
                            overridePendingTransition(R.anim.left_in, R.anim.right_out);
                        }else {
                            Toast.makeText(UpdateEndorsementActivity.this, ""+aBoolean, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(UpdateEndorsementActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(UpdateEndorsementActivity.this, "Update Endorse : "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateEndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void closeActivity(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      /*  Intent home = new Intent(getApplicationContext(), MainActivity.class);
        home.putExtra("type","endorse");
        startActivity(home);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);*/
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}

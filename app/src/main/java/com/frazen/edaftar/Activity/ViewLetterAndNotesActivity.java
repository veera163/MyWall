package com.frazen.edaftar.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Model.LettersAndNotes;
import com.frazen.edaftar.Model.ViewLetterAndNotes;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.Utility;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;

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

public class ViewLetterAndNotesActivity extends AppCompatActivity {

    TextView txt_path_file,txt_path, txt_endorse_num,txt_date,txt_status,txt_category,txt_referenced_by,txt_reference_phone,txt_petitioner,txt_petitioner_name,txt_petitioner_phone,
            txt_department,txt_dispatch_mode,txt_address_to,txt_reply_received,txt_address,txt_hamlet,txt_district,
            txt_mandel,txt_village,txt_subject,txt_remarks,txt_file_location;
    CardView card_view,card_view_file;
    private ProgressDialog progressDialog;
    String path_1,path_2;
    int serialNo;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_notes_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            serialNo=bundle.getInt("serialNo");
            //  endorsement = (EndorsementDetails) bundle.getSerializable("ENDORSEMENT_DETAILS");
        }
        txt_path=findViewById(R.id.path);
        txt_path_file=findViewById(R.id.path_file);

        txt_file_location=findViewById(R.id.txt_file_location);
        card_view=findViewById(R.id.card_view);
        card_view_file=findViewById(R.id.card_view_file);
        txt_endorse_num=findViewById(R.id.txt_endorse_num);
        txt_date=findViewById(R.id.txt_date);
        txt_status=findViewById(R.id.txt_status);
        txt_referenced_by=findViewById(R.id.txt_referenced_by);
        txt_reference_phone=findViewById(R.id.txt_reference_phone);
        txt_petitioner=findViewById(R.id.txt_petitioner);
        txt_petitioner_name=findViewById(R.id.txt_petitioner_name);
        txt_petitioner_phone=findViewById(R.id.txt_petitioner_phone);
        txt_department=findViewById(R.id.txt_department);
        txt_category=findViewById(R.id.txt_category);
        txt_dispatch_mode=findViewById(R.id.txt_dispatch_mode);
        txt_address_to=findViewById(R.id.txt_address_to);
        txt_reply_received=findViewById(R.id.txt_reply_received);
        txt_address=findViewById(R.id.txt_address);
        txt_hamlet=findViewById(R.id.txt_hamlet);
        txt_district=findViewById(R.id.txt_district);
        txt_mandel=findViewById(R.id.txt_mandel);
        txt_village=findViewById(R.id.txt_village);
        txt_subject=findViewById(R.id.txt_subject);
        txt_remarks=findViewById(R.id.txt_remarks);

        card_view.setOnClickListener(view -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                    Uri.parse(path_1));
            startActivity(viewIntent);
        });

        card_view_file.setOnClickListener(view -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                    Uri.parse(path_2));
            startActivity(viewIntent);
        });

        getLatterAndNotes(serialNo);
    }

    private void getLatterAndNotes(int serialNo) {

        String url = AppServerService.baseURL+"letter/view/"+serialNo;
        Log.e("veera",url);

        progressDialog = ProgressDialog.show(ViewLetterAndNotesActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<ViewLetterAndNotes> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getLettersDetails(url);
        listCall.enqueue(new Callback<ViewLetterAndNotes>() {
            @Override
            public void onResponse(@NonNull Call<ViewLetterAndNotes> call, @NonNull Response<ViewLetterAndNotes> response) {

                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        ViewLetterAndNotes viewLetterAndNotes= response.body();
                        if(viewLetterAndNotes.getLetter()!=null){
                          LettersAndNotes endorsement= viewLetterAndNotes.getLetter();
                            try {
                                txt_endorse_num.setText(endorsement.getLonNo());
                                if(endorsement.getCreateDate()!=null && !endorsement.getCreateDate().isEmpty()){
                                    date=formatter.parse(endorsement.getCreateDate());
                                    txt_date.setText(Utility.formatDate(date,SELECTED_DATE_DISPLAY_FORMAT));
                                }
                                txt_status.setText(endorsement.getStatus());
                                txt_category.setText(endorsement.getType());
                                txt_referenced_by.setText(endorsement.getReferenceName());
                                txt_reference_phone.setText(endorsement.getReferenceMobile());
                                txt_petitioner.setText(endorsement.getPetitionerPosition());
                                txt_petitioner_name.setText(endorsement.getPetitionerName());
                                txt_petitioner_phone.setText(endorsement.getPetitionerMobile());
                                txt_department.setText(endorsement.getDepartment());
                                txt_dispatch_mode.setText(endorsement.getDispatchMode());
                                txt_address_to.setText(endorsement.getSentToOfficer());
                                txt_reply_received.setText(endorsement.getReplyRecieved());
                                txt_address.setText(endorsement.getAddress());
                                txt_hamlet.setText(endorsement.getHamlet());
                                txt_district.setText(endorsement.getDistrict());
                                txt_mandel.setText(endorsement.getMandal());
                                txt_village.setText(endorsement.getVillage());
                                txt_subject.setText(endorsement.getPetitionDetails());
                                txt_remarks.setText(endorsement.getRemarks());
                                txt_file_location.setText(endorsement.getLocation());
                                if(viewLetterAndNotes.getUploadedFiles()!=null && !viewLetterAndNotes.getUploadedFiles().isEmpty()){
                                    card_view.setVisibility(View.VISIBLE);
                                    path_1= viewLetterAndNotes.getUploadedFiles().get(0).getFilePath()+"/"+viewLetterAndNotes.getUploadedFiles().get(0).getFileName();
                                    path_1 = path_1.replaceAll(" ", "%20");
                                    txt_path.setText(viewLetterAndNotes.getUploadedFiles().get(0).getFileName());
                                    Log.e("veera",path_1);
                                }
                                else {
                                    card_view.setVisibility(View.GONE);
                                }
                                if(viewLetterAndNotes.getAttachments()!=null && !viewLetterAndNotes.getAttachments().isEmpty()){
                                    card_view_file.setVisibility(View.VISIBLE);
                                    path_2= viewLetterAndNotes.getAttachments().get(0).getFilePath()+"/"+viewLetterAndNotes.getAttachments().get(0).getFileName();
                                    path_2 = path_2.replaceAll(" ", "%20");
                                    txt_path_file.setText(viewLetterAndNotes.getAttachments().get(0).getFileName());
                                    Log.e("veera",path_2);
                                }
                                else {
                                    card_view_file.setVisibility(View.GONE);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(ViewLetterAndNotesActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ViewLetterAndNotesActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ViewLetterAndNotesActivity.this, "LettersAndNotes: "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ViewLetterAndNotes> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewLetterAndNotesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

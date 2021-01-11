package com.frazen.edaftar.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Model.Peshi;
import com.frazen.edaftar.Model.ViewPeshi;
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

public class ViewPeshiActivity extends AppCompatActivity {

    String fileNO,path;
    TextView txt_endorse_num,txt_subject,txt_status,txt_department,txt_received_from,txt_received_date,txt_return_to,txt_return_date,
            txt_assigmed_to,txt_remarks,txt_mini_remarks,txt_path,txt_file_location;
    CardView card_view;
    private ProgressDialog progressDialog;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date revdate,rundate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peshi_details);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fileNO = bundle.getString("fileNo");
        }
        txt_endorse_num=findViewById(R.id.txt_endorse_num);
        txt_subject=findViewById(R.id.txt_subject);
        txt_status=findViewById(R.id.txt_status);
        txt_department=findViewById(R.id.txt_department);
        txt_received_from=findViewById(R.id.txt_received_from);
        txt_received_date=findViewById(R.id.txt_received_date);
        txt_return_to=findViewById(R.id.txt_return_to);
        txt_return_date=findViewById(R.id.txt_return_date);
        txt_assigmed_to=findViewById(R.id.txt_assigmed_to);
        txt_remarks=findViewById(R.id.txt_remarks);
        txt_mini_remarks=findViewById(R.id.txt_mini_remarks);
        txt_path=findViewById(R.id.path);
        txt_file_location=findViewById(R.id.txt_file_location);
        card_view=findViewById(R.id.card_view);

        card_view.setOnClickListener(view -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                    Uri.parse(path));
            startActivity(viewIntent);
        });
        getPeshiDetails(fileNO);

    }

    private void getPeshiDetails(String fileNO) {
        String url = AppServerService.baseURL+"peshi/id/"+fileNO;
        Log.e("veera",url);

        progressDialog = ProgressDialog.show(ViewPeshiActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<ViewPeshi> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getPeshiDetails(url);
        listCall.enqueue(new Callback<ViewPeshi>() {
            @Override
            public void onResponse(@NonNull Call<ViewPeshi> call, @NonNull Response<ViewPeshi> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        ViewPeshi viewPeshi= response.body();
                        if(viewPeshi.getPeshiFiles()!=null){
                           Peshi endorsement= viewPeshi.getPeshiFiles();
                            try {
                                txt_endorse_num.setText(endorsement.getFileNo());
                                txt_subject.setText(endorsement.getSubject());
                                txt_status.setText(endorsement.getStatus());
                                txt_department.setText(endorsement.getDepartment());
                                txt_received_from.setText(endorsement.getReceivedFrom());
                                if(endorsement.getReceivedDate()!=null && !endorsement.getReceivedDate().isEmpty()) {
                                    revdate=formatter.parse(endorsement.getReceivedDate());
                                    txt_received_date.setText(Utility.formatDate(revdate,SELECTED_DATE_DISPLAY_FORMAT));
                                }
                                txt_return_to.setText(endorsement.getReturnedTo());
                                if(endorsement.getReturnedDate()!=null && !endorsement.getReturnedDate().isEmpty()) {
                                    rundate=formatter.parse(endorsement.getReturnedDate());
                                    txt_return_date.setText(Utility.formatDate(rundate,SELECTED_DATE_DISPLAY_FORMAT));
                                }
                                txt_assigmed_to.setText(endorsement.getAssignedTo());
                                txt_remarks.setText(endorsement.getRemarks());
                                txt_mini_remarks.setText(endorsement.getMinisterRemarks());
                                txt_file_location.setText(endorsement.getFileLocation());
                                if(viewPeshi.getUploadedFiles()!=null && !viewPeshi.getUploadedFiles().isEmpty()){
                                    card_view.setVisibility(View.VISIBLE);
                                    path= viewPeshi.getUploadedFiles().get(0).getFilePath()+"/"+viewPeshi.getUploadedFiles().get(0).getFileName();
                                    path = path.replaceAll(" ", "%20");
                                    txt_path.setText(viewPeshi.getUploadedFiles().get(0).getFileName());
                                    Log.e("veera",path);
                                }
                                else {
                                    card_view.setVisibility(View.GONE);
                                }


                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                        }else {
                            Toast.makeText(ViewPeshiActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ViewPeshiActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ViewPeshiActivity.this, "View Peshi : "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ViewPeshi> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewPeshiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

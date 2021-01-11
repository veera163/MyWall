package com.frazen.edaftar.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Model.Grievance;
import com.frazen.edaftar.Model.ViewGrievance;
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

public class ViewGrivanceActivity extends AppCompatActivity {

    String serialNo;
    private ProgressDialog progressDialog;
    String path;
    CardView card_view;
    TextView txt_path,txt_endorse_num,txt_title,txt_status,txt_date,txt_source,txt_organization,txt_assigmed_to,txt_gist_of_case,
            txt_person,txt_person_phone,txt_relation_to_contact,txt_contact_name,txt_contact_phone,txt_twitter_link,txt_email_id,
            txt_address,txt_hamlet,txt_district,txt_mandel,txt_village,txt_solution,txt_mini_remarks;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grivance_details);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            serialNo=bundle.getString("serialNo");
            //  endorsement = (EndorsementDetails) bundle.getSerializable("ENDORSEMENT_DETAILS");
        }
        txt_endorse_num=findViewById(R.id.txt_endorse_num);
        txt_title=findViewById(R.id.txt_title);
        txt_status=findViewById(R.id.txt_status);
        txt_date=findViewById(R.id.txt_date);
        txt_source=findViewById(R.id.txt_source);
        txt_organization=findViewById(R.id.txt_organization);
        txt_assigmed_to=findViewById(R.id.txt_assigmed_to);
        txt_gist_of_case=findViewById(R.id.txt_gist_of_case);
        txt_person=findViewById(R.id.txt_person);
        txt_person_phone=findViewById(R.id.txt_person_phone);
        txt_relation_to_contact=findViewById(R.id.txt_relation_to_contact);
        txt_contact_name=findViewById(R.id.txt_contact_name);
        txt_contact_phone=findViewById(R.id.txt_contact_phone);
        txt_twitter_link=findViewById(R.id.txt_twitter_link);
        txt_email_id=findViewById(R.id.txt_email_id);
        txt_address=findViewById(R.id.txt_address);
        txt_hamlet=findViewById(R.id.txt_hamlet);
        txt_district=findViewById(R.id.txt_district);
        txt_mandel=findViewById(R.id.txt_mandel);
        txt_village=findViewById(R.id.txt_village);
        txt_solution=findViewById(R.id.txt_solution);
        txt_mini_remarks=findViewById(R.id.txt_mini_remarks);

        txt_path=findViewById(R.id.path);
        card_view=findViewById(R.id.card_view);
        card_view.setOnClickListener(view -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                    Uri.parse(path));
            startActivity(viewIntent);
        });

        getGrivance(serialNo, MyWall.getLoginId());
    }

    private void getGrivance(String serialNo, String loginId) {

        String url = String.format("%sgrievance/view/%s/%s", AppServerService.baseURL, serialNo,loginId);
        Log.e("veera",url);
        progressDialog = ProgressDialog.show(ViewGrivanceActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<ViewGrievance> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getGrievanceDetails(url);
        listCall.enqueue(new Callback<ViewGrievance>() {
            @Override
            public void onResponse(@NonNull Call<ViewGrievance> call, @NonNull Response<ViewGrievance> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        ViewGrievance viewPeshi= response.body();
                        if(viewPeshi.getGrievance()!=null){
                            Grievance grievance= viewPeshi.getGrievance();
                            try {
                                txt_endorse_num.setText(grievance.getTgId());
                                txt_title.setText(grievance.getTitle());
                                txt_status.setText(grievance.getStatus());
                                if(grievance.getCreateDate()!=null && !grievance.getCreateDate().isEmpty()){
                                    date=formatter.parse(grievance.getCreateDate());
                                    txt_date.setText(Utility.formatDate(date,SELECTED_DATE_DISPLAY_FORMAT));
                                }
                                txt_source.setText(grievance.getSource());
                                txt_organization.setText(grievance.getOrgName());
                                txt_assigmed_to.setText(grievance.getAssignedTo());
                                txt_gist_of_case.setText(grievance.getGistOfCase());
                                txt_person.setText(grievance.getPerson());
                                txt_person_phone.setText(grievance.getPersonPhone());
                                txt_relation_to_contact.setText(grievance.getRelationToContact());
                                txt_contact_name.setText(grievance.getContactName());
                                txt_contact_phone.setText(grievance.getContactNo());
                                txt_twitter_link.setText(grievance.getTwitterLink());
                                txt_email_id.setText(grievance.getContactEmail());
                                txt_address.setText(grievance.getAddress());
                                txt_hamlet.setText(grievance.getHamlet());
                                txt_district.setText(grievance.getDistrict());
                                txt_mandel.setText(grievance.getMandal());
                                txt_village.setText(grievance.getVillage());
                                txt_solution.setText(grievance.getResolution());
                                txt_mini_remarks.setText(grievance.getMinisterTweet());
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
                            Toast.makeText(ViewGrivanceActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ViewGrivanceActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ViewGrivanceActivity.this, "View Grivance : "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ViewGrievance> call, @NonNull Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(ViewGrivanceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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

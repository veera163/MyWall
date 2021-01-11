package com.frazen.edaftar.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Adopters.SanctionedAdopter;
import com.frazen.edaftar.Model.ReimbrusementAndLoc;
import com.frazen.edaftar.Model.SanctionedDetail;
import com.frazen.edaftar.Model.ViewReimbrusementAndLoc;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.Utility;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewReimbrusementAndLocActivity extends AppCompatActivity {

    String fileNO,type;
    String path;
    RecyclerView sanctioned_view;
    List<SanctionedDetail> sanctionedDetails;
    SanctionedAdopter listAdopter;
    CardView card_view;
    TextView txt_endorse_num,txt_date,txt_status,txt_referenced_by,txt_reference_phone,txt_applicant_name,txt_relation,txt_sex,txt_age,
            txt_phone_no,txt_aadhar,txt_problem,txt_hospital,txt_hospital_address,treatment_amount,txt_address,txt_hamlet,txt_district,txt_mandel,
            txt_village,txt_remarks,txt_path,txt_title,txt_sanctioned_details;
    private ProgressDialog progressDialog;
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd-MM-y";
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimbrusement_loc_details);
        sanctionedDetails= new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fileNO = bundle.getString("fileNo");
            type = bundle.getString("type");
        }
        Log.e("veera",fileNO+"  "+type);
        txt_title=findViewById(R.id.txt_title);
        txt_title.setText(capitalize(String.format("%s", type)));
        card_view=findViewById(R.id.card_view);
        txt_sanctioned_details=findViewById(R.id.txt_sanctioned_details);
        txt_endorse_num=findViewById(R.id.txt_endorse_num);
        txt_date=findViewById(R.id.txt_date);
        txt_status=findViewById(R.id.txt_status);
        txt_referenced_by=findViewById(R.id.txt_referenced_by);
        txt_reference_phone=findViewById(R.id.txt_reference_phone);
        txt_applicant_name=findViewById(R.id.txt_applicant_name);
        txt_relation=findViewById(R.id.txt_relation);
        txt_sex=findViewById(R.id.txt_sex);
        txt_age=findViewById(R.id.txt_age);
        txt_phone_no=findViewById(R.id.txt_phone_no);
        txt_aadhar=findViewById(R.id.txt_aadhar);
        txt_problem=findViewById(R.id.txt_problem);
        txt_hospital=findViewById(R.id.txt_hospital);
        txt_hospital_address=findViewById(R.id.txt_hospital_address);
        treatment_amount=findViewById(R.id.treatment_amount);
        txt_address=findViewById(R.id.txt_address);
        txt_hamlet=findViewById(R.id.txt_hamlet);
        txt_district=findViewById(R.id.txt_district);
        txt_mandel=findViewById(R.id.txt_mandel);
        txt_village=findViewById(R.id.txt_village);
        txt_remarks=findViewById(R.id.txt_remarks);
        txt_path=findViewById(R.id.path);
        card_view.setOnClickListener(view -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                            Uri.parse(path));
            startActivity(viewIntent);
        });
        sanctioned_view=findViewById(R.id.sanctioned_view);
        sanctioned_view.setHasFixedSize(true);
         sanctioned_view.addItemDecoration(new DividerItemDecoration(sanctioned_view.getContext(), DividerItemDecoration.VERTICAL));
        sanctioned_view.setAdapter(listAdopter);
        sanctioned_view.setLayoutManager(new LinearLayoutManager(this));

        getDetails(fileNO,type);
    }

    private void getDetails(String fileNO, String type) {
        String url = String.format("%sreimbursement/view/%s/%s", AppServerService.baseURL, fileNO, type);
        Log.e("veera",url);

        progressDialog = ProgressDialog.show(ViewReimbrusementAndLocActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<ViewReimbrusementAndLoc> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getReimbrusementAndLocDetails(url);
        listCall.enqueue(new Callback<ViewReimbrusementAndLoc>() {
            @Override
            public void onResponse(@NonNull Call<ViewReimbrusementAndLoc> call, @NonNull Response<ViewReimbrusementAndLoc> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        ViewReimbrusementAndLoc viewPeshi= response.body();
                        if(viewPeshi.getReimbrusementAndLoc()!=null){
                            ReimbrusementAndLoc endorsement= viewPeshi.getReimbrusementAndLoc();
                            try {
                                txt_endorse_num.setText(endorsement.getFileNo());
                                if(endorsement.getDate()!=null && !endorsement.getDate().isEmpty()){
                                    date=formatter.parse(endorsement.getDate());
                                    txt_date.setText(Utility.formatDate(date,SELECTED_DATE_DISPLAY_FORMAT));
                                }
                                txt_status.setText(endorsement.getStatus());
                                txt_referenced_by.setText(endorsement.getRefBy());
                                txt_reference_phone.setText(endorsement.getRefPhone());
                                txt_applicant_name.setText(endorsement.getPatientName());
                                txt_relation.setText(String.format("%s %s", endorsement.getRelation(), endorsement.getRelativeName()));
                                txt_sex.setText(endorsement.getSex());
                                txt_age.setText(endorsement.getAge());
                                txt_phone_no.setText(endorsement.getpPhone());
                                txt_aadhar.setText(endorsement.getAadhar());
                                txt_problem.setText(endorsement.getDesease());
                                txt_hospital.setText(endorsement.getHospitalName());
                                txt_hospital_address.setText(endorsement.getHospAddress());
                                treatment_amount.setText(String.format("%s /-",endorsement.getTreatmentAmount()));
                                txt_aadhar.setText(endorsement.getAadhar());
                                txt_aadhar.setText(endorsement.getAadhar());
                                txt_address.setText(endorsement.getpAddress());
                                txt_hamlet.setText(endorsement.getHamlet());
                                txt_district.setText(endorsement.getDistrict());
                                txt_mandel.setText(endorsement.getMandal());
                                txt_village.setText(endorsement.getVillage());
                                txt_remarks.setText(endorsement.getRemarks());

                                if(viewPeshi.getSanctionedDetails()!=null && !viewPeshi.getSanctionedDetails().isEmpty()){
                                    sanctionedDetails= viewPeshi.getSanctionedDetails();
                                    int s=sanctionedDetails.size();

                                    // Collections.sort(endorsementDetails, (m1, m2) -> m2.getSerialNo().compareTo(m1.getSerialNo()));
                                    // Collections.sort(endorsementDetails, Collections.reverseOrder());
                                    listAdopter = new SanctionedAdopter(ViewReimbrusementAndLocActivity.this,sanctionedDetails);
                                    sanctioned_view.setHasFixedSize(true);
                                    sanctioned_view.setItemViewCacheSize(s);
                                    sanctioned_view.setDrawingCacheEnabled(true);
                                    sanctioned_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                                    sanctioned_view.setAdapter(listAdopter);
                                }
                                else {
                                    sanctioned_view.setVisibility(View.GONE);
                                    txt_sanctioned_details.setVisibility(View.GONE);
                                }

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
                            Toast.makeText(ViewReimbrusementAndLocActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ViewReimbrusementAndLocActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ViewReimbrusementAndLocActivity.this, "View ReimbrusementAndLoc : "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ViewReimbrusementAndLoc> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewReimbrusementAndLocActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
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

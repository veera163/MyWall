package com.frazen.edaftar.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Adopters.EndorsementListAdopter;
import com.frazen.edaftar.Listeners.ClickListener;
import com.frazen.edaftar.Listeners.RecyclerTouchListener;
import com.frazen.edaftar.Model.EndorsementDetails;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndorsementActivity extends AppCompatActivity {
    EditText ed_search;
    private ProgressDialog progressDialog;
    List<EndorsementDetails> endorsementDetails;
    private RecyclerView endorsement_view;
    EndorsementListAdopter listAdopter;
    String type,value,url;
    TextView txt_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_events);
        endorsementDetails= new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getString("type");
            value = bundle.getString("value");
        }
        Log.e("veera",type+"-"+value);

        //you can set the title for your toolbar here for different fragments different titles
        ed_search=findViewById(R.id.ed_search);
        txt_title=findViewById(R.id.txt_title);
        txt_title.setText(capitalize(String.format("%s (%s)", type, value)));
        endorsement_view = findViewById(R.id.endorsement_view);
        endorsement_view.setHasFixedSize(true);
        // endorsement_view.addItemDecoration(new DividerItemDecoration(endorsement_view.getContext(), DividerItemDecoration.VERTICAL));
        endorsement_view.setAdapter(listAdopter);
        endorsement_view.setLayoutManager(new LinearLayoutManager(this));

        endorsement_view.addOnItemTouchListener(new RecyclerTouchListener(EndorsementActivity.this, endorsement_view, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
               /* Intent intent= new Intent(getContext(), UpdateEndorsementActivity.class);
                intent.putExtra("ENDORSEMENT_DETAILS", endorsementDetails.get(position));
                Objects.requireNonNull(getActivity()).startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length()>0){
                    if(endorsementDetails!=null && !endorsementDetails.isEmpty()){
                        List<EndorsementDetails> infos= new ArrayList<>();
                        for(int i=0;i<endorsementDetails.size();i++){
                            String endorseNum=endorsementDetails.get(i).getEndorsementNo()!=null? endorsementDetails.get(i).getEndorsementNo():"";
                            String name=endorsementDetails.get(i).getPetitionerName()!=null? endorsementDetails.get(i).getPetitionerName():"";
                            String Dept=endorsementDetails.get(i).getDepartment()!=null? endorsementDetails.get(i).getDepartment():"";
                            String status=endorsementDetails.get(i).getStatus()!=null? endorsementDetails.get(i).getStatus():"";
                            String officer=endorsementDetails.get(i).getEndorsedOfficer()!=null? endorsementDetails.get(i).getEndorsedOfficer():"";
                            //  String mandel=endorsementDetails.get(i).getMandal()!=null? endorsementDetails.get(i).getMandal():"";
                            //  String village=endorsementDetails.get(i).getVillage()!=null? endorsementDetails.get(i).getVillage():"";


                            if(name.toLowerCase().contains(s.toString().toLowerCase()) ||endorseNum.toLowerCase().contains(s.toString().toLowerCase()) ){
                                infos.add(endorsementDetails.get(i));
                            }
                            else if(status.toLowerCase().contains(s.toString().toLowerCase())|| Dept.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(endorsementDetails.get(i));
                            }
                            else if(officer.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(endorsementDetails.get(i));
                            }
                          /*  else if(mandel.toLowerCase().contains(s.toString().toLowerCase()) || village.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(endorsementDetails.get(i));
                            }*/
                        }
                        if(!infos.isEmpty()){
                            int size = infos.size();
                            Collections.sort(endorsementDetails, (m1, m2) -> m2.getEndorsementNo().compareTo(m1.getEndorsementNo()));
                            // Collections.sort(endorsementDetails, Collections.reverseOrder());
                            listAdopter = new EndorsementListAdopter(EndorsementActivity.this,infos, EndorsementActivity.this);
                            endorsement_view.setHasFixedSize(true);
                            endorsement_view.setItemViewCacheSize(size);
                            endorsement_view.setDrawingCacheEnabled(true);
                            endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                            endorsement_view.setAdapter(listAdopter);
                        }
                        else {
                            endorsement_view.setAdapter(null);
                            Toast.makeText(EndorsementActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(EndorsementActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if(endorsementDetails!=null && !endorsementDetails.isEmpty()){
                        int ss=endorsementDetails.size();
                        Collections.sort(endorsementDetails, (m1, m2) -> m2.getEndorsementNo().compareTo(m1.getEndorsementNo()));
                        // Collections.sort(endorsementDetails, Collections.reverseOrder());
                        listAdopter = new EndorsementListAdopter(EndorsementActivity.this,endorsementDetails, EndorsementActivity.this);
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(ss);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(listAdopter);

                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(EndorsementActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        getEndrose();
    }
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }
    private void getEndrose() {
        if(value.equals("all")){
            url  = AppServerService.baseURL+type+"/"+value;
            url = url.replaceAll(" ", "%20");
        }else {
            url  = AppServerService.baseURL+type+"/all/"+value;
            url = url.replaceAll(" ", "%20");
        }
        Log.e("veera",url);
        progressDialog = ProgressDialog.show(EndorsementActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<List<EndorsementDetails>> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getAllEndorse(url);
        listCall.enqueue(new Callback<List<EndorsementDetails>>() {
            @Override
            public void onResponse(@NonNull Call<List<EndorsementDetails>> call, @NonNull Response<List<EndorsementDetails>> response) {

                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if(response.body()!=null && !response.body().isEmpty()){
                        endorsementDetails= response.body();
                        int s=endorsementDetails.size();

                        Collections.sort(endorsementDetails, (m1, m2) -> m2.getEndorsementNo().compareTo(m1.getEndorsementNo()));
                        // Collections.sort(endorsementDetails, Collections.reverseOrder());
                        listAdopter = new EndorsementListAdopter(EndorsementActivity.this,endorsementDetails, EndorsementActivity.this);
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(s);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(listAdopter);

                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(EndorsementActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(EndorsementActivity.this, "Endorsement : "+ response.code(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<EndorsementDetails>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

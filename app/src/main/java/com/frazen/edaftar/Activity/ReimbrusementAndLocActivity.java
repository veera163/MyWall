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

import com.frazen.edaftar.Adopters.ReimbursemetAndLocAdopter;
import com.frazen.edaftar.Listeners.ClickListener;
import com.frazen.edaftar.Listeners.RecyclerTouchListener;
import com.frazen.edaftar.Model.ReimbrusementAndLoc;
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

public class ReimbrusementAndLocActivity extends AppCompatActivity {

    String type,value,sub;
    private ProgressDialog progressDialog;
    String url;
    List<ReimbrusementAndLoc> reimbrusementList;
    private RecyclerView endorsement_view;
    ReimbursemetAndLocAdopter reimbursemetAdopter;
    EditText ed_search;
    TextView txt_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_events);
        reimbrusementList= new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getString("type");
            value = bundle.getString("value");
            sub=bundle.getString("sub");
        }
        Log.e("veera",type+"-"+value);
        ed_search=findViewById(R.id.ed_search);
        txt_title=findViewById(R.id.txt_title);
        txt_title.setText(capitalize(String.format("%s (%s)", sub, value)));
        endorsement_view = findViewById(R.id.endorsement_view);
        endorsement_view.setHasFixedSize(true);
        // endorsement_view.addItemDecoration(new DividerItemDecoration(endorsement_view.getContext(), DividerItemDecoration.VERTICAL));
        endorsement_view.setAdapter(reimbursemetAdopter);
        endorsement_view.setLayoutManager(new LinearLayoutManager(this));
        endorsement_view.addOnItemTouchListener(new RecyclerTouchListener(this, endorsement_view, new ClickListener() {
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
                    if(reimbrusementList!=null && !reimbrusementList.isEmpty()){
                        List<ReimbrusementAndLoc> infos= new ArrayList<>();

                        for(int i=0;i<reimbrusementList.size();i++){
                            String endorseNum=reimbrusementList.get(i).getFileNo()!=null? reimbrusementList.get(i).getFileNo():"";
                            String name=reimbrusementList.get(i).getPatientName()!=null? reimbrusementList.get(i).getPatientName():"";
                            String status=reimbrusementList.get(i).getStatus()!=null? reimbrusementList.get(i).getStatus():"";
                            String type=reimbrusementList.get(i).getRelativeName()!=null? reimbrusementList.get(i).getRelativeName():"";
                            String village=reimbrusementList.get(i).getVillage()!=null? reimbrusementList.get(i).getVillage():"";
                            String mandal=reimbrusementList.get(i).getMandal()!=null? reimbrusementList.get(i).getMandal():"";
                            //  String mandel=endorsementDetails.get(i).getMandal()!=null? endorsementDetails.get(i).getMandal():"";
                            //  String village=endorsementDetails.get(i).getVillage()!=null? endorsementDetails.get(i).getVillage():"";

                            String dist=reimbrusementList.get(i).getDistrict()!=null? reimbrusementList.get(i).getDistrict():"";

                            if(name.toLowerCase().contains(s.toString().toLowerCase()) ||endorseNum.toLowerCase().contains(s.toString().toLowerCase()) ){
                                infos.add(reimbrusementList.get(i));
                            }
                            else if(status.toLowerCase().contains(s.toString().toLowerCase())|| type.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(reimbrusementList.get(i));
                            }
                            else if(village.toLowerCase().contains(s.toString().toLowerCase()) || mandal.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(reimbrusementList.get(i));
                            }
                            else if(dist.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(reimbrusementList.get(i));
                            }
                          /*  else if(mandel.toLowerCase().contains(s.toString().toLowerCase()) || village.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(endorsementDetails.get(i));
                            }*/
                        }
                        if(!infos.isEmpty()){
                            int size = infos.size();
                            Collections.reverse(reimbrusementList);
                            // Collections.sort(infos, (m1, m2) -> m2.getFileNo().compareTo(m1.getFileNo()));
                            // Collections.sort(endorsementDetails, Collections.reverseOrder());
                            reimbursemetAdopter = new ReimbursemetAndLocAdopter(ReimbrusementAndLocActivity.this,infos, ReimbrusementAndLocActivity.this);
                            endorsement_view.setHasFixedSize(true);
                            endorsement_view.setItemViewCacheSize(size);
                            endorsement_view.setDrawingCacheEnabled(true);
                            endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                            endorsement_view.setAdapter(reimbursemetAdopter);
                        }
                        else {
                            endorsement_view.setAdapter(null);
                            Toast.makeText(ReimbrusementAndLocActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(ReimbrusementAndLocActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if(reimbrusementList!=null && !reimbrusementList.isEmpty()){
                        int ss=reimbrusementList.size();
                        Collections.reverse(reimbrusementList);
                        // Collections.sort(reimbrusementList, (m1, m2) -> m2.getFileNo().compareTo(m1.getFileNo()));
                        // Collections.sort(endorsementDetails, Collections.reverseOrder());
                        reimbursemetAdopter = new ReimbursemetAndLocAdopter(ReimbrusementAndLocActivity.this,reimbrusementList, ReimbrusementAndLocActivity.this);
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(ss);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(reimbursemetAdopter);
                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(ReimbrusementAndLocActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        getReimbrusementData(type,value);

    }
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    private void getReimbrusementData(String type, String value) {
        if(value.equals("all")){
            url  = String.format("%s%s/%s/%s", AppServerService.baseURL, type, value, sub);
            url = url.replaceAll(" ", "%20");
        }else {
            url  = String.format("%s%s/all/%s/%s", AppServerService.baseURL, type, sub, value);
            url = url.replaceAll(" ", "%20");
        }
        Log.e("veera",url);
        progressDialog = ProgressDialog.show(ReimbrusementAndLocActivity.this, "Please wait.",
                "Service Loading ...!", true);
        Call<List<ReimbrusementAndLoc>> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getReimbrusementAndLoc(url);
        listCall.enqueue(new Callback<List<ReimbrusementAndLoc>>() {
            @Override
            public void onResponse(@NonNull Call<List<ReimbrusementAndLoc>> call, @NonNull Response<List<ReimbrusementAndLoc>> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if(response.body()!=null && !response.body().isEmpty()){
                        reimbrusementList= response.body();
                        int s=reimbrusementList.size();

                       // Collections.sort(reimbrusementList, (m1, m2) -> m1.getDate().compareTo(m2.getDate()));
                        // Collections.sort(reimbrusementList, Collections.reverseOrder());
                        Collections.reverse(reimbrusementList);
                        reimbursemetAdopter = new ReimbursemetAndLocAdopter(ReimbrusementAndLocActivity.this,reimbrusementList, ReimbrusementAndLocActivity.this);
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(s);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(reimbursemetAdopter);
                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(ReimbrusementAndLocActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(ReimbrusementAndLocActivity.this, "reimbursemet : "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<ReimbrusementAndLoc>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ReimbrusementAndLocActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

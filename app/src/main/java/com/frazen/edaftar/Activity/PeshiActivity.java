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

import com.frazen.edaftar.Adopters.PeshiAdopter;
import com.frazen.edaftar.Listeners.ClickListener;
import com.frazen.edaftar.Listeners.RecyclerTouchListener;
import com.frazen.edaftar.Model.Peshi;
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

public class PeshiActivity extends AppCompatActivity {

    String type,value;
    private ProgressDialog progressDialog;
    String url;
    List<Peshi> peshiList;
    private RecyclerView endorsement_view;
    PeshiAdopter peshiAdopter;
    EditText ed_search;
    TextView txt_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_events);
        peshiList= new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getString("type");
            value = bundle.getString("value");
        }
        Log.e("veera",type+"-"+value);
        ed_search=findViewById(R.id.ed_search);
        txt_title=findViewById(R.id.txt_title);
        txt_title.setText(capitalize(String.format("%s (%s)", type, value)));
        endorsement_view = findViewById(R.id.endorsement_view);
        endorsement_view.setHasFixedSize(true);
        // endorsement_view.addItemDecoration(new DividerItemDecoration(endorsement_view.getContext(), DividerItemDecoration.VERTICAL));
        endorsement_view.setAdapter(peshiAdopter);
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
                    if(peshiList!=null && !peshiList.isEmpty()){
                        List<Peshi> infos= new ArrayList<>();

                        for(int i=0;i<peshiList.size();i++){
                            String endorseNum=peshiList.get(i).getFileNo()!=null? peshiList.get(i).getFileNo():"";
                            String name=peshiList.get(i).getSubject()!=null? peshiList.get(i).getSubject():"";
                            String type=peshiList.get(i).getReceivedDate()!=null? peshiList.get(i).getReceivedDate():"";
                            String officer=peshiList.get(i).getDepartment()!=null? peshiList.get(i).getDepartment():"";
                            //  String mandel=endorsementDetails.get(i).getMandal()!=null? endorsementDetails.get(i).getMandal():"";
                            //  String village=endorsementDetails.get(i).getVillage()!=null? endorsementDetails.get(i).getVillage():"";

                            String status=peshiList.get(i).getStatus()!=null? peshiList.get(i).getStatus():"";

                            if(name.toLowerCase().contains(s.toString().toLowerCase()) ||endorseNum.toLowerCase().contains(s.toString().toLowerCase()) ){
                                infos.add(peshiList.get(i));
                            }
                            else if(status.toLowerCase().contains(s.toString().toLowerCase())|| type.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(peshiList.get(i));
                            }
                            else if(officer.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(peshiList.get(i));
                            }
                          /*  else if(mandel.toLowerCase().contains(s.toString().toLowerCase()) || village.toLowerCase().contains(s.toString().toLowerCase())){
                                infos.add(endorsementDetails.get(i));
                            }*/
                        }
                        if(!infos.isEmpty()){
                            int size = infos.size();
                            Collections.sort(peshiList, (m1, m2) -> m2.getReceivedDate().compareTo(m1.getReceivedDate()));
                            //  Collections.sort(infos, (m1, m2) -> m2.getFileNo().compareTo(m1.getFileNo()));
                            // Collections.sort(endorsementDetails, Collections.reverseOrder());
                            peshiAdopter = new PeshiAdopter(PeshiActivity.this,infos,PeshiActivity.this);
                            endorsement_view.setHasFixedSize(true);
                            endorsement_view.setItemViewCacheSize(size);
                            endorsement_view.setDrawingCacheEnabled(true);
                            endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                            endorsement_view.setAdapter(peshiAdopter);
                        }
                        else {
                            endorsement_view.setAdapter(null);
                            Toast.makeText(PeshiActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(PeshiActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if(peshiList!=null && !peshiList.isEmpty()){
                        int ss=peshiList.size();
                        Collections.sort(peshiList, (m1, m2) -> m2.getReceivedDate().compareTo(m1.getReceivedDate()));
                        //  Collections.sort(peshiList, (m1, m2) -> m2.getFileNo().compareTo(m1.getFileNo()));
                        // Collections.sort(endorsementDetails, Collections.reverseOrder());
                        peshiAdopter = new PeshiAdopter(PeshiActivity.this,peshiList,PeshiActivity.this);
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(ss);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(peshiAdopter);
                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(PeshiActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        getPeshiData(type,value);
    }

    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }
    private void getPeshiData(String type, String value) {

        if(value.equals("all")){
            url  = AppServerService.baseURL+type+"/"+value;
            url = url.replaceAll(" ", "%20");
        }else {
            url  = AppServerService.baseURL+type+"/all/"+value;
            url = url.replaceAll(" ", "%20");
        }
        Log.e("veera",url);
        progressDialog = ProgressDialog.show(PeshiActivity.this, "Please wait.",
                "Service Loading ...!", true);

        Call<List<Peshi>> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getPeshi(url);
        listCall.enqueue(new Callback<List<Peshi>>() {
            @Override
            public void onResponse(@NonNull Call<List<Peshi>> call, @NonNull Response<List<Peshi>> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if(response.body()!=null && !response.body().isEmpty()){
                        peshiList= response.body();
                        int s=peshiList.size();
                       // Collections.reverse(peshiList);
                           Collections.sort(peshiList, (m1, m2) -> m2.getReceivedDate().compareTo(m1.getReceivedDate()));
                        // Collections.sort(endorsementDetails, Collections.reverseOrder());
                        peshiAdopter = new PeshiAdopter(PeshiActivity.this,peshiList,PeshiActivity.this);
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(s);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(peshiAdopter);
                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(PeshiActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(PeshiActivity.this, "Grievance : "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Peshi>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PeshiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

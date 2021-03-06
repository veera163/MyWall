package com.frazen.edaftar.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frazen.edaftar.Activity.CreateEndorsementActivity;
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
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndorsementFragment extends Fragment {

    ImageView img_add;
    EditText ed_search;
    private ProgressDialog progressDialog;
    List<EndorsementDetails> endorsementDetails;
    private RecyclerView endorsement_view;
    EndorsementListAdopter listAdopter;
    public static final String SERVER_DATE_FORMAT = "y-MM-dd";
    public static final String TAG = "MyFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_endosement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        endorsementDetails= new ArrayList<>();

        //you can set the title for your toolbar here for different fragments different titles
      //  Objects.requireNonNull(getActivity()).setTitle("Endorsement");
        ed_search=view.findViewById(R.id.ed_search);
        img_add=view.findViewById(R.id.add);
        img_add.setOnClickListener(v -> {
            Intent intent= new Intent(getContext(), CreateEndorsementActivity.class);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);

        });
        endorsement_view = view.findViewById(R.id.endorsement_view);
        endorsement_view.setHasFixedSize(true);
       // endorsement_view.addItemDecoration(new DividerItemDecoration(endorsement_view.getContext(), DividerItemDecoration.VERTICAL));
        endorsement_view.setAdapter(listAdopter);
        endorsement_view.setLayoutManager(new LinearLayoutManager(getContext()));

        endorsement_view.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), endorsement_view, new ClickListener() {
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
                            listAdopter = new EndorsementListAdopter(getContext(),infos,getActivity());
                            endorsement_view.setHasFixedSize(true);
                            endorsement_view.setItemViewCacheSize(size);
                            endorsement_view.setDrawingCacheEnabled(true);
                            endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                            endorsement_view.setAdapter(listAdopter);
                        }
                        else {
                            endorsement_view.setAdapter(null);
                            Toast.makeText(getContext(), "No Records Found", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if(endorsementDetails!=null && !endorsementDetails.isEmpty()){
                        int ss=endorsementDetails.size();
               /*         Collections.sort(endorsementDetails, (s1, s2) -> {
                            Integer val1 = Integer.parseInt(s1.getEndorsementNo());
                            Integer val2 = Integer.parseInt(s2.getEndorsementNo());
                            return val2.compareTo(val1);
                        });*/

                       Collections.sort(endorsementDetails, (m1, m2) -> m2.getEndorsementNo().compareTo(m1.getEndorsementNo()));
                        // Collections.sort(endorsementDetails, Collections.reverseOrder());
                        listAdopter = new EndorsementListAdopter(getContext(),endorsementDetails,getActivity());
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(ss);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(listAdopter);

                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(getContext(), "No Records Found", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        getEndrose();
    }

    private void getEndrose() {

        String url = AppServerService.baseURL+"endorsement/all";
        progressDialog = ProgressDialog.show(getContext(), "Please wait.",
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
                                  /*  Collections.sort(endorsementDetails, (s1, s2) -> {
                            Integer val1 = Integer.parseInt(s1.getCreateDate());
                            Integer val2 = Integer.parseInt(s2.getCreateDate());
                            return val1.compareTo(val2);
                        });*/
                       Collections.sort(endorsementDetails, (m1, m2) -> m2.getEndorsementNo().compareTo(m1.getEndorsementNo()));
                       // Collections.sort(endorsementDetails, Collections.reverseOrder());
                        listAdopter = new EndorsementListAdopter(getContext(),endorsementDetails,getActivity());
                        endorsement_view.setHasFixedSize(true);
                        endorsement_view.setItemViewCacheSize(s);
                        endorsement_view.setDrawingCacheEnabled(true);
                        endorsement_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        endorsement_view.setAdapter(listAdopter);

                    }else {
                        endorsement_view.setAdapter(null);
                        Toast.makeText(getContext(), "No Records Found", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    progressDialog.dismiss();
                    if(response.code()==401){
                        Toast.makeText(getContext(), "Session Timeout. Please Login : "+ response.code(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Endorsement : "+ response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<EndorsementDetails>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}

package com.frazen.edaftar.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.frazen.edaftar.Adopters.SpinnerAdapter;
import com.frazen.edaftar.Model.Endorsement;
import com.frazen.edaftar.Model.InitEndorsement;
import com.frazen.edaftar.Model.Mandals;
import com.frazen.edaftar.Model.MinisterData;
import com.frazen.edaftar.Model.Villages;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.RealPathUtil;
import com.frazen.edaftar.Util.Utility;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.frazen.edaftar.Util.Utility.getImageUri;

public class CreateEndorsementActivity extends AppCompatActivity {

    Button create_ticket_attach_file,create_contact_reset_button,create_contact_save_button;
    private String userChoosenTask;
    private int REQUEST_CAMERA = 11;
    private final int FILE_REQUEST_CODE = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 2;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private String selectedFilePath = "", selectedFileName = "";
    private TextView txt_date, create_ticket_selected_file_name;
    private LinearLayout create_ticket_selected_file_layout;
    EditText ed_endorse_num,ed_referenced_by,ed_reference_phone,ed_petitioner_name,ed_petitioner_phone,ed_endorse_officer,
            ed_address,ed_hamlet,ed_petitionDetails;
    private String selectedDateStr = "", selectedDateDisplayStr = "";
    public static final String SERVER_DATE_FORMAT = "y-MM-dd";
    public static final String SELECTED_DATE_DISPLAY_FORMAT = "dd/MM/y";
    File finalFile;
    private ProgressDialog progressDialog;
    ArrayList<String> status_array,Petition_array,reply_array,district_array,mandel_array,village_array,minister_array,department_array;
    private Spinner sp_petitioner,sp_status, sp_reply,sp_district,sp_mandel,sp_village,sp_minister,sp_department;
    private SpinnerAdapter categoriesSpinnerAdapter;
    String status_type,petitioner_type,reply_type="",district_type="",mandel_type="",village_type="",minister_type="";
    String endorse_num,referenced_by,reference_phone,petitioner_name="",petitioner_phone,deparment_type,endorse_officer,address="",hamlet="",petitionDetails;
   int serialNo;
   LinearLayout layout_minister,layout_petition;
   ImageView viewImage;
   CardView card_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_endorsement);
        viewImage=findViewById(R.id.viewImage);
        card_view=findViewById(R.id.card_view);
        layout_minister=findViewById(R.id.layout_minister);
        layout_petition=findViewById(R.id.layout_petition);
        txt_date=findViewById(R.id.ed_date);
        ed_endorse_num=findViewById(R.id.ed_endorse_num);
        ed_referenced_by=findViewById(R.id.ed_referenced_by);
        ed_reference_phone=findViewById(R.id.ed_reference_phone);
        ed_petitioner_name=findViewById(R.id.ed_petitioner_name);
        ed_petitioner_phone=findViewById(R.id.ed_petitioner_phone);
        ed_endorse_officer=findViewById(R.id.ed_endorse_officer);
        ed_address=findViewById(R.id.ed_address);
        ed_hamlet=findViewById(R.id.ed_hamlet);
        ed_petitionDetails=findViewById(R.id.ed_petitionDetails);

        create_ticket_attach_file=findViewById(R.id.create_ticket_attach_file);
        create_ticket_selected_file_name = findViewById(R.id.create_ticket_selected_file_name);
        create_ticket_selected_file_layout = findViewById(R.id.create_ticket_selected_file_layout);
        create_contact_reset_button=findViewById(R.id.create_contact_reset_button);
        create_contact_save_button=findViewById(R.id.create_contact_save_button);
        sp_petitioner = findViewById(R.id.sp_petitioner);
        sp_status = findViewById(R.id.sp_status);
        sp_district = findViewById(R.id.sp_district);
        sp_mandel = findViewById(R.id.sp_mandel);
        sp_village = findViewById(R.id.sp_village);
         sp_reply = findViewById(R.id.sp_reply);
        sp_minister=findViewById(R.id.sp_minister);
        sp_department=findViewById(R.id.sp_department);
        selectedDateDisplayStr=formatDate(new Date(), SELECTED_DATE_DISPLAY_FORMAT);
        selectedDateStr = formatDate(new Date(), SERVER_DATE_FORMAT);
        txt_date.setText(selectedDateDisplayStr);
        txt_date.setOnClickListener(this::showDatePicker);
        create_ticket_attach_file.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(CreateEndorsementActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(CreateEndorsementActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                ActivityCompat.requestPermissions(CreateEndorsementActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        });

        status_array= new ArrayList<>();
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, status_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_status.setAdapter(categoriesSpinnerAdapter);
      //  sp_status.setSelection(0, false);
        sp_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status_type = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Petition_array= new ArrayList<>();
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, Petition_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_petitioner.setAdapter(categoriesSpinnerAdapter);
        //  sp_petitioner.setSelection(0, false);
        sp_petitioner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petitioner_type = parent.getItemAtPosition(position).toString();
                if(petitioner_type.equals("General") || petitioner_type.equals("Others")){
                    layout_petition.setVisibility(View.VISIBLE);
                    layout_minister.setVisibility(View.GONE);
                }else {
                    layout_petition.setVisibility(View.GONE);
                    layout_minister.setVisibility(View.VISIBLE);
                    getMinister(petitioner_type);


                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        reply_array= new ArrayList<>();
        reply_array.add("No");
        reply_array.add("Yes");
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, reply_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_reply.setAdapter(categoriesSpinnerAdapter);
        //  sp_reply.setSelection(0, false);
        sp_reply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reply_type = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        minister_array= new ArrayList<>();
        minister_array.add("Select");
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, minister_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_minister.setAdapter(categoriesSpinnerAdapter);
        //  sp_district.setSelection(0, false);
        sp_minister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).toString().equals(minister_array.get(0))) {
                    minister_type = parent.getItemAtPosition(position).toString();
                    Toast.makeText(CreateEndorsementActivity.this, minister_type, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        department_array= new ArrayList<>();
        department_array.add("Select");
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, department_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_department.setAdapter(categoriesSpinnerAdapter);
        //  sp_district.setSelection(0, false);
        sp_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deparment_type = parent.getItemAtPosition(position).toString();
               }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        district_array= new ArrayList<>();
        district_array.add("Select");
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, district_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_district.setAdapter(categoriesSpinnerAdapter);
        //  sp_district.setSelection(0, false);
        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district_type = parent.getItemAtPosition(position).toString();
                if (!district_type.equals(district_array.get(0))) {
                    getMandel(district_type);
                }
                else {
                    mandel_array= new ArrayList<>();
                    mandel_array.add("Select");
                    categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, mandel_array);
                    categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_mandel.setAdapter(categoriesSpinnerAdapter);
                } }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        mandel_array= new ArrayList<>();
        mandel_array.add("Select");
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, mandel_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_mandel.setAdapter(categoriesSpinnerAdapter);
        //  sp_mandel.setSelection(0, false);
        sp_mandel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).toString().equals(mandel_array.get(0))) {
                    mandel_type = parent.getItemAtPosition(position).toString();
                    getVillage(district_type,mandel_type);
                }
                else {
                    village_array= new ArrayList<>();
                    village_array.add("Select");
                    categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, village_array);
                    categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_village.setAdapter(categoriesSpinnerAdapter);
                } }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        village_array= new ArrayList<>();
        village_array.add("Select");
        categoriesSpinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_row_item, village_array);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_village.setAdapter(categoriesSpinnerAdapter);
        //  sp_village.setSelection(0, false);
        sp_village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).toString().equals(village_array.get(0))) {
                    village_type = parent.getItemAtPosition(position).toString();
                    Toast.makeText(CreateEndorsementActivity.this, village_type, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        create_contact_reset_button.setOnClickListener(view -> {
            onBackPressed();
        });
        create_contact_save_button.setOnClickListener(view -> {
            saveInitDetails();
        });


        getInitDetails();
    }

    private void getMinister(String minister_type) {

        String url = String.format("%ssettings/lists/%s", AppServerService.baseURL, minister_type);
        Log.e("veera",url);
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Service Loading ...!", true);
        Call<List<MinisterData>> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getMinister(url);
        listCall.enqueue(new Callback<List<MinisterData>>() {
            @Override
            public void onResponse(@NonNull Call<List<MinisterData>> call, @NonNull Response<List<MinisterData>> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null && !response.body().isEmpty()){
                        minister_array= new ArrayList<>();
                        minister_array.add("Select");
                        for(int i=0;i<response.body().size();i++){
                            minister_array.add(response.body().get(i).getListItem());
                        }
                        categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, minister_array);
                        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_minister.setAdapter(categoriesSpinnerAdapter);
                    }
                    else {
                        Toast.makeText(CreateEndorsementActivity.this, "Mandals data Found", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(CreateEndorsementActivity.this, " Mandals : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MinisterData>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateEndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void saveInitDetails() {
        endorse_num=ed_endorse_num.getText().toString().trim();
        referenced_by=ed_referenced_by.getText().toString().trim();
        reference_phone=ed_reference_phone.getText().toString().trim();
        petitioner_name=ed_petitioner_name.getText().toString().trim();
        petitioner_phone=ed_petitioner_phone.getText().toString().trim();
        endorse_officer=ed_endorse_officer.getText().toString().trim();
        address=ed_address.getText().toString().trim();
        hamlet=ed_hamlet.getText().toString().trim();
        petitionDetails=ed_petitionDetails.getText().toString().trim();

        boolean isValid = true;
        View focusView = null;
        ed_endorse_num.setError(null);
        if (TextUtils.isEmpty(endorse_num)) {
            ed_endorse_num.setError(getString(R.string.error_field_required));
            focusView = ed_endorse_num;
            isValid = false;
        }
        else  if (TextUtils.isEmpty(selectedDateStr)) {
            txt_date.setError(getString(R.string.error_field_required));
            focusView = txt_date;
            isValid = false;
        }
        else  if (TextUtils.isEmpty(referenced_by)) {
            ed_referenced_by.setError(getString(R.string.error_field_required));
            focusView = ed_referenced_by;
            isValid = false;
        }
        else  if (TextUtils.isEmpty(reference_phone)) {
            ed_reference_phone.setError(getString(R.string.error_field_required));
            focusView = ed_reference_phone;
            isValid = false;
        }
        else if (!isValidMobile(reference_phone)) {
            ed_reference_phone.setError(getString(R.string.error_invalid_phone));
            focusView = ed_reference_phone;
            isValid = false;
        }
        else  if (TextUtils.isEmpty(petitioner_name)) {
            ed_petitioner_name.setError(getString(R.string.error_field_required));
            focusView = ed_petitioner_name;
            isValid = false;
        }
        else  if (TextUtils.isEmpty(petitioner_phone)) {
            ed_petitioner_phone.setError(getString(R.string.error_field_required));
            focusView = ed_petitioner_phone;
            isValid = false;
        }
        else if (!isValidMobile(petitioner_phone)) {
            ed_petitioner_phone.setError(getString(R.string.error_invalid_phone));
            focusView = ed_petitioner_phone;
            isValid = false;
        }
        else if (deparment_type.equals(department_array.get(0))) {
            Toast.makeText(this, "Please Select Department", Toast.LENGTH_SHORT).show();
            focusView =sp_department; ;
            isValid = false;
        }
        else if (district_type.equals(district_array.get(0))) {
            Toast.makeText(this, "Please Select District", Toast.LENGTH_SHORT).show();
            focusView =sp_district; ;
            isValid = false;
        }
        else  if (TextUtils.isEmpty(petitionDetails)) {
            ed_petitionDetails.setError(getString(R.string.error_field_required));
            focusView = ed_petitionDetails;
            isValid = false;
        }
        else  if (TextUtils.isEmpty(selectedFileName)) {
            Toast.makeText(this, "Please add Attachment ...", Toast.LENGTH_SHORT).show();
            focusView = create_ticket_attach_file;
            isValid = false;
        }
        if (isValid){

            sendData(endorse_num,selectedDateStr,status_type,referenced_by,reference_phone,petitioner_type,petitioner_name,petitioner_phone,
                    deparment_type,endorse_officer,reply_type,address,hamlet,district_type,mandel_type,village_type,petitionDetails);
        }
        else {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
    }

    private boolean isValidMobile(String phone) {
        Pattern patterns = Pattern.compile("^[3-9][0-9]{9}$");
        return patterns.matcher(phone).matches();
        // return true;
    }
    private void sendData(String endorse_num, String selectedDateStr, String status_type, String referenced_by,
                          String reference_phone, String petitioner_type, String petitioner_name, String petitioner_phone,
                          String deparment, String endorse_officer, String reply_type, String address, String hamlet,
                          String district_type, String mandel_type, String village_type, String petitionDetails) {
        Endorsement endorsement= new Endorsement();
        endorsement.setSerialNo(String.valueOf(serialNo));
        endorsement.setEndorsementNo(endorse_num);
        endorsement.setCreateDate(selectedDateStr);
        endorsement.setStatus(status_type);
        endorsement.setReferredBy(referenced_by);
        endorsement.setRefByPhone(reference_phone);
        endorsement.setPetitionerCategory(petitioner_type);
        if(petitioner_type.equals("General") || petitioner_type.equals("Others")){
            endorsement.setPetitionerName(petitioner_name);
        }
        else {
            endorsement.setPetitionerName(minister_type);
        }
        endorsement.setPetitionerMobile(petitioner_phone);
        endorsement.setDepartment(deparment);
        endorsement.setEndorsedOfficer(endorse_officer);
        endorsement.setReplyReceived(reply_type);
        endorsement.setAddress(address);
        endorsement.setHamlet(hamlet);

      //  endorsement.setCountry("India");
      //  endorsement.setState("Telangana");
        endorsement.setDistrict(district_type);
        endorsement.setMandal(mandel_type);
        endorsement.setVillage(village_type);
        endorsement.setPetitionDetails(petitionDetails);
        endorsement.setUsername(MyWall.getLoginId());
        Log.e("veera",endorsement.toString());
        progressDialog = ProgressDialog.show(CreateEndorsementActivity.this, "Please wait.",
                "Service Loading ...!", true);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), finalFile);
        //Log.e("request", String.valueOf(requestFile));
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("doc", finalFile.getName(), requestFile);

        // add another part within the multipart request
        RequestBody SerialNo = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getSerialNo());
        RequestBody EndorsementNo = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getEndorsementNo());
        RequestBody Status = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getStatus());
        RequestBody PetitionerCategory = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getPetitionerCategory());
        RequestBody PetitionerName = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getPetitionerName());
        RequestBody PetitionerMobile = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getPetitionerMobile());
        RequestBody CreateDate = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getCreateDate());
        RequestBody ReferredBy = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getReferredBy());
        RequestBody PetitionDetails = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getPetitionDetails());
        RequestBody EndorsedOfficer = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getEndorsedOfficer());
        RequestBody Department = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getDepartment());
        RequestBody Address = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getAddress());
        RequestBody Hamlet = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getHamlet());
        RequestBody Village = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getVillage());
        RequestBody Mandal = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getMandal());
        RequestBody District = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getDistrict());
        RequestBody ReplyReceived = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getReplyReceived());
        RequestBody RefByPhone = RequestBody.create(MediaType.parse("multipart/form-data"), endorsement.getRefByPhone());
        RequestBody username = RequestBody.create(MediaType.parse("multipart/form-data"),MyWall.getLoginId());
        RequestBody actTime = RequestBody.create(MediaType.parse("multipart/form-data"),endorsement.getCreateDate());
        RequestBody remarks = RequestBody.create(MediaType.parse("multipart/form-data")," ");
        
        Call<Boolean> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).
                createEndorsement(SerialNo,EndorsementNo,Status,PetitionerCategory,PetitionerName,PetitionerMobile,CreateDate,ReferredBy,PetitionDetails,
                        EndorsedOfficer,Department,Address,Hamlet,Village,Mandal,District,ReplyReceived,RefByPhone,username,actTime,remarks,body);
        listCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Boolean aBoolean= response.body();
                        if(aBoolean){
                            Toast.makeText(CreateEndorsementActivity.this, "Successfully Created...", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(CreateEndorsementActivity.this, MainActivity.class);
                            home.putExtra("type","endorse");
                            startActivity(home);
                            overridePendingTransition(R.anim.left_in, R.anim.right_out);
                        }else {
                            Toast.makeText(CreateEndorsementActivity.this, ""+aBoolean, Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(CreateEndorsementActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(CreateEndorsementActivity.this, "Create Endorse"+response.code(), Toast.LENGTH_SHORT).show();
                   /* Intent home = new Intent(EndorsementActivity.this, MainActivity.class);
                    home.putExtra("type","endorse");
                    startActivity(home);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);*/
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateEndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateEndorsementActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, (dialog, item) -> {
            boolean result= Utility.checkPermission(CreateEndorsementActivity.this);

            if (items[item].equals("Take Photo")) {
                userChoosenTask ="Take Photo";
                if(result)
                    cameraIntent();
            } else if (items[item].equals("Choose from Gallery")) {
                userChoosenTask ="Choose from Gallery";
                if(result)
                    openFileSelector();
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void openFileSelector() {

        final String[] ACCEPT_MIME_TYPES = {"application/*", "image/*"};
        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //   Intent gallery = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        gallery.setType("*/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            gallery.putExtra(Intent.EXTRA_MIME_TYPES, ACCEPT_MIME_TYPES);
        }
        if (gallery.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(gallery, "Select File"), FILE_REQUEST_CODE);
        } else {
            Toast.makeText(this, "no_support_for_read_external_storage", Toast.LENGTH_SHORT).show();
        }
    }
    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
           if (requestCode == REQUEST_CAMERA){
                try {
                    Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    assert photo != null;
                    Uri selectedDocumentUri = getImageUri(getApplicationContext(), photo);
                    String picturePath = null;
                    if (selectedDocumentUri.toString().startsWith("content://com.google.android.apps.photos.content")) {
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(selectedDocumentUri);
                            if (inputStream != null) {
                                Bitmap pictureBitmap = BitmapFactory.decodeStream(inputStream);
                                picturePath = RealPathUtil.getRealPath(CreateEndorsementActivity.this, getImageUri(CreateEndorsementActivity.this, pictureBitmap));
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        picturePath = RealPathUtil.getRealPath(CreateEndorsementActivity.this, selectedDocumentUri);

                    }
                    updateSelectedFilePath(picturePath);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Uri selectedDocumentUri = data.getData();
                    String picturePath = null;
                    if (Objects.requireNonNull(selectedDocumentUri).toString().startsWith("content://com.google.android.apps.photos.content")) {
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(selectedDocumentUri);
                            if (inputStream != null) {
                                Bitmap pictureBitmap = BitmapFactory.decodeStream(inputStream);
                                picturePath = RealPathUtil.getRealPath(CreateEndorsementActivity.this, getImageUri(CreateEndorsementActivity.this, pictureBitmap));
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        picturePath = RealPathUtil.getRealPath(CreateEndorsementActivity.this, selectedDocumentUri);
                    }
                    updateSelectedFilePath(picturePath);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        openFileSelector();
                } else {
                    Toast.makeText(this, "Please give read external storage permission to upload document.", Toast.LENGTH_SHORT).show();
                }
                break;
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Gallery"))
                        openFileSelector();
                }  //code for deny

                break;

        }
    }


    private void updateSelectedFilePath(String picturePath) {
        try {
            if (picturePath != null && !picturePath.isEmpty()) {
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                viewImage.setImageBitmap(thumbnail);
                card_view.setVisibility(View.VISIBLE);
            }
            else {
                card_view.setVisibility(View.GONE);
            }
             finalFile = new File(picturePath);
            this.selectedFilePath = picturePath;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (!Objects.equals(selectedFilePath, "")) {
                    selectedFileName = selectedFilePath.substring(selectedFilePath.lastIndexOf("/") + 1);
                    create_ticket_selected_file_name.setText(selectedFileName);
                    create_ticket_selected_file_layout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(this, "An error occurred while selecting image to upload.", Toast.LENGTH_SHORT).show();
                }
            }

           /* if(id.equals("idProof")){
                File finalFile = new File(picturePath);
                upload(finalFile,id);

            }
            else {
                File finalFile = new File(picturePath);
                upload(finalFile,id);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getVillage(String district_type, String mandel_type) {
        String url = String.format("%slocation/villages/Telangana/%s/%s", AppServerService.baseURL,district_type,mandel_type);
        Log.e("veera",url);
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Service Loading ...!", true);
        Call<List<Villages>> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getVillage(url);
        listCall.enqueue(new Callback<List<Villages>>() {
            @Override
            public void onResponse(@NonNull Call<List<Villages>> call, @NonNull Response<List<Villages>> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null && !response.body().isEmpty()){
                        village_array= new ArrayList<>();
                        village_array.add("Select");
                        for(int i=0;i<response.body().size();i++){
                            village_array.add(response.body().get(i).getVillageName());
                        }
                        categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, village_array);
                        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_village.setAdapter(categoriesSpinnerAdapter);
                    }
                    else {
                        Toast.makeText(CreateEndorsementActivity.this, "Villages data Found", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(CreateEndorsementActivity.this, " Villages : "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Villages>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateEndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getMandel(String district_type) {
        String url = String.format("%slocation/mandals/Telangana/%s", AppServerService.baseURL, district_type);
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Service Loading ...!", true);
        Call<List<Mandals>> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getMandel(url);
        listCall.enqueue(new Callback<List<Mandals>>() {
            @Override
            public void onResponse(@NonNull Call<List<Mandals>> call, @NonNull Response<List<Mandals>> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                   if(response.body()!=null && !response.body().isEmpty()){
                       mandel_array= new ArrayList<>();
                       mandel_array.add("Select");
                       for(int i=0;i<response.body().size();i++){
                           mandel_array.add(response.body().get(i).getMandalName());
                       }
                       categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, mandel_array);
                       categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                       sp_mandel.setAdapter(categoriesSpinnerAdapter);
                   }
                   else {
                       Toast.makeText(CreateEndorsementActivity.this, "Mandals data Found", Toast.LENGTH_SHORT).show();
                   }

                }
                else {
                    Toast.makeText(CreateEndorsementActivity.this, " Mandals : "+response.code(), Toast.LENGTH_SHORT).show();
                }


            }
            @Override
            public void onFailure(@NonNull Call<List<Mandals>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateEndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getInitDetails() {
        String url = String.format("%sendorsement/create/init", AppServerService.baseURL);
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Service Loading ...!", true);
        Call<InitEndorsement> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getInitEndorse(url);
        listCall.enqueue(new Callback<InitEndorsement>() {
            @Override
            public void onResponse(@NonNull Call<InitEndorsement> call, @NonNull Response<InitEndorsement> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){

                        InitEndorsement initEndorsement= response.body();
                        serialNo=initEndorsement.getFileSerialNo();
                        if(initEndorsement.getStatus()!=null && !initEndorsement.getStatus().isEmpty()){
                            for (int i=0;i<initEndorsement.getStatus().size();i++){
                                status_array.add(initEndorsement.getStatus().get(i).getListItem());
                            }
                            categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, status_array);
                            categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_status.setAdapter(categoriesSpinnerAdapter);
                            String myString = "New"; //the value you want the position for
                            int spinnerPosition = categoriesSpinnerAdapter.getPosition(myString);
                            //set the default according to value
                            sp_status.setSelection(spinnerPosition);
                        }

                        if(initEndorsement.getPositionsList()!=null && !initEndorsement.getPositionsList().isEmpty()){
                            for (int i=0;i<initEndorsement.getPositionsList().size();i++){
                                Petition_array.add(initEndorsement.getPositionsList().get(i).getListItem());
                            }
                            categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, Petition_array);
                            categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_petitioner.setAdapter(categoriesSpinnerAdapter);
                        }
                        if(initEndorsement.getDeptsList()!=null && !initEndorsement.getDeptsList().isEmpty()){
                            for (int i=0;i<initEndorsement.getDeptsList().size();i++){
                                department_array.add(initEndorsement.getDeptsList().get(i).getListItem());
                            }
                            categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, department_array);
                            categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_department.setAdapter(categoriesSpinnerAdapter);
                        }

                        if(initEndorsement.getDistricts()!=null && !initEndorsement.getDistricts().isEmpty()){
                            for (int i=0;i<initEndorsement.getDistricts().size();i++){
                                district_array.add(initEndorsement.getDistricts().get(i).getDistrictName());
                            }
                            categoriesSpinnerAdapter = new SpinnerAdapter(CreateEndorsementActivity.this, R.layout.spinner_row_item, district_array);
                            categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_district.setAdapter(categoriesSpinnerAdapter);
                        }
                    }
                    else {
                        Toast.makeText(CreateEndorsementActivity.this,"No Data Found", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(CreateEndorsementActivity.this," "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<InitEndorsement> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateEndorsementActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDatePicker(View view) {
        try {
            String preSelectedDate = selectedDateStr;
            if (selectedDateStr.isEmpty()) {
                preSelectedDate = formatDate(new Date(), SERVER_DATE_FORMAT);
            }
            String[] dateValues = preSelectedDate.split("-");
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEndorsementActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, (datePicker, year, monthOfYear, dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                selectedDateStr = formatDate(calendar.getTime(), SERVER_DATE_FORMAT);
                selectedDateDisplayStr = formatDate(calendar.getTime(), SELECTED_DATE_DISPLAY_FORMAT);
                txt_date.setText(selectedDateDisplayStr);
            }, Integer.parseInt(dateValues[0]), Integer.parseInt(dateValues[1]) - 1, Integer.parseInt(dateValues[2]));

            datePickerDialog.setCancelable(false);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

            datePickerDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String formatDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* Intent home = new Intent(getApplicationContext(), MainActivity.class);
        home.putExtra("type","endorse");
        startActivity(home);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);*/
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void cancelFileSelection(View view) {
        selectedFileName="";
        create_ticket_selected_file_layout.setVisibility(View.GONE);
        create_ticket_selected_file_name.setText("");
        card_view.setVisibility(View.GONE);
    }

    public void closeActivity(View view) {
        onBackPressed();
    }
}

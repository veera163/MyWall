package com.frazen.edaftar.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.frazen.edaftar.Model.AppAccessInfo;
import com.frazen.edaftar.Model.User;
import com.frazen.edaftar.Model.UserDomain;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class LoginActivity extends AppCompatActivity {

    Button sign_up;
    TextView forgot_password;
    EditText ed_userName,ed_password;
    CheckBox showPass;
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign_up=findViewById(R.id.sign_up);
        forgot_password=findViewById(R.id.forgot_password);
        ed_userName=findViewById(R.id.ed_userName);
        ed_password=findViewById(R.id.ed_password);
        showPass=findViewById(R.id.show_pass);
        showPass.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                ed_password.setSelection(ed_password.getText().length());

            }
            else {
                ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                ed_password.setSelection(ed_password.getText().length());

            }
        });
        forgot_password.setOnClickListener(v -> {

            Toast.makeText(this, "In Process", Toast.LENGTH_SHORT).show();

        });
        sign_up.setOnClickListener(v -> {
            attemptLogin();
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (MyWall.validateAccessToken()) {
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            home.putExtra("type","dashboard");
            startActivity(home);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
       /* else {
            Toast.makeText(this, "Session Timeout !", Toast.LENGTH_SHORT).show();
        }*/
    }

    private void attemptLogin() {

        String username = ed_userName.getText().toString().trim();
        String password = ed_password.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(username)){

            Toast.makeText(this, "Enter User Name", Toast.LENGTH_SHORT).show();
            focusView = ed_userName;
            cancel = true;
        }
        else if(TextUtils.isEmpty(password)){

            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            focusView = ed_password;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }
        else{
            appLogin(username,password);
        }
    }

    private void appLogin(String username, String password) {

        String clientId = "iamclient";
        String secret = "system38567";
        String grantType = "password";
        progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait.",
                "Service Loading ...!", true);
        APIServiceCalls loginServiceCall = AppServerService.createService(APIServiceCalls.class, clientId, secret);
        Call<AppAccessInfo> call = loginServiceCall.userLogin(grantType, username,password);
        call.enqueue(new Callback<AppAccessInfo>() {
            @Override
            public void onResponse(@Nullable Call<AppAccessInfo> call,@Nullable Response<AppAccessInfo> response) {
                if (response != null) {
                    if(response.isSuccessful()){
                        if(response.body()!=null){
                            AppAccessInfo appAccessInfo = response.body();
                            Log.e("veera",String.valueOf(appAccessInfo.getAccessToken()));
                            if(appAccessInfo.getAccessToken()!=null && !appAccessInfo.getAccessToken().isEmpty()){
                                MyWall.setLoginId(username);
                                MyWall.setAccessToken(appAccessInfo.getAccessToken(), appAccessInfo.getRefreshToken(), Integer.parseInt(appAccessInfo.getExpiresIn()));
                                userInfo();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Token is Missing", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, " Token Data is Missing .... ", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, " token : "+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@Nullable  Call<AppAccessInfo> call, @Nullable Throwable t) {
                progressDialog.dismiss();
                if (t != null) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userInfo() {
        String url = AppServerService.baseURL+"settings/user/policies/"+MyWall.getLoginId();
        Call<UserDomain> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getUser(url);
        listCall.enqueue(new Callback<UserDomain>() {
            @Override
            public void onResponse(@NonNull Call<UserDomain> call, @NonNull Response<UserDomain> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
                        UserDomain domain = response.body();
                        Log.e("veera",domain.toString());
                        User user=domain.getUser();
                        MyWall.setUserInfo(user.getId(),user.getActive(),user.getUsername());
                        MyWall.setRoles(user.getRoles());
                            if(user.getRoles()!=null && !user.getRoles().isEmpty()){
                                if(user.getRoles().get(0).equals("SYS_ADMIN") || user.getRoles().get(0).equals("ADMIN") || user.getRoles().get(0).equals("USER")){
                                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                                    home.putExtra("type","dashboard");
                                    startActivity(home);
                                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Not Authorized", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Role is Missing", Toast.LENGTH_SHORT).show();
                            }
                    } else {
                        Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Log.e("veera",response.toString());
                    Toast.makeText(LoginActivity.this, " user : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDomain> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

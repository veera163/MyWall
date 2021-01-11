package com.frazen.edaftar.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.frazen.edaftar.R;

public class SplashActivity extends AppCompatActivity {
  public  CardView cardView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkAndMove();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        cardView = findViewById(R.id.splash_icon);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation zoom = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_screen);

        AnimationSet s = new AnimationSet(false);
        s.addAnimation(zoom);
        cardView.startAnimation(s);

        s.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                checkAndMove();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    private void checkAndMove() {

        if (requirePermissionCheck()) {
            Intent logInPage = new Intent(this, LoginActivity.class);
            logInPage.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(logInPage);
            finish();

        } else {

            askPermssion();
        }
    }

    private void askPermssion() {
        int REQ_PERMISSIONS = 7;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, REQ_PERMISSIONS);
    }

    private boolean requirePermissionCheck() {
        return ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
}

package com.frazen.edaftar.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frazen.edaftar.Fragments.DashBoardFragment;
import com.frazen.edaftar.Fragments.EndorsementFragment;
import com.frazen.edaftar.Fragments.MeetingsFragment;
import com.frazen.edaftar.Model.DashBoardData;
import com.frazen.edaftar.MyWall;
import com.frazen.edaftar.R;
import com.frazen.edaftar.Util.GPSTracker;
import com.frazen.edaftar.api.APIServiceCalls;
import com.frazen.edaftar.api.AppServerService;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    protected Context activityContext;
    ImageView imageView;
    TextView userName,mTitle;
    String type;
    GPSTracker gpsTracker;
    public static DashBoardData dashBoardData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gpsTracker= new GPSTracker(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = (bundle.getString("type"));
        }
        activityContext = MainActivity.this;
        Toolbar toolbar = findViewById(R.id.toolbar);
         mTitle = findViewById(R.id.txt_title);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        userName = navigationView.getHeaderView(0).findViewById(R.id.name);
        imageView= navigationView.getHeaderView(0).findViewById(R.id.img_profile);
        Log.e("veera",MyWall.getAccessToken());
        getDashBoardData();



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getDashBoardData() {
        String url = AppServerService.baseURL+"dashboard/data";
        Log.e("veera",url);

        Call<DashBoardData> listCall = AppServerService.createService(APIServiceCalls.class, "Bearer " + MyWall.getAccessToken()).getDashBoard(url);
        listCall.enqueue(new Callback<DashBoardData>() {
            @Override
            public void onResponse(@NonNull Call<DashBoardData> call, @NonNull Response<DashBoardData> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        dashBoardData = response.body();

                        if(type.equals("endorse")){
                            displaySelectedScreen(R.id.endorsement);
                        }
                        else if(type.equals("meeting")){
                            displaySelectedScreen(R.id.meeting);
                        }
                        else {
                            displaySelectedScreen(R.id.dashboard);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("veera",response.toString());
                    if(response.code()==401){
                        TextView textView = new TextView(MainActivity.this);
                        textView.setText("Session Timeout !");
                        textView.setGravity(Gravity.START);
                        textView.setPadding(30, 20, 20, 20);
                        textView.setTextSize(18F);
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                        textView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                        textView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));

                        AlertDialog.Builder  alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setCustomTitle(textView);
                        alertDialogBuilder
                                .setMessage("\n Your session has expired. Please login")
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        (dialog, id) -> {
                                            // make first time launch TRUE
                                            MyWall.clearCache();
                                            Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.left_in, R.anim.right_out);
                                            finish();
                                        });
                               /* .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });*/

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "DashBoard: "+ response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<DashBoardData> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.exit:
                TextView textView = new TextView(MainActivity.this);
                textView.setText("Exit !");
                textView.setGravity(Gravity.START);
                textView.setPadding(30, 20, 20, 20);
                textView.setTextSize(18F);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                textView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));

                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setCustomTitle(textView);
                alertDialogBuilder
                        .setMessage("\nWould you like to close this app")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                (dialog, id) -> {
                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_HOME);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                })
                        .setNegativeButton("No", (dialog, id) -> dialog.cancel());
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.signout:

                TextView text = new TextView(MainActivity.this);
                text.setText("SignOut !");
                text.setGravity(Gravity.START);
                text.setPadding(30, 20, 20, 20);
                text.setTextSize(18F);
                text.setTypeface(Typeface.DEFAULT_BOLD);
                text.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                text.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setCustomTitle(text);
                alertDialogBuilder
                        .setMessage("\nWould you like to Sign out this app")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                (dialog, id) -> {
                                    // make first time launch TRUE
                                    MyWall.clearCache();
                                    Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                                    finish();
                                })
                        .setNegativeButton("No", (dialog, id) -> dialog.cancel());

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        DashBoardFragment dashBoardFragment =new DashBoardFragment();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (getVisibleFragment() instanceof DashBoardFragment) {
            TextView text = new TextView(MainActivity.this);
            text.setText("Exit !");
            text.setGravity(Gravity.START);
            text.setPadding(30, 20, 20, 20);
            text.setTextSize(18F);
            text.setTypeface(Typeface.DEFAULT_BOLD);
            text.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
            text.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
             alertDialogBuilder = new AlertDialog.Builder(this);
              alertDialogBuilder.setCustomTitle(text);
              alertDialogBuilder
                      .setMessage("\nWould you like to close this app")
                      .setCancelable(false)
                      .setPositiveButton("Yes",
                              (dialog, id) -> {
                                  Intent intent = new Intent(Intent.ACTION_MAIN);
                                  intent.addCategory(Intent.CATEGORY_HOME);
                                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                  startActivity(intent);
                                  finish();
                              })
                      .setNegativeButton("No", (dialog, id) -> dialog.cancel());
              alertDialog = alertDialogBuilder.create();
              alertDialog.show();
            // super.onBackPressed();
        }
        else {
            if (dashBoardFragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, dashBoardFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
    }
    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        Fragment fragment = null;

        //initializing the fragment String which is selected
        switch (itemId) {
            case R.id.dashboard:
                fragment = new DashBoardFragment();
                mTitle.setText("Dashboard");
                break;
            case R.id.meeting:
                fragment = new MeetingsFragment();
                mTitle.setText("Meetings");
                break;
            case R.id.endorsement:
                fragment = new EndorsementFragment();
                mTitle.setText("Endorsement");
                break;
            case R.id.nav_share:
                if(gpsTracker.canGetLocation()){

                    googleIntent(gpsTracker.getLocation());
                }else {
                    gpsTracker.showSettingsAlert();
                }
                break;
        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void googleIntent(Location address) {

        Intent i = new Intent(Intent.ACTION_SEND);
        String location = address.getLatitude()+ "," + address.getLongitude();
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Current Location");
        i.putExtra(Intent.EXTRA_TEXT, " http://maps.google.com/maps?q=loc:" + location);

        try {
            startActivity(Intent.createChooser(i, "Share Location"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Not found any app", Toast.LENGTH_LONG).show();
        }
      /*  try
        {
            // Launch Google to look for address:
            Uri mapIntentUri = Uri.parse("google.navigation:q=" + location);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        catch ( ActivityNotFoundException ex  )
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps")));
        }*/
    }
}
package com.frazen.edaftar.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.frazen.edaftar.Fragments.EndorsementStatusFragment;
import com.frazen.edaftar.Fragments.GrievancesFragment;
import com.frazen.edaftar.Fragments.LettersFragment;
import com.frazen.edaftar.Fragments.LocFragment;
import com.frazen.edaftar.Fragments.PeshiFragment;
import com.frazen.edaftar.Fragments.ReimbursementFragment;
import com.frazen.edaftar.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DashBoardActivity extends FragmentActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private boolean isBackPressed = false;
    private final List<Integer> tabsInBack = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        viewPager = findViewById(R.id.pager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(!isBackPressed){
                    tabsInBack.add(tab.getPosition());
                }
                else {
                    isBackPressed = false;}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GrievancesFragment(), " Grievances");
        adapter.addFragment(new ReimbursementFragment(), "Reimbursement");
        adapter.addFragment(new LocFragment(), "LOC");
        adapter.addFragment(new PeshiFragment(), "Peshi-Files");
        adapter.addFragment(new EndorsementStatusFragment(), "Endorsement");
        adapter.addFragment(new LettersFragment(), "Letters");
        viewPager.setAdapter(adapter);
    }

    public void closeActivity(View view) {
        onBackPressed();
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            home.putExtra("type","meeting");
            startActivity(home);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }else {
            //If any other tab is open, then switch to first tab
            viewPager.setCurrentItem(0,true);
        }

       /* // one By One
        int currentItem = viewPager.getCurrentItem();
        if (currentItem != 0) {
            viewPager.setCurrentItem(currentItem - 1, true);
        } else {
            super.onBackPressed();
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            home.putExtra("type","meeting");
            startActivity(home);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }*/
    }
}

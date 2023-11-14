package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.du_an_1.controller.fragment.AccountFragment;
import com.example.du_an_1.controller.fragment.Fragment_list_bill;
import com.example.du_an_1.controller.fragment.HomeFragment;
import com.example.du_an_1.controller.fragment.ListProductsFragment;
import com.example.du_an_1.databinding.ActivityMainBinding;
import com.example.du_an_1.funtions.MyFragment;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        initView();
        MobileAds.initialize(this);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

        // Đặt thời gian chờ để reset trạng thái doubleBackToExitPressedOnce
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                },
                2000 // 2 giây
        );
    }

    private void initView() {
        MyFragment.replaceFragment(this.getSupportFragmentManager()
                , R.id.fragmentContainer
                , new HomeFragment()
                , false);

        // ánh xạ sự kiện khi nhấn vào bottom nav view

        mainBinding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottomNavHome){
                    MyFragment.replaceFragment(MainActivity.this.getSupportFragmentManager()
                            , R.id.fragmentContainer
                            , new HomeFragment()
                            , false);
                } else if (item.getItemId() == R.id.bottomNavBill) {
                    MyFragment.replaceFragment(MainActivity.this.getSupportFragmentManager()
                            , R.id.fragmentContainer
                            , new Fragment_list_bill()
                            , false);
                } else if (item.getItemId() == R.id.bottomNavProduct) {
                    MyFragment.replaceFragment(MainActivity.this.getSupportFragmentManager()
                            , R.id.fragmentContainer
                            , new ListProductsFragment()
                            , false);
                } else if (item.getItemId() == R.id.bottomNavAccount) {
                    MyFragment.replaceFragment(MainActivity.this.getSupportFragmentManager()
                            , R.id.fragmentContainer
                            , new AccountFragment()
                            , false);
                }
                return true;
            }
        });
    }


}
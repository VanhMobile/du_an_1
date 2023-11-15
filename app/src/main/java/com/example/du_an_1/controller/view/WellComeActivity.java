package com.example.du_an_1.controller.view;

import static com.example.du_an_1.funtions.NetworkUtils.isNetworkAvailable;
import static com.example.du_an_1.funtions.NetworkUtils.openNetworkSettings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.example.du_an_1.databinding.ActivityWellComeBinding;

public class WellComeActivity extends AppCompatActivity {

    private ActivityWellComeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityWellComeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);

        if (isNetworkAvailable(this)){
            chuyenman();
        } else {
             new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WellComeActivity.this);
                    builder.setTitle("Không có kết nối mạng");
                    builder.setMessage("Vui lòng kết nối với một mạng để tiếp tục.");

                    builder.setPositiveButton("Cài đặt mạng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Mở cài đặt mạng khi người dùng chọn
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            finish();
                        }
                    });

                    builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Đóng ứng dụng hoặc thực hiện các hành động khác
                            finish();
                        }
                    });

                    builder.show();
                }
            },2000);

        }
    }

    private void chuyenman() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WellComeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
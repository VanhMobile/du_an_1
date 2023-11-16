package com.example.du_an_1.controller.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.ActivityConfrimPasswordBinding;
import com.example.du_an_1.databinding.ActivityOtpConfirmBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpConfirmActivity extends AppCompatActivity {

    ActivityOtpConfirmBinding binding ;
    private  String phoneNumber;
    private String id ;
    private FirebaseAuth auth;
    public static final  String TAG = OtpConfirmActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
        auth = FirebaseAuth.getInstance();
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = binding.edtOtp.getText().toString().trim();
                onClichSendOtp(otp);
            }
        });
    }

    private void onClichSendOtp(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, otp);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            goToChangePass(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(OtpConfirmActivity.this, "fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void getData(){
        phoneNumber = getIntent().getStringExtra("phone_number");
        id = getIntent().getStringExtra("id");
    }
    private void goToChangePass(String phoneNumber) {
        Intent intent = new Intent(this , NewPassActivity.class );
        intent.putExtra("phone_number", phoneNumber);
        startActivity(intent);
    }
}
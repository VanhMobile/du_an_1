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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ConfrimEmailActivity extends AppCompatActivity {

    public static final String TAG = ConfrimEmailActivity.class.getName();
    private  ActivityConfrimPasswordBinding confrimPasswordBinding ;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        confrimPasswordBinding = ActivityConfrimPasswordBinding.inflate(getLayoutInflater());
        setContentView(confrimPasswordBinding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        confrimPasswordBinding.btnBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfrimEmailActivity.this , LoginActivity.class );
                startActivity(intent);
            }
        });
        confrimPasswordBinding.btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = confrimPasswordBinding.edtNumberPhone.getText().toString().trim();
                onclickverify(sdt);
            }
        });
    }

    private void onclickverify(String sdt) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(sdt)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                                Log.e(TAG , "abc");
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(ConfrimEmailActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                goToOtp(sdt , s);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
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
                                Toast.makeText(ConfrimEmailActivity.this, "fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToChangePass(String phoneNumber) {
        Intent intent = new Intent(this , NewPassActivity.class );
        intent.putExtra("phone_number", phoneNumber);
        startActivity(intent);
    }
    private void goToOtp(String sdt, String s) {
        Intent intent = new Intent(this , OtpConfirmActivity.class );
        intent.putExtra("phone_number", sdt);
        intent.putExtra("id" , s);
        startActivity(intent);
    }
}
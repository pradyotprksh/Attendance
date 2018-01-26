package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class TeacherActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText teacherEmail, teacherPassword;
    private Button teacherLoginButton, noAccount;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        teacherLoginButton = findViewById(R.id.teacherLoginBtn);
        mAuth = FirebaseAuth.getInstance();
        teacherEmail = findViewById(R.id.teacherEmailId);
        teacherPassword = findViewById(R.id.teacherPassword);
        noAccount = findViewById(R.id.noAccount);
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(TeacherActivity.this, TeacherControl.class));
            finish();
        }
        teacherLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = teacherEmail.getText().toString();
                final String password = teacherPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please Enter your email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(TeacherActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(TeacherActivity.this, "Are you really a Teacher? Or You have made a mistake.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(TeacherActivity.this, "Done Successfully Log In", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(TeacherActivity.this, TeacherControl.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherActivity.this, "Create your account.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TeacherActivity.this, CreateTeacherAccount.class);
                startActivity(intent);
            }
        });
    }
}

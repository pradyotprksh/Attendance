package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateTeacherAccount extends AppCompatActivity {

    private EditText teacherUserName, teacherEmail, teacherPassword;
    private Button addAccount;
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher_account);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        teacherUserName = findViewById(R.id.userName);
        teacherEmail = findViewById(R.id.teacherEmail);
        teacherPassword = findViewById(R.id.teacherPassword);
        addAccount = findViewById(R.id.createTeacherAccount);
        mAuth = FirebaseAuth.getInstance();
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = teacherUserName.getText().toString();
                String email = teacherEmail.getText().toString();
                String password = teacherPassword.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateTeacherAccount.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Something is wrong. Try Again.", Toast.LENGTH_SHORT).show();
                                } else {
                                    FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                                    String uid = mCurrentUser.getUid();
                                    mFirebaseDatabase = FirebaseDatabase
                                            .getInstance()
                                            .getReference()
                                            .child("Users")
                                            .child("Teacher")
                                            .child("SubjectTeacher")
                                            .child(uid);
                                    mFirebaseDatabase.child("Name").setValue(userName);
                                    mFirebaseDatabase.child("Number").setValue(number);
//                                    HashMap<String, String> initialData1 = new HashMap<>();
//                                    HashMap<String, Integer> initialData2 = new HashMap<>();
//                                    HashMap<String, HashMap> initialData = new HashMap<>();
//                                    initialData1.put("Name", userName);
//                                    initialData2.put("Number", number);
//                                    initialData.put("Name",initialData2);
//                                    mFirebaseDatabase.setValue(initialData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
                                    Intent intent = new Intent(CreateTeacherAccount.this, TeacherActivity.class);
                                    startActivity(intent);
                                    finish();
//                                            } else {
//                                                Toast.makeText(getApplicationContext(), "Something is wrong.", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    });
                                }
                            }
                        });
            }
        });
    }
}

package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentSMSInformation extends AppCompatActivity {

    String branch, semester, className;
    private DatabaseReference mFirebaseDatabase, mFirebaseDatabase1;
    private ListView studentList;
    private StudentListAdapter studentListAdapter;
    private List<StudentUsn> mStudentList;
    Button sendSms;
    int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    String email, name, parentNumber, phone, usn, days, totalDays, percentage, subject, time, date, value, finalMessage, appendMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_smsinformation);
        Intent intent = getIntent();
        branch = intent.getStringExtra("branchName");
        semester = intent.getStringExtra("semesterName");
        className = intent.getStringExtra("className");
        sendSms = findViewById(R.id.sendSms);
        // add value to list
        mFirebaseDatabase = FirebaseDatabase
                .getInstance().getReference()
                .child("Users").child("Student").child(branch).child(semester).child(className);
        studentList = findViewById(R.id.studentList);
        mStudentList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase
                .getInstance().getReference()
                .child("Users").child("Student").child(branch).child(semester).child(className);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String email = (String) dataSnapshot1.child("Email").getValue();
                    String name = (String) dataSnapshot1.child("Name").getValue();
                    String parentNumber = (String) dataSnapshot1.child("ParentNumber").getValue();
                    String phone = (String) dataSnapshot1.child("Phone").getValue();
                    String usn = (String) dataSnapshot1.child("Usn").getValue();
                    mStudentList.add(new StudentUsn(email, name, parentNumber, phone, usn));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
            }
        });
        studentListAdapter = new StudentListAdapter(getApplicationContext(), mStudentList);
        studentList.setAdapter(studentListAdapter);
        // send sms
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase
                        .getInstance().getReference()
                        .child("Users").child("Student").child(branch).child(semester).child(className);
                mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            email = (String) dataSnapshot1.child("Email").getValue();
                            name = (String) dataSnapshot1.child("Name").getValue();
                            parentNumber = (String) dataSnapshot1.child("ParentNumber").getValue();
                            phone = (String) dataSnapshot1.child("Phone").getValue();
                            usn = (String) dataSnapshot1.child("Usn").getValue();
                            finalMessage = "Hello,\nYour Son/Daughter " + name
                                    + "\nUSN: " + usn
                                    + "\nEmail-Id: " + email
                                    + "\nPhone Number: " + phone
                                    + "\n========================";
                            mFirebaseDatabase1 = FirebaseDatabase
                                    .getInstance().getReference()
                                    .child("Users").child("Attendance").child(branch).child(semester)
                                    .child(className).child(usn);
                            mFirebaseDatabase1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                        days = dataSnapshot2.child("Days").getValue().toString();
                                        totalDays = dataSnapshot2.child("TotalDays").getValue().toString();
                                        percentage = dataSnapshot2.child("Percentage").getValue().toString();
                                        subject = dataSnapshot2.getKey();
                                        for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                                            for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                                date = dataSnapshot3.getKey();
                                                time = dataSnapshot4.getKey();
                                                value = dataSnapshot4.getValue().toString();
                                            }
                                        }
                                        appendMessage = appendMessage.concat("\nSubject : " + subject
                                                + "\nDate: " + date
                                                + "\nAbsent/Present: " + value
                                                + "\n----------------------");
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            if (ContextCompat.checkSelfPermission(StudentSMSInformation.this, android.Manifest.permission.READ_PHONE_STATE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(StudentSMSInformation.this,
                                        new String[]{android.Manifest.permission.READ_PHONE_STATE},
                                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                            }
                            if (ContextCompat.checkSelfPermission(StudentSMSInformation.this, android.Manifest.permission.SEND_SMS)
                                    != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(StudentSMSInformation.this,
                                        new String[]{android.Manifest.permission.SEND_SMS},
                                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                            } else {
                                finalMessage = "Hello,\nYour Son/Daughter " + name
                                        + "\nUSN: " + usn
                                        + "\nEmail-Id: " + email
                                        + "\nPhone Number: " + phone
                                        + "\n========================";
                                SmsManager sms = SmsManager.getDefault();
                                if (android.os.Build.VERSION.SDK_INT >= 22) {
                                    Log.e("Alert", "Checking SubscriptionId");
                                    try {
                                        Log.e("Alert", "SubscriptionId is " + sms.getSubscriptionId());
                                    } catch (Exception e) {
                                        Log.e("Alert", e.getMessage());
                                        Log.e("Alert", "Fixed SubscriptionId to 1");
                                        sms = SmsManager.getSmsManagerForSubscriptionId(1);
                                    }
                                }
                                sms.sendTextMessage(parentNumber, null, finalMessage, null, null);
                                Toast.makeText(getApplicationContext(), finalMessage, Toast.LENGTH_SHORT).show();
//                                        Toast.makeText(getApplicationContext(), appendMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(StudentSMSInformation.this,
                TeacherControl.class);
        startActivity(myIntent);
        finish();
    }
}

package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private DatabaseReference mFirebaseDatabase;
    private ListView studentList;
    private StudentListAdapter studentListAdapter;
    private List<StudentUsn> mStudentList;
    Button sendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_smsinformation);
        Intent intent = getIntent();
        branch = intent.getStringExtra("branchName");
        semester = intent.getStringExtra("semesterName");
        className = intent.getStringExtra("className");
        sendSms = findViewById(R.id.sendSms);
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
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentInformation extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase1;
    String branchSelected, semesterSelected, classSelected, usn;
    String currentDaysString, currentTotalString, percentageString;
    private ListView attendanceList;
    private StudentAttendanceListAdapter attendanceListAdapter;
    private List<StudentAttendance> mAttendanceList;
    String subject;
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        Intent intent = getIntent();
        branchSelected = intent.getStringExtra("branch");
        usn = intent.getStringExtra("usn");
        semesterSelected = intent.getStringExtra("semester");
        classSelected = intent.getStringExtra("className");
        refresh = findViewById(R.id.refreshBtn);
        attendanceList = findViewById(R.id.attendanceList);
        mAttendanceList = new ArrayList<>();
        mFirebaseDatabase1 = FirebaseDatabase.getInstance()
                .getReference().child("Users")
                .child("Attendance").child(branchSelected).child(semesterSelected).child(classSelected).child(usn);
        mFirebaseDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    subject = dataSnapshot1.getKey();
                    currentDaysString = (dataSnapshot1.child("Days").getValue().toString());
                    currentTotalString = (dataSnapshot1.child("TotalDays").getValue().toString());
                    percentageString = (dataSnapshot1.child("Percentage").getValue().toString());
//                    Toast.makeText(getApplicationContext(), percentageString, Toast.LENGTH_SHORT).show();
                    mAttendanceList.add(new StudentAttendance(subject, percentageString));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        attendanceListAdapter = new StudentAttendanceListAdapter(getApplicationContext(), mAttendanceList);
        attendanceList.setAdapter(attendanceListAdapter);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                intent.putExtra("usn", usn);
                intent.putExtra("branch", branchSelected);
                intent.putExtra("semester", semesterSelected);
                intent.putExtra("className", classSelected);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(StudentInformation.this,
                StudentActivity.class);
        startActivity(myIntent);
        finish();
    }
}

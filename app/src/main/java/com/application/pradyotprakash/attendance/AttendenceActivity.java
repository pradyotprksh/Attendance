package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

public class AttendenceActivity extends AppCompatActivity {

    private ListView studentList;
    private StudentListAdapter studentListAdapter;
    private List<StudentUsn> mStudentList;
    String branch, semester, className, subject;
    private DatabaseReference mFirebaseDatabase;
    private Button uploadFiles, sendNotification;
    int FILE_UPLOAD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        final Intent intent = getIntent();
        branch = intent.getStringExtra("branch");
        semester = intent.getStringExtra("semester");
        className = intent.getStringExtra("className");
        subject = intent.getStringExtra("subject");
        uploadFiles = findViewById(R.id.uploadFiles);
        sendNotification = findViewById(R.id.sendNotification);
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
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AttendenceActivity.this, AttendenceCalculation.class);
                intent.putExtra("branch", branch);
                intent.putExtra("semester", semester);
                intent.putExtra("className", className);
                String usn = mStudentList.get(position).getUsn();
                intent.putExtra("usn", usn);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });
        uploadFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AttendenceActivity.this,UploadingFiles.class);
                startActivity(intent1);
                finish();
            }
        });
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AttendenceActivity.this,SendingNotification.class);
                intent1.putExtra("branch", branch);
                intent1.putExtra("semester", semester);
                intent1.putExtra("className", className);
                intent1.putExtra("subject", subject);
                startActivity(intent1);
                finish();
            }
        });
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(AttendenceActivity.this,
                TeacherControl.class);
        startActivity(myIntent);
        finish();
    }
}

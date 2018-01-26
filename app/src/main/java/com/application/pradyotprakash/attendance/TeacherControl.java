package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherControl extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase, mFirebaseDatabase1, mFirebaseDatabase2;
    private TextView userName;
    private Button addDetails;
    private ListView classList;
    private ClassTeacherListAdapter classListAdapter;
    private List<ClassTeacher> mClassList;
    private ListView subjectList;
    private SubjectTeacherListAdapter subjectListAdapter;
    private List<SubjectTeacher> mSubjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_control);
        FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mCurrentUser.getUid();
        userName = findViewById(R.id.teacherName);
        addDetails = findViewById(R.id.addDetails);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher").child("SubjectTeacher").child(uid);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String tUserName = dataSnapshot.child("Name").getValue().toString();
                userName.setText(tUserName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
            }
        });
        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherControl.this, AddDetails.class));
                finish();
            }
        });
        classList = findViewById(R.id.classTeacherInforamtion);
        mClassList = new ArrayList<>();
        mFirebaseDatabase1 = FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher").child("ClassTeacher").child(uid);
        mFirebaseDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) {
                    String className = "No Data Available";
                    String branchName = "No Data Available";
                    String semesterName = "No Data Available";
                    mClassList.add(new ClassTeacher(branchName, className, semesterName));
                } else {
                    String className = (String) dataSnapshot.child("ClassName").getValue();
                    String branchName = (String) dataSnapshot.child("Branch").getValue();
                    String semesterName = (String) dataSnapshot.child("Semester").getValue();
                    mClassList.add(new ClassTeacher(branchName, className, semesterName));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
            }
        });
        classListAdapter = new ClassTeacherListAdapter(getApplicationContext(), mClassList);
        classList.setAdapter(classListAdapter);
        // subject list
        subjectList = findViewById(R.id.subjectTeacherInforamtion);
        mSubjectList = new ArrayList<>();
        mFirebaseDatabase2 = FirebaseDatabase
                .getInstance().getReference()
                .child("Users").child("Teacher").child("SubjectTeacher").child(uid);
        mFirebaseDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        String teacherBranch = dataSnapshot2.getKey();
                        for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                            String teacherSemester = dataSnapshot3.getKey();
                            for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                String teacherClass = dataSnapshot4.getKey();
                                String subject = (String) dataSnapshot4.child("Subject").getValue();
                                mSubjectList.add(new SubjectTeacher(teacherBranch, teacherSemester, teacherClass, subject));
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
            }
        });
        subjectListAdapter = new SubjectTeacherListAdapter(getApplicationContext(), mSubjectList);
        subjectList.setAdapter(subjectListAdapter);
        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TeacherControl.this, AttendenceActivity.class);
                String branch = mSubjectList.get(position).getBranch();
                String semester = mSubjectList.get(position).getSemester();
                String className = mSubjectList.get(position).getClassName();
                String subject = mSubjectList.get(position).getSubject();
                intent.putExtra("branch", branch);
                intent.putExtra("semester", semester);
                intent.putExtra("className", className);
                intent.putExtra("subject", subject);
                startActivity(intent);
                finish();
            }
        });
    }
}

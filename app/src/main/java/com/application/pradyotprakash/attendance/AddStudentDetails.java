package com.application.pradyotprakash.attendance;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStudentDetails extends AppCompatActivity {

    EditText studentName, studentUsn, studentPhoneNumber, parentPhoneNumber, studentEmailId;
    TextView lastAdded;
    Button addDetails, doneAdding;
    private DatabaseReference mFirebaseDatabase, mFirebaseDatabase1;
    String uid, studentBranch, studentClass, studentSemester;
    int numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_details);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        studentName = findViewById(R.id.studentName);
        studentUsn = findViewById(R.id.studentUSN);
        studentPhoneNumber = findViewById(R.id.studentPhoneNumber);
        parentPhoneNumber = findViewById(R.id.parentPhoneNumber);
        studentEmailId = findViewById(R.id.studentEmail);
        addDetails = findViewById(R.id.addDetails);
        doneAdding = findViewById(R.id.doneAdding);
        lastAdded = findViewById(R.id.lastAdded);
        FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = mCurrentUser.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher").child("ClassTeacher").child(uid);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentBranch = dataSnapshot.child("Branch").getValue().toString();
                studentClass = dataSnapshot.child("ClassName").getValue().toString();
                studentSemester = dataSnapshot.child("Semester").getValue().toString();
                numbers = Integer.parseInt(dataSnapshot.child("Numbers").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
            }
        });
        addDetails.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String name = studentName.getText().toString();
                String usn = studentUsn.getText().toString();
                String phn = studentPhoneNumber.getText().toString();
                String parent = parentPhoneNumber.getText().toString();
                String email = studentEmailId.getText().toString();
                mFirebaseDatabase1 = FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher").child("ClassTeacher").child(uid);
                mFirebaseDatabase1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        numbers = Integer.parseInt(dataSnapshot.child("Numbers").getValue().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
                    }
                });
                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Student");
                mFirebaseDatabase
                        .child(studentBranch)
                        .child(studentSemester)
                        .child(studentClass)
                        .child("StudentDetails" + numbers)
                        .child("Name")
                        .setValue(name);
                mFirebaseDatabase
                        .child(studentBranch)
                        .child(studentSemester)
                        .child(studentClass)
                        .child("StudentDetails" + numbers)
                        .child("Usn")
                        .setValue(usn);
                mFirebaseDatabase
                        .child(studentBranch)
                        .child(studentSemester)
                        .child(studentClass)
                        .child("StudentDetails" + numbers)
                        .child("Phone")
                        .setValue(phn);
                mFirebaseDatabase
                        .child(studentBranch)
                        .child(studentSemester)
                        .child(studentClass)
                        .child("StudentDetails" + numbers)
                        .child("ParentNumber")
                        .setValue(parent);
                mFirebaseDatabase
                        .child(studentBranch)
                        .child(studentSemester)
                        .child(studentClass)
                        .child("StudentDetails" + numbers)
                        .child("Email")
                        .setValue(email);
                studentName.setText("");
                studentUsn.setText("");
                studentPhoneNumber.setText("");
                parentPhoneNumber.setText("");
                studentEmailId.setText("");
                lastAdded.setText("Last Added Student USN is: "+usn);
                numbers = numbers + 1;
                mFirebaseDatabase1.child("Numbers").setValue(numbers);
            }
        });
    }
}

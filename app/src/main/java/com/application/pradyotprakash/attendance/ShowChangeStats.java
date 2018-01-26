package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowChangeStats extends AppCompatActivity {

    String branch, semester, className, subject, usn, percentageString, daysString, totalDaysString, addDaysString;
    TextView usnText, classText, subjectText, totalDaysText, presentDaysText, percentageText, presentDaysText1, percentageText1;
    EditText addDays;
    Button confirm;
    private DatabaseReference mFirebaseDatabase1, mFirebaseDatabase;
    double days, totalDays, percentage, addDaysValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_change_stats);
        Intent intent = getIntent();
        branch = intent.getStringExtra("branch");
        semester = intent.getStringExtra("semester");
        className = intent.getStringExtra("className");
        subject = intent.getStringExtra("subject");
        usn = intent.getStringExtra("usn");
        percentageString = intent.getStringExtra("percentage");
        daysString = intent.getStringExtra("days");
        totalDaysString = intent.getStringExtra("totalDays");
        usnText = findViewById(R.id.usn);
        classText = findViewById(R.id.className);
        subjectText = findViewById(R.id.subject);
        totalDaysText = findViewById(R.id.totalClassDays);
        presentDaysText = findViewById(R.id.presentClassDays);
        percentageText = findViewById(R.id.percentageCurrent);
        addDays = findViewById(R.id.addDays);
        confirm = findViewById(R.id.confirm);
        usnText.setText(usn);
        classText.setText(className);
        subjectText.setText(subject);
        totalDaysText.setText(totalDaysString);
        presentDaysText.setText(daysString);
        percentageText.setText(percentageString);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDaysString = addDays.getText().toString();
                if (!addDaysString.equals("")) {
                    addDaysValue = Double.valueOf(addDaysString);
                    days = Double.valueOf(daysString);
                    totalDays = Double.valueOf(totalDaysString);
                    days = days + addDaysValue;
                    percentage = (days / totalDays) * 100;
                    percentageString = String.valueOf(percentage);
                    daysString = String.valueOf(days);
                    mFirebaseDatabase1 = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child("Attendance")
                            .child(branch).child(semester)
                            .child(className).child(usn)
                            .child(subject);
                    mFirebaseDatabase1.child("Days").setValue(days);
                    mFirebaseDatabase1.child("Percentage").setValue(percentageString);
                    presentDaysText1 = findViewById(R.id.presentClassDays);
                    percentageText1 = findViewById(R.id.percentageCurrent);
                    percentageText1.setText(percentageString);
                    presentDaysText1.setText(daysString);
                }
            }
        });
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Users").child("Attendance")
                .child(branch).child(semester)
                .child(className).child(usn)
                .child(subject);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String date = dataSnapshot1.getKey();
                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        String time = dataSnapshot2.getKey();
                        String value = dataSnapshot2.getValue().toString();
                        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), time, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

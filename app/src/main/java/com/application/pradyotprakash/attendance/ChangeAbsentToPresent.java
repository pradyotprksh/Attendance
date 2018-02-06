package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeAbsentToPresent extends AppCompatActivity {

    Button change;
    String date, time, branch, semester, className, subject, usn, percentageString, daysString, totalDaysString, value;
    private DatabaseReference mFirebaseDatabase, mFirebaseDatabase1;
    double days, totalDays, percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_absent_to_present);
        change = findViewById(R.id.makeItPresent);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        branch = intent.getStringExtra("branch");
        semester = intent.getStringExtra("semester");
        className = intent.getStringExtra("className");
        subject = intent.getStringExtra("subject");
        usn = intent.getStringExtra("usn");
        percentageString = intent.getStringExtra("percentage");
        daysString = intent.getStringExtra("days");
        totalDaysString = intent.getStringExtra("totalDays");
        value = intent.getStringExtra("value");
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Users").child("Attendance")
                .child(branch).child(semester)
                .child(className).child(usn)
                .child(subject);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value.equals("Absent")) {
                    mFirebaseDatabase = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child("Attendance")
                            .child(branch).child(semester)
                            .child(className).child(usn)
                            .child(subject);
                    mFirebaseDatabase.child(date).child(time).setValue("Present");
                    days = Double.valueOf(daysString);
                    days = days + 1;
                    totalDays = Double.valueOf(totalDaysString);
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
                }
            }
        });
    }

}

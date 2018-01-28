package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AttendenceCalculation extends AppCompatActivity {

    Button present, absent, stats;
    String branch, semester, className, subject, usn, percentageString, daysString, totalDaysString;
    private DatabaseReference mFirebaseDatabase, mFirebaseDatabase1, mFirebaseDatabase2;
    double days, total, currentDays, currentTotal;
    double percentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_calculation);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .3));
        present = findViewById(R.id.presentStudent);
        absent = findViewById(R.id.absentStudent);
        stats = findViewById(R.id.statsButton);
        Intent intent = getIntent();
        branch = intent.getStringExtra("branch");
        semester = intent.getStringExtra("semester");
        className = intent.getStringExtra("className");
        subject = intent.getStringExtra("subject");
        usn = intent.getStringExtra("usn");
        mFirebaseDatabase = FirebaseDatabase.getInstance()
                .getReference().child("Users").child("Attendance").child(branch).child(semester)
                .child(className).child(usn)
                .child(subject);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    currentDays = Double.parseDouble(dataSnapshot.child("Days").getValue().toString());
                } catch (Exception e) {
                    currentDays = 0;
                }
                try {
                    currentTotal = Double.parseDouble(dataSnapshot.child("TotalDays").getValue().toString());
                } catch (Exception e) {
                    currentTotal = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = currentDays + 1;
                total = currentTotal + 1;
                percentage = (days / total) * 100;
                percentageString = String.valueOf(percentage);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String currentDateTimeString = sdf.format(d);
                mFirebaseDatabase1 = FirebaseDatabase.getInstance().getReference().child("Users").child("Attendance");
                mFirebaseDatabase1
                        .child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child("Days").setValue(days);
                mFirebaseDatabase1.child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child("TotalDays").setValue(total);
                mFirebaseDatabase1.child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child("Percentage").setValue(percentageString);
                mFirebaseDatabase1.child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child(date).child(currentDateTimeString).setValue("Present");
            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = currentDays;
                total = currentTotal + 1;
                percentage = (days / total) * 100;
                percentageString = String.valueOf(percentage);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String currentDateTimeString = sdf.format(d);
                mFirebaseDatabase1 = FirebaseDatabase.getInstance().getReference().child("Users").child("Attendance");
                mFirebaseDatabase1
                        .child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child("Days").setValue(days);
                mFirebaseDatabase1.child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child("TotalDays").setValue(total);
                mFirebaseDatabase1.child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child("Percentage").setValue(percentageString);
                mFirebaseDatabase1.child(branch).child(semester)
                        .child(className).child(usn)
                        .child(subject).child(date).child(currentDateTimeString).setValue("Absent");
            }
        });
        mFirebaseDatabase2 = FirebaseDatabase.getInstance()
                .getReference().child("Users").child("Attendance").child(branch).child(semester)
                .child(className).child(usn)
                .child(subject);
        mFirebaseDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    percentageString = (dataSnapshot.child("Percentage").getValue().toString());
                } catch (Exception e) {
                    percentageString = "";
                }
                try {
                    daysString = (dataSnapshot.child("Days").getValue().toString());
                } catch (Exception e) {
                    daysString = "";
                }
                try {
                    totalDaysString = (dataSnapshot.child("TotalDays").getValue().toString());
                } catch (Exception e) {
                    totalDaysString = "";
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttendenceCalculation.this, ShowChangeStats.class);
                intent.putExtra("branch", branch);
                intent.putExtra("semester", semester);
                intent.putExtra("className", className);
                intent.putExtra("usn", usn);
                intent.putExtra("subject", subject);
                intent.putExtra("percentage", percentageString);
                intent.putExtra("days", daysString);
                intent.putExtra("totalDays", totalDaysString);
                startActivity(intent);
                finish();
            }
        });
    }
}

package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TypeSelection extends AppCompatActivity {

    private RadioButton admin, teacher, student;
    private RadioGroup group;
    private Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .5), (int) (height * .3));
        admin = findViewById(R.id.adminLogin);
        teacher = findViewById(R.id.teacherLogin);
        student = findViewById(R.id.childrenLogin);
        proceed = findViewById(R.id.proceedButton);
        group = findViewById(R.id.typeLogin);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (admin.isChecked()){
                    proceed.setVisibility(View.VISIBLE);
                }
                else if (teacher.isChecked()){
                    proceed.setVisibility(View.VISIBLE);
                }
                else if (student.isChecked()){
                    proceed.setVisibility(View.VISIBLE);
                }
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (admin.isChecked()){
                    Toast.makeText(getApplicationContext(), "Hello, Admin.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TypeSelection.this,AdminActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (teacher.isChecked()){
                    Intent intent = new Intent(TypeSelection.this,TeacherActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (student.isChecked()){
                    Toast.makeText(getApplicationContext(), "Hey, Student", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TypeSelection.this,StudentActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}

package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class StudentActivity extends AppCompatActivity {

    private TextView usnText;
    private Button giveAttendace;
    private Spinner branchesSpinner, semesterSpinner;
    EditText className;
    String branchSelected, semesterSelected, classSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        usnText = findViewById(R.id.studentUSN);
        giveAttendace = findViewById(R.id.checkAttendance);
        branchesSpinner = findViewById(R.id.branchOption);
        semesterSpinner = findViewById(R.id.semesterOptions);
        className = findViewById(R.id.classNames);
        branchesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchSelected = String.valueOf(branchesSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semesterSelected = String.valueOf(semesterSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        giveAttendace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = usnText.getText().toString();//.toUpperCase();
                classSelected = className.getText().toString();
//                boolean b = Pattern.matches("[0-9]SG[0-9][0-9][A-Z][A-Z][0-9][0-9][0-9]",usn);
//                if (TextUtils.isEmpty(usn)) {
//                    Toast.makeText(getApplicationContext(), "Enter your USN.", Toast.LENGTH_SHORT).show();
//                }
//                else if (usn.length() < 10 || usn.length() > 10) {
//                    Toast.makeText(getApplicationContext(), "Enter your correct USN.", Toast.LENGTH_SHORT).show();
//                }
                //if (b){
                Intent intent = new Intent(StudentActivity.this, StudentInformation.class);
                intent.putExtra("usn", usn);
                intent.putExtra("branch", branchSelected);
                intent.putExtra("semester", semesterSelected);
                intent.putExtra("className", classSelected);
                startActivity(intent);
                finish();
                //}
                //else {
                //Toast.makeText(getApplicationContext(), "Invalid USN.", Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }
}

package com.application.pradyotprakash.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendingNotification extends AppCompatActivity {

    private TextView classValue,branchValue,semesterValue,subjectValue;
    String className,subject,branch,semester;
    private EditText message_value;
    private Button send_bttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_notification);
        className = getIntent().getStringExtra("className");
        subject = getIntent().getStringExtra("subject");
        branch = getIntent().getStringExtra("branch");
        semester = getIntent().getStringExtra("semester");
        classValue = findViewById(R.id.class_value);
        branchValue = findViewById(R.id.branch_value);
        semesterValue = findViewById(R.id.semester_value);
        subjectValue = findViewById(R.id.subject_value);
        classValue.setText(className);
        branchValue.setText(branch);
        semesterValue.setText(semester);
        subjectValue.setText(subject);
        message_value = findViewById(R.id.message_value);
        send_bttn = findViewById(R.id.send_bttn);
        send_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = message_value.getText().toString();
                if (!TextUtils.isEmpty(value)){

                }
            }
        });
    }
}

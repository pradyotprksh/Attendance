package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminControl extends AppCompatActivity {

    private Button addTeacherAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control);
        addTeacherAccount = findViewById(R.id.addTeacher);
        addTeacherAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminControl.this,CreateTeacherAccount.class);
                startActivity(intent);
            }
        });
    }
}

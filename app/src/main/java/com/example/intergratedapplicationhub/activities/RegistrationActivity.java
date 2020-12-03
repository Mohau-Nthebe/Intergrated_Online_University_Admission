package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intergratedapplicationhub.R;

public class RegistrationActivity extends AppCompatActivity {

    Button btnInstitutionRegister,btnStudentRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        btnStudentRegistration= findViewById(R.id.btnStudentRegistration);



        btnStudentRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, StudentRegistration.class));
                RegistrationActivity.this.finish();
            }
        });
    }
}

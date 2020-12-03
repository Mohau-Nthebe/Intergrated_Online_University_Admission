package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;



public class StudentProfileDetails extends AppCompatActivity {
    TextView tvPersonalDetails,tvResidentialAddress,tvLoginInformation;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_details);

        imageView =findViewById(R.id.imageView);

        tvPersonalDetails = findViewById(R.id.tvPersonalDetails);
        tvResidentialAddress = findViewById(R.id.tvResidentialAddress);
        tvLoginInformation = findViewById(R.id.tvLoginInformation);

        tvPersonalDetails.setText("ID NUMBER              : " + BackendlessApplication.user.getProperty("IdNumber")
                                 +"\nNAME                      : " + BackendlessApplication.user.getProperty("Name")
                                + "\nSURNAME                : " + BackendlessApplication.user.getProperty("Surname") +
                                  "\nGENDER                   : " + BackendlessApplication.user.getProperty("Gender") +
                                 "\nHOME LANGUAGE  : " + BackendlessApplication.user.getProperty("HomeLanguage") +
                                 "\nTITLE                       : " + BackendlessApplication.user.getProperty("Title"));

        tvResidentialAddress.setText( "HOUSE NUMBER  : " + BackendlessApplication.user.getProperty("HouseNumber")  +
                                      "\nSTREET NAME      : " + BackendlessApplication.user.getProperty("StreetName")
                                    + "\nTOWN                  : " + BackendlessApplication.user.getProperty("Town") +
                                      "\nCITY                    : " + BackendlessApplication.user.getProperty("City") +
                                      "\nZIP CODE             : " + BackendlessApplication.user.getProperty("ZipCode"));

        tvLoginInformation.setText("EMAIL ADDRESS : " +  BackendlessApplication.user.getEmail() );

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(StudentProfileDetails.this, UpdateStudent.class);
                i.putExtra("IdNumber",BackendlessApplication.user.getProperty("IdNumber").toString());
                i.putExtra("Email",  BackendlessApplication.user.getEmail());
                i.putExtra("Surname",BackendlessApplication.user.getProperty("Surname").toString());
                i.putExtra("City",BackendlessApplication.user.getProperty("City").toString());
                i.putExtra("Gender",BackendlessApplication.user.getProperty("Gender").toString());
                i.putExtra("HomeLanguage",BackendlessApplication.user.getProperty("HomeLanguage").toString());
                i.putExtra("Title",BackendlessApplication.user.getProperty("Title").toString());
                i.putExtra("HouseNumber",BackendlessApplication.user.getProperty("HouseNumber").toString());
                i.putExtra("StreetName",BackendlessApplication.user.getProperty("StreetName").toString());
                i.putExtra("Town",BackendlessApplication.user.getProperty("Town").toString());
                i.putExtra("ZipCode",BackendlessApplication.user.getProperty("ZipCode").toString());
                i.putExtra("Name",BackendlessApplication.user.getProperty("Name").toString());
                startActivity(i);
                StudentProfileDetails.this.finish();

            }
        });
    }
}

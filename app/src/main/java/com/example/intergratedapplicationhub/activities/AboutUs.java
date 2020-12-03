package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intergratedapplicationhub.R;

public class AboutUs extends AppCompatActivity {

    Button btnM,btnV,btnMiss;
    TextView tvMan,tvVision,tvMission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        btnM = findViewById(R.id.btnM);
        btnV = findViewById(R.id.btnV);
        btnMiss = findViewById(R.id.btnMiss);
        tvMan = findViewById(R.id.tvMan);
        tvVision = findViewById(R.id.tvVision);
        tvMission = findViewById(R.id.tvMission);


        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvMan.setText("The Department of Education is mandated to provide compulsory basic education to all learners in the province progressively," +
                        " including learners with special needs and early childhood education. " +
                        "The Department therefore; seeks to provide access to quality teaching, " +
                        "learning and assessment for all learners. ");

            }
        });

        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvVision.setText("We are committed to provide functional and modern institutions and schools that enable quality teaching and learning " +
                        "that protect and promote the right of every learner to quality, equitable and relevant education. ");

            }
        });

        btnMiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvMission.setText("Every learner feels valued and inspired in our innovative education system ");

            }
        });

    }
}
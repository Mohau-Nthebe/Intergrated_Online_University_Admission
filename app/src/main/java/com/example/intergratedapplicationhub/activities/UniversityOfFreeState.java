package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.intergratedapplicationhub.R;

public class UniversityOfFreeState extends AppCompatActivity
{
    TextView tvAdmin, tvEmail, tvSite;
    TextView tvCounseling, tvResidences, tvFinancial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_of_free_state);

        tvAdmin = findViewById(R.id.tvAdmin);
        tvEmail = findViewById(R.id.tvEmail);
        tvSite = findViewById(R.id.tvSite);
        tvCounseling = findViewById(R.id.tvCounselling);
        tvResidences = findViewById(R.id.tvResidences);
        tvFinancial = findViewById(R.id.tvFinancial);

        tvAdmin.setText("Reabee");
        tvEmail.setText("info@ufs.ac.za");
        tvSite.setText("www.ufs.ac.za");
        tvCounseling.setText("https://www.ufs.ac.za/supportservices/" +
                "departments/health-and-wellness-centre-divisions/hiv-and-aids-office-home/general/useful-links");
        tvResidences.setText("Tel: +27 51 401 3455");
        tvFinancial.setText("Tel: +27 51 401 9894/7731/7175/7125");

    }
}

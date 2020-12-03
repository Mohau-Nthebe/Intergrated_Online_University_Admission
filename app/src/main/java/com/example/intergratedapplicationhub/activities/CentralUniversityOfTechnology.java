package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.intergratedapplicationhub.R;

public class CentralUniversityOfTechnology extends AppCompatActivity
{

    TextView tvAdmin25, tvGmail, tvPage;
    TextView tvTherapy, tvReses, tvFinancialAid;

    TextView tvAdm, tvMail, tvHome;
    TextView tvStudCounselling, tvRes, tvFinance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_university_of_technology);

        tvAdmin25 = findViewById(R.id.tvAdmin25);
        tvGmail = findViewById(R.id.tvGmail);
        tvPage = findViewById(R.id.tvPage);
        tvTherapy = findViewById(R.id.tvTherapy);
        tvReses = findViewById(R.id.tvReses);
        tvFinancialAid = findViewById(R.id.tvFinancialAid);

        tvAdm = findViewById(R.id.tvAdm);
        tvMail = findViewById(R.id.tvMail);
        tvHome = findViewById(R.id.tvHome);
        tvStudCounselling = findViewById(R.id.tvStudCounselling);
        tvRes = findViewById(R.id.tvRes);
        tvFinance = findViewById(R.id.tvFinance);

        tvAdmin25.setText("Reabee");
        tvGmail.setText("www.cut.ac.za");
        tvPage.setText("Reabee");
        tvTherapy.setText(" Cronje Gert Tel: +27 (0)51 507 3156 email: gcronje@cut.ac.za");
        tvReses.setText("Reabee");
        tvFinancialAid.setText("Finance Info Tel: +27 51 507 3167 email: financeinfo@cut.ac.za");

        tvAdm.setText("Reabee");
        tvMail.setText("Reabee");
        tvHome.setText("Reabee");
        tvStudCounselling.setText("Reabee");
        tvRes.setText("Ngo Tebogo Tel: +27 51 507 3149 email: ssepeng@cut.ac.za");
        tvFinance.setText("Finance Info Tel: +27 51 507 3167 email: financeinfo@cut.ac.za");



    }
}

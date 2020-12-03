package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intergratedapplicationhub.R;

public class NewsActivity extends AppCompatActivity
{



    Button btnCut, btnUfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        btnCut = findViewById(R.id.btnCut);
        btnUfs = findViewById(R.id.btnUfs);

        btnCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(NewsActivity.this,CentralUniversityOfTechnology.class));
                NewsActivity.this.finish();

               //Intent intent = new Intent(NewsActivity.this,CentralUniversityOfTechnology.class);
               //startActivity(intent);


            }

        });

        btnUfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(NewsActivity.this,UniversityOfFreeState.class));
                NewsActivity.this.finish();
            }
        });
    }
}

package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;
import com.example.intergratedapplicationhub.entities.Application;

import java.util.List;

public class ApplicationListDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvPersonalDetails,tvResidentialAddress,tvResults,tvCourses,tvApplicationStatus;

    Button btnNewStatus;
    private int index;
    LinearLayout lvStatus ;
    TextView tvFirst,tvSecond;
    Spinner spinnerFirst,spinnerSecond;
    private String firstChoiceStatus,secondChoiceStatus;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list_details);

        Intent intent = getIntent();
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvPersonalDetails = findViewById(R.id.tvPersonalDetails);
        tvResidentialAddress = findViewById(R.id.tvResidentialAddress);
        tvResults = findViewById(R.id.tvResults);
        tvCourses = findViewById(R.id.tvCourses);
        tvApplicationStatus = findViewById(R.id.tvApplicationStatus);
        btnNewStatus = findViewById(R.id.btnNewStatus);
        lvStatus = findViewById(R.id.lvStatus);
        tvFirst = findViewById(R.id.tvFirst);
        tvSecond = findViewById(R.id.tvSecond);
        spinnerFirst = findViewById(R.id.spinnerFirst);
        spinnerSecond = findViewById(R.id.spinnerSecond);
        tvFirst.setText(intent.getStringExtra("FirstChoice"));
        tvSecond.setText(intent.getStringExtra("SecondChoice"));
        index = getIntent().getExtras().getInt("key");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.applicationStatuses,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirst.setAdapter(adapter);
        spinnerFirst.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.applicationStatuses,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSecond.setAdapter(adapter2);
        spinnerSecond.setOnItemSelectedListener(this);

        btnNewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackendlessApplication.applications.get(index).setFirstChoiceStatus(firstChoiceStatus);
                BackendlessApplication.applications.get(index).setSecondChoiceStatus(secondChoiceStatus);

                showProgress(true);
                tvLoad.setText("Busy Updating Status.....");
                Backendless.Persistence.save(BackendlessApplication.applications.get(index), new AsyncCallback<Application>() {
                    @Override
                    public void handleResponse(Application response) {
                        Toast.makeText(ApplicationListDetails.this, " Status Successfully Updated ", Toast.LENGTH_SHORT).show();
                        ApplicationListDetails.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(ApplicationListDetails.this, "ERROR : " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    }
                });

            }
        });

        tvPersonalDetails.setText("ID NUMBER              : " + intent.getStringExtra("IdNumber")+
                "\nNAME                      : " + intent.getStringExtra("Name")+
                "\nSURNAME                : " + intent.getStringExtra("Surname")+
                "\nGENDER                   : " + intent.getStringExtra("Gender")+
                "\nHOME LANGUAGE  : " + intent.getStringExtra("HomeLanguage")+
                "\nTITLE                       : " + intent.getStringExtra("Title")+
                "\nEMAIL ADDRESS : " +  intent.getStringExtra("Email"));

        tvResidentialAddress.setText( "HOUSE NUMBER  : " + intent.getStringExtra("HouseNumber")+
                "\nSTREET NAME      : " + intent.getStringExtra("StreetName")+
                "\nTOWN                  : " + intent.getStringExtra("Town")+
                "\nCITY                    : " + intent.getStringExtra("City")+
                "\nZIP CODE             : " + intent.getStringExtra("ZipCode"));
        tvResults.setText(intent.getStringExtra("Subject1")+" :" + intent.getStringExtra("Mark1") + "%" +
                "\n" + intent.getStringExtra("Subject2")+" :" + intent.getStringExtra("Mark2") + "%" +
                "\n" + intent.getStringExtra("Subject3")+" :" + intent.getStringExtra("Mark3") + "%" +
                "\n" + intent.getStringExtra("Subject4")+" :" + intent.getStringExtra("Mark4") + "%" +
                "\n" + intent.getStringExtra("Subject5")+" :" + intent.getStringExtra("Mark5") + "%" +
                "\n" + intent.getStringExtra("Subject6")+" :" + intent.getStringExtra("Mark6") + "%" +
                "\n" + intent.getStringExtra("Subject7")+" :" + intent.getStringExtra("Mark7") + "%");
        tvCourses.setText("First Choice      : "+ intent.getStringExtra("FirstChoice")
                + "\nSecond Choice     : "+ intent.getStringExtra("SecondChoice")
                +"\n"+ "\nCampus Applied At : " + intent.getStringExtra("Campus"));
        tvApplicationStatus.setText(intent.getStringExtra("FirstChoice") + "        : " + intent.getStringExtra("FirstChoiceStatus")  + "\n" +
                intent.getStringExtra("SecondChoice") + "         : " + intent.getStringExtra("SecondChoiceStatus") );


        if(BackendlessApplication.user.getProperty("Role").toString().equals("Student")){
            tvApplicationStatus.setVisibility(View.VISIBLE);
        }
        else{
            tvApplicationStatus.setVisibility(View.VISIBLE);
            btnNewStatus.setVisibility(View.VISIBLE);
            lvStatus.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String str1 = spinnerFirst.getSelectedItem().toString();
        String str2 = spinnerSecond.getSelectedItem().toString();
        firstChoiceStatus = str1;
        secondChoiceStatus = str2;


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}

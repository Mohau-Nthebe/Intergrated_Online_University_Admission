package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateStudent extends AppCompatActivity {
    EditText tvId;
    TextInputEditText text_input_surname,tvName,tvHouseNumber,tvStreetName,tvTown,tvCity,tvZipCode,tvEmail,tvGender,tvLanguage,tvTitle;
    Button btnUpdate;
    TextInputLayout text_input_lasttt,id,text_input_username,text_input_houseNumber,text_input_streetName;
    TextInputLayout text_input_town,text_input_city,text_input_zipCode;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        Intent intent = getIntent();

        text_input_zipCode = findViewById(R.id.text_input_zipCode);
        text_input_username = findViewById(R.id.text_input_username);
        text_input_houseNumber = findViewById(R.id.text_input_houseNumber);
        text_input_streetName = findViewById(R.id.text_input_streetName);
        text_input_town = findViewById(R.id.text_input_town);
        text_input_lasttt = findViewById(R.id.text_input_lastname);
        text_input_city = findViewById(R.id.text_input_city);
        id = findViewById(R.id.text_input_ID);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvId = findViewById(R.id.tvId);
        text_input_surname = findViewById(R.id.text_input_surname);
        tvName = findViewById(R.id.tvName);
        tvHouseNumber = findViewById(R.id.tvHouseNumber);
        tvStreetName = findViewById(R.id.tvStreetName);
        tvTown = findViewById(R.id.tvTown);
        tvCity = findViewById(R.id.tvCity);
        tvZipCode = findViewById(R.id.tvZipCode);
        tvEmail = findViewById(R.id.tvEmail);
        tvGender = findViewById(R.id.tvGender);
        tvLanguage = findViewById(R.id.tvLanguage);
        tvTitle = findViewById(R.id.tvTitle);
        btnUpdate = findViewById(R.id.btnUpdate);

        tvEmail.setEnabled(false);
        tvEmail.setFocusable(false);
        tvGender.setEnabled(false);
        tvGender.setFocusable(false);
        tvTitle.setEnabled(false);
        tvTitle.setFocusable(false);
        tvLanguage.setEnabled(false);
        tvLanguage.setFocusable(false);

        tvGender.setText(intent.getStringExtra("Gender"));
        tvLanguage.setText(intent.getStringExtra("HomeLanguage"));
        tvTitle.setText(intent.getStringExtra("Title"));
        tvId.setText(intent.getStringExtra("IdNumber"));
        text_input_surname.setText(intent.getStringExtra("Surname"));
        tvName.setText(intent.getStringExtra("Name"));
        tvHouseNumber.setText(intent.getStringExtra("HouseNumber"));
        tvStreetName.setText(intent.getStringExtra("StreetName"));
        tvTown.setText(intent.getStringExtra("Town"));
        tvCity.setText(intent.getStringExtra("City"));
        tvZipCode.setText(intent.getStringExtra("ZipCode"));
        tvEmail.setText(intent.getStringExtra("Email"));



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        String l = text_input_lasttt.getEditText().getText().toString().trim();
                        String IDNumber = id.getEditText().getText().toString().trim();
                        String names = text_input_username.getEditText().getText().toString().trim();
                        String streetName = text_input_streetName.getEditText().getText().toString().trim();
                        String town = text_input_town.getEditText().getText().toString().trim();
                        String city = text_input_city.getEditText().getText().toString().trim();
                        String zipCode = text_input_zipCode.getEditText().getText().toString().trim();
                        String houseNumber = text_input_houseNumber.getEditText().getText().toString().trim();



                BackendlessUser user = new BackendlessUser();
                        user.setProperty("objectId",BackendlessApplication.user.getObjectId());
                        user.setProperty("Name",names);
                        user.setProperty("Surname",l);
                        user.setProperty("IdNumber",IDNumber);
                        user.setProperty("HouseNumber",houseNumber);
                        user.setProperty("StreetName",streetName);
                        user.setProperty("Town",town);
                        user.setProperty("City",city);
                        user.setProperty("ZipCode",zipCode);
                        showProgress(true);
                tvLoad.setText("Busy Updating Your Details.....Please Wait....");
                Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        BackendlessApplication.user = response;
                        Toast.makeText(UpdateStudent.this, "Successfully Updated Your Details", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(UpdateStudent.this, StudentProfile.class);
                        i.putExtra("Surname",BackendlessApplication.user.getProperty("Surname").toString());
                        i.putExtra("Name",BackendlessApplication.user.getProperty("Name").toString());
                        startActivity(i);
                        UpdateStudent.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(UpdateStudent.this, "Error" + fault.getMessage(), Toast.LENGTH_LONG).show();
                        showProgress(false);

                    }
                });


            }
        });


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

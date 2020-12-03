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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;
import com.example.intergratedapplicationhub.entities.Marks;

public class InstitutionApplication extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnSubjectSubmit;
    EditText etSubject1,etSubject2,etSubject3,etSubject4,etSubject5,etSubject6,etSubject7;
    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6;
    String firstAdditionalLanguage,secondAdditionalLanguage,Maths,LO,subject6,subject7,subject5;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    String mark1,mark2,mark3,mark4,mark5,mark6,mark7;
    String apScore;
    //double apScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_application);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        spinner1 = findViewById(R.id.spinnerFirstLanguage);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.firstAdditionalLanguage,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = findViewById(R.id.spinnerSecondLanguage);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.secondAdditionalLanguage,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = findViewById(R.id.spinnerMaths);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.mathematics,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        spinner4 = findViewById(R.id.spinnerOther1);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.otherSubjects,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);

        spinner5 = findViewById(R.id.spinnerOther2);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.otherSubjects,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(this);

        spinner6 = findViewById(R.id.spinnerOther3);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.otherSubjects,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner6.setOnItemSelectedListener(this);


        btnSubjectSubmit = findViewById(R.id.btnSubjectSubmit);
        etSubject1 = findViewById(R.id.etSubject1);
        etSubject2 = findViewById(R.id.etSubject2);
        etSubject3 = findViewById(R.id.etSubject3);
        etSubject4 = findViewById(R.id.etSubject4);
        etSubject5 = findViewById(R.id.etSubject5);
        etSubject6 = findViewById(R.id.etSubject6);
        etSubject7 = findViewById(R.id.etSubject7);

        final Intent intent = getIntent();
        //String email = intent.getStringExtra("email");



        btnSubjectSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etSubject1.getText().toString().trim().isEmpty() || etSubject2.getText().toString().trim().isEmpty() ||etSubject3.getText().toString().trim().isEmpty() ||
                        etSubject4.getText().toString().trim().isEmpty() || etSubject5.getText().toString().trim().isEmpty() || etSubject6.getText().toString().trim().isEmpty()
                        || etSubject7.getText().toString().trim().isEmpty()){
                    Toast.makeText(InstitutionApplication.this, "Enter All The Seven Subject Marks", Toast.LENGTH_SHORT).show();
                }
                else{
                    showProgress(true);
                    tvLoad.setText("Taking You To The Second Part Of The Application.....Please Wait");
                    mark1 = etSubject1.getText().toString().trim();
                    mark2 = etSubject2.getText().toString().trim();
                    mark3 = etSubject3.getText().toString().trim();
                    mark4 = etSubject4.getText().toString().trim();
                    mark5 = etSubject5.getText().toString().trim();
                    mark6 = etSubject6.getText().toString().trim();
                    mark7 = etSubject7.getText().toString().trim();

                    int mark1Level = CheckLevel(Double.parseDouble(mark1));
                    int mark2Level = CheckLevel(Double.parseDouble(mark2));
                    int mark3Level = CheckLevel(Double.parseDouble(mark3));
                    int mark4Level = 1;
                    int mark5Level = CheckLevel(Double.parseDouble(mark5));
                    int mark6Level = CheckLevel(Double.parseDouble(mark6));
                    int mark7Level = CheckLevel(Double.parseDouble(mark7));
                    apScore = String.valueOf(mark1Level + mark2Level + mark3Level + mark4Level + mark5Level +  mark6Level + mark7Level);


                    Marks mark = new Marks();
                    mark.setMark1(etSubject1.getText().toString().trim());
                    mark.setMark2(etSubject2.getText().toString().trim());
                    mark.setMark3(etSubject3.getText().toString().trim());
                    mark.setMark4(etSubject4.getText().toString().trim());
                    mark.setMark5(etSubject6.getText().toString().trim());
                    mark.setMark6(etSubject6.getText().toString().trim());
                    mark.setMark7(etSubject7.getText().toString().trim());
                    mark.setSubject1(firstAdditionalLanguage);
                    mark.setSubject2(secondAdditionalLanguage);
                    mark.setSubject3(Maths);
                    mark.setSubject4(LO);
                    mark.setSubject5(subject5);
                    mark.setSubject6(subject6);
                    mark.setSubject7(subject7);
                    mark.setEmail(intent.getStringExtra("Email"));

                    showProgress(true);
                    tvLoad.setText("Busy Saving Your Marks...");
                    Backendless.Persistence.save(mark, new AsyncCallback<Marks>() {
                        @Override
                        public void handleResponse(Marks response) {
                            Toast.makeText(InstitutionApplication.this, "Marks Successfully Saved..", Toast.LENGTH_SHORT).show();
                            InstitutionApplication.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(InstitutionApplication.this, "ERROR : "+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);

                        }
                    });

                }

            }
        });
    }

    private int CheckLevel(double mark) {
        int level = 0;
        if(mark >=0 && mark<=29){
            level = 1;
        }
        else if(mark >=30 && mark<=39){
            level = 2;
        }
        else if(mark >=40 && mark<=49){
            level = 3;
        }
        else if(mark >=50 && mark<=59){
            level = 4;
        }
        else if(mark >=60 && mark<=69){
            level = 5;
        }
        else if(mark >=70 && mark<=79){
            level = 6;
        }
        else if(mark >=80 && mark<=100){
            level = 7;
        }
        return level;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String str1 = spinner1.getSelectedItem().toString();
        String str2 = spinner2.getSelectedItem().toString();
        String str3 = spinner3.getSelectedItem().toString();
        String str4 = spinner4.getSelectedItem().toString();
        String str5 = spinner5.getSelectedItem().toString();
        String str6 = spinner6.getSelectedItem().toString();

        firstAdditionalLanguage = str1;
        secondAdditionalLanguage = str2;
        Maths = str3;
        LO = "Life Orientation";
        subject5 = str4;
        subject6 = str5;
        subject7 = str6;

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

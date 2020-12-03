package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;
import com.example.intergratedapplicationhub.entities.Application;
import com.example.intergratedapplicationhub.entities.Marks;

import java.util.ArrayList;
import java.util.List;

public class ActualApplication extends AppCompatActivity  {

    Spinner spinner1;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter4;
    Button btnApply,btnReApply,btnFinish;
    LinearLayout submit,register;

    TextView tvFirstChoice,tvSecondChoice;
    Button btnCourses;
    String universityName,universityCampus,mark1,mark2,mark3,mark4,mark5,mark6,mark7;
    String subject1,subject2,subject3,subject4,subject5,subject6,subject7,apScore;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    TextView  mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    //holds checked items
    ArrayList<Integer> mUserItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_application);

        submit = findViewById(R.id.submit);
        register = findViewById(R.id.register);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);


        String whereClause = "Email = '" + BackendlessApplication.user.getEmail() + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        showProgress(true);
        tvLoad.setText("Retrieving Your Marks For Application");
        Backendless.Persistence.of(Marks.class).find(queryBuilder, new AsyncCallback<List<Marks>>() {
            @Override
            public void handleResponse(List<Marks> response) {
                BackendlessApplication.marks = response;

                mark1 = BackendlessApplication.marks.get(0).getMark1();
                mark2 = BackendlessApplication.marks.get(0).getMark2();
                mark3 = BackendlessApplication.marks.get(0).getMark3();
                mark4 = BackendlessApplication.marks.get(0).getMark4();
                mark5 = BackendlessApplication.marks.get(0).getMark5();
                mark6 =BackendlessApplication.marks.get(0).getMark6();
                mark7 = BackendlessApplication.marks.get(0).getMark7();
                subject1 = BackendlessApplication.marks.get(0).getSubject1();
                subject2 = BackendlessApplication.marks.get(0).getSubject2();
                subject3 = BackendlessApplication.marks.get(0).getSubject3();
                subject4 = BackendlessApplication.marks.get(0).getSubject4();
                subject5 = BackendlessApplication.marks.get(0).getSubject5();
                subject6 = BackendlessApplication.marks.get(0).getSubject6();
                subject7 = BackendlessApplication.marks.get(0).getSubject7();
                BackendlessApplication.user.getProperty("Name");
                showProgress(false);
                Toast.makeText(ActualApplication.this, "Marks Successfully Retrieved", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(ActualApplication.this, "ERROR : "+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

            }
        });

        btnApply= findViewById(R.id.btnApply);
        btnReApply= findViewById(R.id.btnReApply);
        btnFinish= findViewById(R.id.btnFinish);
        tvFirstChoice= findViewById(R.id.tvFirstChoice);
        tvSecondChoice = findViewById(R.id.tvSecondChoice);
        btnCourses = findViewById(R.id.btnCourses);



        spinner1 = findViewById(R.id.spinnerInstitution);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.institution,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner2 = findViewById(R.id.spinnerCampus);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.cutCampus,android.R.layout.simple_spinner_item);
        adapter4 = ArrayAdapter.createFromResource(this,R.array.ufsCampus,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==1){

                    spinner2.setAdapter(adapter2);
                }
                else{
                    spinner2.setAdapter(adapter4);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner1.getSelectedItem().equals("Central University Of Technology"))
                {
                    universityName = "Central University Of Technology";
                    if(i==1){
                        listItems = getResources().getStringArray(R.array.bloemfonteinCampusCourses);
                        checkedItems = new boolean[listItems.length];
                        universityCampus = "Bloemfontein Campus";

                    }
                    else{
                        listItems = getResources().getStringArray(R.array.welkomCampusCourses);
                        checkedItems = new boolean[listItems.length];
                        universityCampus = "Welkom Campus";
                    }

                }
                else {
                    universityName = "University Of Free State";
                    if(i==1){
                        listItems = getResources().getStringArray(R.array.ufsBloemCampusCourses);
                        checkedItems = new boolean[listItems.length];
                        universityCampus = "Bloemfontein Campus";

                    }
                    else if(i==2)
                    {
                        listItems = getResources().getStringArray(R.array.qwaqwaCampusCourses);
                        checkedItems = new boolean[listItems.length];
                        universityCampus = "Qwaqwa Campus";

                    }
                    else{
                        listItems = getResources().getStringArray(R.array.southCampusCourses);
                        checkedItems = new boolean[listItems.length];
                        universityCampus = "South Campus";

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ActualApplication.this);
                mBuilder.setTitle("Courses Available");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked)
                        {
                            if(!mUserItems.contains(position)){
                                mUserItems.add(position);
                            }

                        }

                        else if(mUserItems.contains(position)){
                            mUserItems.remove(position);
                        }

                    }
                });
                mBuilder.setCancelable(true);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        try{

                            String item = "";
                            for(int i = 0; i<mUserItems.size(); i++){
                                tvFirstChoice.setText(listItems[mUserItems.get(0)]);
                                tvSecondChoice.setText(listItems[mUserItems.get(1)]);
                                item = item + listItems[mUserItems.get(i)];
                                if(i != mUserItems.size() - 1){
                                    item = item + ",";

                                }
                            }



                        }
                        catch (Exception e)
                        {
                            if(mUserItems.size()<2)
                            Toast.makeText(ActualApplication.this, "error: choose two courses ", Toast.LENGTH_SHORT).show();
                            else
                                throw e;
                        }


                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNegativeButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //looping through checked items to clear them all
                        for(int i=0; i<checkedItems.length; i++){
                            checkedItems[i]=false;
                            mUserItems.clear();

                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        btnReApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit.setVisibility(View.GONE);
                register.setVisibility(View.VISIBLE);
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualApplication.this.finish();
            }
        });
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUserItems.size()<2) {
                    Toast.makeText(ActualApplication.this, "error: choose two courses ", Toast.LENGTH_SHORT).show();
                }
                else{

                    Application application = new Application();
                    application.setMark1(mark1);
                    application.setMark2(mark2);
                    application.setMark3(mark3);
                    application.setMark4(mark4);
                    application.setMark5(mark5);
                    application.setMark6(mark6);
                    application.setMark7(mark7);
                    application.setSubject1(subject1);
                    application.setSubject2(subject2);
                    application.setSubject3(subject3);
                    application.setSubject4(subject4);
                    application.setSubject5(subject5);
                    application.setSubject6(subject6);
                    application.setSubject7(subject7);
                    application.setUniversityName(universityName);
                    application.setUniversityCampus(universityCampus);
                    application.setApplicationStatus("In Process");
                    application.setFirstChoice(tvFirstChoice.getText().toString().trim());
                    application.setSecondChoice(tvSecondChoice.getText().toString().trim());
                    application.setUserEmail(BackendlessApplication.user.getEmail());
                    application.setHouseNumber(BackendlessApplication.user.getProperty("HouseNumber").toString());
                    application.setName(BackendlessApplication.user.getProperty("Name").toString());
                    application.setID(BackendlessApplication.user.getProperty("IdNumber").toString());
                    application.setLastName(BackendlessApplication.user.getProperty("Surname").toString());
                    application.setStreetName(BackendlessApplication.user.getProperty("StreetName").toString());
                    application.setTown(BackendlessApplication.user.getProperty("Town").toString());
                    application.setCity(BackendlessApplication.user.getProperty("City").toString());
                    application.setZipCode(BackendlessApplication.user.getProperty("ZipCode").toString());
                    application.setTitle(BackendlessApplication.user.getProperty("Title").toString());
                    application.setHomeLanguage(BackendlessApplication.user.getProperty("HomeLanguage").toString());
                    application.setGender(BackendlessApplication.user.getProperty("Gender").toString());
                    application.setFirstChoiceStatus("In Process");
                    application.setSecondChoiceStatus("In Process");
                    showProgress(true);
                    tvLoad.setText("Finalizing Your Application...");

                    Backendless.Persistence.save(application, new AsyncCallback<Application>() {
                        @Override
                        public void handleResponse(Application response) {
                            Toast.makeText(ActualApplication.this, "Application Successful", Toast.LENGTH_SHORT).show();
                            submit.setVisibility(View.VISIBLE);
                            register.setVisibility(View.GONE);
                            tvFirstChoice.setText("");
                            tvSecondChoice.setText("");
                            mUserItems.clear();
                            showProgress(false);

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(ActualApplication.this, "ERROR :"+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);

                        }
                    });



                }

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

package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;
import com.example.intergratedapplicationhub.entities.Application;
import com.example.intergratedapplicationhub.entities.ApplicationAdapter;

import java.util.List;

public class ApplicationList extends AppCompatActivity implements ApplicationAdapter.ItemClicked {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    private int count;


    RecyclerView rvList;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        rvList = findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);

        String whereClause = "userEmail = '" + BackendlessApplication.user.getEmail() + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        //queryBuilder.setGroupBy("universityName");

        showProgress(true);
        tvLoad.setText("Getting All Your Applications...");

        Backendless.Persistence.of(Application.class).find(queryBuilder, new AsyncCallback<List<Application>>() {
            @Override
            public void handleResponse(List<Application> response) {
                BackendlessApplication.applications = response;
                myAdapter = new ApplicationAdapter(ApplicationList.this,BackendlessApplication.applications);
                rvList.setAdapter(myAdapter);
                showProgress(false);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(ApplicationList.this, "ERROR : "+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.power:
                showProgress(true);
                tvLoad.setText("Logging you out...please wait...");

                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Toast.makeText(ApplicationList.this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ApplicationList.this, MainActivity.class));
                        ApplicationList.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        Toast.makeText(ApplicationList.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClicked(int index) {
        count  = index;
        Intent i = new Intent(ApplicationList.this, ApplicationListDetails.class);
        i.putExtra("Mark1",BackendlessApplication.applications.get(index).getMark1());
        i.putExtra("Mark2",BackendlessApplication.applications.get(index).getMark2());
        i.putExtra("Mark3",BackendlessApplication.applications.get(index).getMark3());
        i.putExtra("Mark4",BackendlessApplication.applications.get(index).getMark4());
        i.putExtra("Mark5",BackendlessApplication.applications.get(index).getMark5());
        i.putExtra("Mark6",BackendlessApplication.applications.get(index).getMark6());
        i.putExtra("Mark7",BackendlessApplication.applications.get(index).getMark7());
        i.putExtra("Subject1",BackendlessApplication.applications.get(index).getSubject1());
        i.putExtra("Subject2",BackendlessApplication.applications.get(index).getSubject2());
        i.putExtra("Subject3",BackendlessApplication.applications.get(index).getSubject3());
        i.putExtra("Subject4",BackendlessApplication.applications.get(index).getSubject4());
        i.putExtra("Subject5",BackendlessApplication.applications.get(index).getSubject5());
        i.putExtra("Subject6",BackendlessApplication.applications.get(index).getSubject6());
        i.putExtra("Subject7",BackendlessApplication.applications.get(index).getSubject7());
        i.putExtra("UniversityName",BackendlessApplication.applications.get(index).getUniversityName());
        i.putExtra("UniversityCampus",BackendlessApplication.applications.get(index).getUniversityCampus());
        i.putExtra("ApplicationStatus",BackendlessApplication.applications.get(index).getApplicationStatus());
        i.putExtra("IdNumber",BackendlessApplication.applications.get(index).getID());
        i.putExtra("Email",  BackendlessApplication.applications.get(index).getUserEmail());
        i.putExtra("Surname",BackendlessApplication.applications.get(index).getLastName());
        i.putExtra("City",BackendlessApplication.applications.get(index).getCity());
        i.putExtra("Gender",BackendlessApplication.applications.get(index).getGender());
        i.putExtra("HomeLanguage",BackendlessApplication.applications.get(index).getHomeLanguage());
        i.putExtra("Title",BackendlessApplication.applications.get(index).getTitle());
        i.putExtra("HouseNumber",BackendlessApplication.applications.get(index).getHouseNumber());
        i.putExtra("StreetName",BackendlessApplication.applications.get(index).getStreetName());
        i.putExtra("Town",BackendlessApplication.applications.get(index).getTown());
        i.putExtra("ZipCode",BackendlessApplication.applications.get(index).getZipCode());
        i.putExtra("Name",BackendlessApplication.applications.get(index).getName());
        i.putExtra("Courses",BackendlessApplication.applications.get(index).getCourses());
        i.putExtra("Status",BackendlessApplication.applications.get(index).getApplicationStatus());
        i.putExtra("Campus",BackendlessApplication.applications.get(index).getUniversityCampus());
        i.putExtra("FirstChoice",BackendlessApplication.applications.get(index).getFirstChoice());
        i.putExtra("SecondChoice",BackendlessApplication.applications.get(index).getSecondChoice());
        i.putExtra("SecondChoiceStatus",BackendlessApplication.applications.get(index).getSecondChoiceStatus());
        i.putExtra("FirstChoiceStatus",BackendlessApplication.applications.get(index).getFirstChoiceStatus());
        i.putExtra("key",index);
        startActivity(i);
        ApplicationList.this.finish();



    }
}

package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;


public class MainActivity extends AppCompatActivity {
    Button btnRegister;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    Button btnLoginSubmit;
    EditText etLogPassword, etLogEmail;
    TextView tvReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        btnLoginSubmit= findViewById(R.id.btnLoginSubmit);

        btnLoginSubmit= findViewById(R.id.btnLoginSubmit);
        etLogPassword= findViewById(R.id.etLogPassword);
        etLogEmail= findViewById(R.id.etLogEmail);
        tvReset= findViewById(R.id.tvReset);

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PasswordResetActivity.class));
            }
        });


        showProgress(true);
        tvLoad.setText("Authenticating user....Please Wait");
        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {

            @Override
            public void handleResponse(Boolean response) {
                if(response){

                    tvLoad.setText("User Authenticated...Logging In.....");
                    //we using this to retrieve saved information of the user
                    String userObjectId = UserIdStorageFactory.instance().getStorage().get();
                    Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            //ALL OF THE LOGGED IN USER INFO IS IN response object
                            BackendlessApplication.user = response;
                            if(response.getProperty("Role").equals("Student")){
                                startActivity(new Intent(MainActivity.this, StudentProfile.class));
                            }
                            else {
                                startActivity(new Intent(MainActivity.this,InstitutionProfile.class));
                            }

                            MainActivity.this.finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(MainActivity.this, "Error" + fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }
                    });
                }
                else{
                    showProgress(false);
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this, "Error" + fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etLogEmail.getText().toString().isEmpty() || etLogEmail.getText().toString().isEmpty() ){
                    Toast.makeText(MainActivity.this, "Please Enter All Fields!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String email = etLogEmail.getText().toString().trim();
                    String password = etLogPassword.getText().toString().trim();


                    showProgress(true);
                    tvLoad.setText("Logging You In....Please Wait");
                    Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            BackendlessApplication.user = response;
                            String email =  response.getEmail();
                            Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                            if (response.getProperty("Role").equals("Student"))
                            {

                                Intent i = new Intent(MainActivity.this, StudentProfile.class);
                                i.putExtra("Email", email);
                                i.putExtra("Surname",response.getProperty("Surname").toString());
                                i.putExtra("City",response.getProperty("City").toString());
                                i.putExtra("Gender",response.getProperty("Gender").toString());
                                i.putExtra("HomeLanguage",response.getProperty("HomeLanguage").toString());
                                i.putExtra("IdNumber",response.getProperty("IdNumber").toString());
                                //i.putExtra("password",response.getProperty("password").toString());
                                i.putExtra("Role",response.getProperty("Role").toString());
                                i.putExtra("Title",response.getProperty("Title").toString());
                                i.putExtra("HouseNumber",response.getProperty("HouseNumber").toString());
                                i.putExtra("StreetName",response.getProperty("StreetName").toString());
                                i.putExtra("Town",response.getProperty("Town").toString());
                                i.putExtra("ZipCode",response.getProperty("ZipCode").toString());
                                i.putExtra("Name",response.getProperty("Name").toString());
                                startActivity(i);


                            }
                            else
                            {
                                startActivity(new Intent(MainActivity.this, InstitutionProfile.class));
                            }

                            MainActivity.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(MainActivity.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);


                        }
                    }, true);
                }
            }
        });


        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
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

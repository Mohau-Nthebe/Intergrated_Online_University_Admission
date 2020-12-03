package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.entities.Institution;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private TextInputLayout textInputEmail,textInputusername,textInputPassword,textInputPassword2;


    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);


        textInputEmail=findViewById(R.id.text_input_email);
        textInputPassword=findViewById(R.id.text_input_password);
        textInputPassword2=findViewById(R.id.text_input_password2);
        textInputusername=findViewById(R.id.text_input_username);



    }
    public   boolean validatePassword()
    {
        String passwordInput =  textInputPassword.getEditText().getText().toString().trim();
        Pattern pattern;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        if(passwordInput.isEmpty())
        {
            textInputPassword.setError("Password Is Required");
            return false;
        }
        else if(!passwordInput.matches(PASSWORD_PATTERN)&& passwordInput.length()>0){
            textInputPassword.setError("password must have a character, \n lower case,uppercase,special characters and \n must be atleast 9 characters long");
            return false;
        }
        else{
            textInputPassword.setError(null);
            textInputPassword.setErrorEnabled(false);
            return true;
        }



    }
    private boolean validatePassword2(){
        String passwordInput =  textInputPassword.getEditText().getText().toString().trim();
        String passwordInput2 = textInputPassword2.getEditText().getText().toString().toString().trim();
        if(passwordInput2.isEmpty())
        {
            textInputPassword2.setError("Confirm Password Is Required");
            return false;
        }
        else if(!passwordInput2.equals(passwordInput) && passwordInput2.length()>0){
            textInputPassword2.setError("Confirm Password Is Not Equal To Your Password ");
            return false;
        }
        else{
            textInputPassword2.setError(null);
            textInputPassword2.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateUsername(){
        String usernameInput = textInputusername.getEditText().getText().toString().trim();
        if(usernameInput.isEmpty())
        {
            textInputusername.setError("Name Is Required");
            return false;
        }
        else{
            textInputusername.setError(null);
            textInputusername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().toString().trim();
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailInput.isEmpty())
        {
            textInputEmail.setError("Email Is Required");
            return false;
        }
        else if (!emailInput.matches(emailPattern)&& emailInput.length()>0)
        {
            textInputEmail.setError("Your email format is incorrect");
            return false;
        }
        else{
            textInputEmail.setError(null);
            textInputEmail.setErrorEnabled(false);
            return true;
        }
    }


    public void confirmInput(View v){
        if(!validateEmail() | !validatePassword() | !validateUsername() | !validatePassword2()){
           return;
        }
        String name = textInputusername.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        String email = textInputEmail.getEditText().getText().toString().trim();

        BackendlessUser user = new BackendlessUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setProperty("Name",name);
        user.setProperty("Role","Institution");


        showProgress(true);
        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(Register.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                Register.this.finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Register.this, "Error" + fault.getMessage(), Toast.LENGTH_SHORT).show();
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

}

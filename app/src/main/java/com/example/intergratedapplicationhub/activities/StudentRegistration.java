package com.example.intergratedapplicationhub.activities;

import androidx.annotation.Nullable;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;
import com.google.android.material.textfield.TextInputLayout;

public class StudentRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextInputLayout textInputEmail,textInputusername,textInputPassword,textInputPassword2;
    TextInputLayout textInputID,textInputLastName,textInputHouseNumber,textInputStreetName;
    TextInputLayout textInputTown,textInputCity,textInputZipCode,textInputIDCopy;
    TextInputLayout textInputGender,textInputTitle,textInputLanguage;

    TextView tvIDCopy,tvResultsCopy,tvCvCopy;
    Button btnUploadID,btnUploadResults,btnUploadCv;
    Intent myFileIntent;

    private View mProgressView;
    private View mLoginFormView;
    TextView tvLoad,tvtitle,tvLanguage,tvGender;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    String title,homelanguage,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        spinner1 = findViewById(R.id.spinnerTitle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.title,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = findViewById(R.id.spinnerLanguage);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.language,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvGender= findViewById(R.id.tvGender);
        tvtitle= findViewById(R.id.tvtitle);
        tvLanguage= findViewById(R.id.tvLanguage);
        tvIDCopy= findViewById(R.id.tvIDCopy);
        tvResultsCopy= findViewById(R.id.tvResultsCopy);
        tvCvCopy= findViewById(R.id.tvCvCopy);
        btnUploadResults= findViewById(R.id.btnUploadResults);
        btnUploadCv= findViewById(R.id.btnUploadCv);
        btnUploadID = findViewById(R.id.btnUploadID);
        textInputIDCopy= findViewById(R.id.text_input_idCopy);
        textInputID=findViewById(R.id.text_input_ID);
        textInputLastName=findViewById(R.id.text_input_lastname);
        textInputHouseNumber=findViewById(R.id.text_input_houseNumber);
        textInputStreetName=findViewById(R.id.text_input_streetName);
        textInputTown=findViewById(R.id.text_input_town);
        textInputCity=findViewById(R.id.text_input_city);
        textInputZipCode=findViewById(R.id.text_input_zipCode);
        textInputEmail=findViewById(R.id.text_input_email);
        textInputPassword=findViewById(R.id.text_input_password);
        textInputPassword2=findViewById(R.id.text_input_password2);
        textInputusername=findViewById(R.id.text_input_username);

        textInputGender = findViewById(R.id.text_input_gender);
        textInputTitle = findViewById(R.id.text_input_title);
        textInputLanguage = findViewById(R.id.text_input_language);

        btnUploadID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,10);
            }
        });

        btnUploadCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,20);
            }
        });

        btnUploadResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,30);
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str1 = spinner1.getSelectedItem().toString();
        String str2 = spinner2.getSelectedItem().toString();
        String str3 = spinner3.getSelectedItem().toString();
        title = str1;
        homelanguage = str2;
        gender = str3;


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){


            case 10:

                if(resultCode== RESULT_OK){
                    String path = data.getData().getPath();
                    //String docName = getPackageName();
                    tvIDCopy.setText(path);

                }

                break;

            case 30:

                if(resultCode== RESULT_OK){
                    String path = data.getData().getPath();
                    //String docName = getPackageName();
                    tvResultsCopy.setText(path);


                }

                break;

            case 20:

                if(resultCode== RESULT_OK){
                    String path = data.getData().getPath();
                    //String docName = getPackageName();
                    tvCvCopy.setText(path);


                }

                break;


        }
    }

    public   boolean validatePassword()
    {
        String passwordInput =  textInputPassword.getEditText().getText().toString().trim();
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
    private boolean validateCode(){
        String codeInput = textInputZipCode.getEditText().getText().toString().trim();
        if(codeInput.isEmpty())
        {
            textInputZipCode.setError("Zip Code Is Required");
            return false;
        }
        else{
            textInputZipCode.setError(null);
            textInputZipCode.setErrorEnabled(false);
            return true;
        }
    }
    

    private boolean validateStudentCity(){
        String studentCityInput = textInputCity.getEditText().getText().toString().trim();
        if(studentCityInput.isEmpty())
        {
            textInputCity.setError("City Is Required");
            return false;
        }
        else{
            textInputCity.setError(null);
            textInputCity.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentTown(){
        String studentTownInput = textInputTown.getEditText().getText().toString().trim();
        if(studentTownInput.isEmpty())
        {
            textInputTown.setError("Your Town Is Required");
            return false;
        }
        else{
            textInputTown.setError(null);
            textInputTown.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validateStreetName(){
        String streetNameInput = textInputStreetName.getEditText().getText().toString().trim();
        if(streetNameInput.isEmpty())
        {
            textInputStreetName.setError("Street Name Is Required");
            return false;
        }
        else{
            textInputStreetName.setError(null);
            textInputStreetName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateHouseNumber(){
        String houseNumberInput = textInputHouseNumber.getEditText().getText().toString().trim();
        if(houseNumberInput.isEmpty())
        {
            textInputHouseNumber.setError("House Number Is Required");
            return false;
        }
        else{
            textInputHouseNumber.setError(null);
            textInputHouseNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateLastName(){
        String lastNameInput = textInputusername.getEditText().getText().toString().trim();
        if(lastNameInput.isEmpty())
        {
            textInputLastName.setError("Name Is Required");
            return false;
        }
        else{
            textInputLastName.setError(null);
            textInputLastName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateID(){
        String idInput = textInputID.getEditText().getText().toString().trim();
        if(idInput.isEmpty())
        {
            textInputID.setError("ID Number Is Required");
            return false;
        }
        else if(idInput.length()>0 && idInput.length()<12)
        {
            textInputID.setError("ID Number has 13 numbers");
            return false;
        }
        else{
            textInputID.setError(null);
            textInputID.setErrorEnabled(false);
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
        if(!validateEmail() | !validatePassword() | !validateUsername() | !validatePassword2() | !validateID()
                | !validateLastName() | !validateHouseNumber() | !validateStreetName() | !validateStudentTown()
        | !validateStudentCity() | !validateCode()){
            return;
        }

        String name = textInputusername.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        final String email = textInputEmail.getEditText().getText().toString().trim();
        String ID = textInputID.getEditText().getText().toString().trim();
        String lastName = textInputLastName.getEditText().getText().toString().trim();
        String houseNumber = textInputHouseNumber.getEditText().getText().toString().trim();
        String streetName = textInputStreetName.getEditText().getText().toString().trim();
        String town = textInputTown.getEditText().getText().toString().trim();
        String city = textInputCity.getEditText().getText().toString().trim();
        String zipCode = textInputZipCode.getEditText().getText().toString().trim();


       BackendlessUser user = new BackendlessUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setProperty("Name",name);
        user.setProperty("IdNumber",ID);
        user.setProperty("HouseNumber",houseNumber);
        user.setProperty("Surname",lastName);
        user.setProperty("StreetName",streetName);
        user.setProperty("Town",town);
        user.setProperty("City",city);
        user.setProperty("ZipCode",zipCode);
        user.setProperty("Role","Student");
        user.setProperty("Title",title);
        user.setProperty("HomeLanguage",homelanguage);
        user.setProperty("Gender",gender);
        showProgress(true);
        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(StudentRegistration.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(StudentRegistration.this, InstitutionApplication.class);
                i.putExtra("Email", email);
                startActivity(i);
                StudentRegistration.this.finish();

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(StudentRegistration.this, "Error" + fault.getMessage(), Toast.LENGTH_SHORT).show();
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

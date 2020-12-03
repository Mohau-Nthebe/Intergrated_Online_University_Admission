package com.example.intergratedapplicationhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.example.intergratedapplicationhub.R;
import com.example.intergratedapplicationhub.application_activities.BackendlessApplication;

public class ContactUs extends AppCompatActivity {
    EditText etName,etSubject,etMessage;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        etSubject = findViewById(R.id.etSubject);
        etMessage = findViewById(R.id.etMessage);
        btnSubmit = findViewById(R.id.btnSubmit);

        final String recipient = "mohaunthebe97@gmail.com";
        final String subject = etSubject.getText().toString();
        final String body = etMessage.getText().toString();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMessage.getText().toString().trim().isEmpty() ||etSubject.getText().toString().trim().isEmpty() ){
                    Toast.makeText(ContactUs.this, "Enter your message", Toast.LENGTH_SHORT).show();
                }
                else{
                    AsyncCallback sendEmailCallback = new AsyncCallback<MessageStatus>() {

                        @Override
                        public void handleResponse(MessageStatus response) {
                            Toast.makeText(ContactUs.this, "Your Message Recieved", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(ContactUs.this, "ERROR :" +fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    };
                    Backendless.Messaging.sendHTMLEmail(subject, body, recipient, sendEmailCallback);

                }
            }
        });
    }
}

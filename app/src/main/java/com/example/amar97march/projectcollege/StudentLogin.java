package com.example.amar97march.projectcollege;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity {

    private static final String TAG="LoginActivity";
    private static final int REQUEST_SIGNUP=0;

    TextView studentEmail;
    TextView studentPass;
    Button btn_login_student;
    Button btn_signUp_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        studentEmail=(TextView) findViewById(R.id.studentEmail);
        studentPass=(TextView)findViewById(R.id.studentPass);
        btn_login_student=(Button) findViewById(R.id.btn_login_student);
        btn_signUp_student=(Button) findViewById(R.id.btn_signup_student);

        btn_login_student.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        btn_signUp_student.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }


        btn_login_student.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(StudentLogin.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = studentEmail.getText().toString();
        String password = studentPass.getText().toString();
        //Logic here

        new android.os.Handler().postDelayed(
                new Runnable(){
                    public void run(){
                        //on login successful
                        onLoginSuccess();
                        //on unsuccessful
                        progressDialog.dismiss();
                    }
                },3000);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==REQUEST_SIGNUP){
            if(resultCode==RESULT_OK){
                //Signup logic here
                this.finish();
            }
        }
    }

    public void onLoginSuccess(){
        btn_login_student.setEnabled(true);
        finish();
    }
    public void onLoginFailed(){
        Toast.makeText(getBaseContext(),"Login Failed",Toast.LENGTH_LONG).show();
        btn_login_student.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = studentEmail.getText().toString();
        String password = studentPass.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            studentEmail.setError("enter a valid email address");
            valid = false;
        } else {
            studentEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            studentPass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            studentPass.setError(null);
        }

        return valid;
    }


}

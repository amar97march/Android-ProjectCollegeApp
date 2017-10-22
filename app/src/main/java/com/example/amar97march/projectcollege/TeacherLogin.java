package com.example.amar97march.projectcollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TeacherLogin extends AppCompatActivity {

    private static final String TAG="LoginActivity";
    private static final int REQUEST_SIGNUP=0;

    TextView teacherEmail;
    TextView teacherPass;
    Button btn_login_teacher;
    Button btn_signUp_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        teacherEmail=(TextView) findViewById(R.id.teacherEmail);
        teacherPass=(TextView)findViewById(R.id.teacherPass);
        btn_login_teacher=(Button) findViewById(R.id.btn_login_teacher);
        btn_signUp_teacher=(Button) findViewById(R.id.btn_signup_teacher);

        btn_login_teacher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        btn_signUp_teacher.setOnClickListener(new View.OnClickListener() {

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


        btn_login_teacher.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(TeacherLogin.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = teacherEmail.getText().toString();
        String password = teacherPass.getText().toString();
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
        btn_login_teacher.setEnabled(true);
        finish();
    }
    public void onLoginFailed(){
        Toast.makeText(getBaseContext(),"Login Failed",Toast.LENGTH_LONG).show();
        btn_login_teacher.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = teacherEmail.getText().toString();
        String password = teacherPass.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            teacherEmail.setError("enter a valid email address");
            valid = false;
        } else {
            teacherEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            teacherPass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            teacherPass.setError(null);
        }

        return valid;
    }


}

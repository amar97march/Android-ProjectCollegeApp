package com.example.amar97march.projectcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginS=(Button) findViewById(R.id.btnS);
        Button loginT=(Button) findViewById(R.id.btnT);

        loginS.setOnClickListener( new View.OnClickListener(){
        @Override
                public void onClick(View v){
                    Intent intent= new Intent(getApplicationContext(),StudentLogin.class);
                    startActivity(intent);
                }
        });

        loginT.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent= new Intent(getApplicationContext(),TeacherLogin.class);
                startActivity(intent);
            }
        });


    }
}

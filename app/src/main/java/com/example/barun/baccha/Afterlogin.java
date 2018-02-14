package com.example.barun.baccha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Afterlogin extends AppCompatActivity {
    private Button searchforlost;
    private Button registerforlostt;
    String valfromlogin;
    private Button searchforf;
    private Button registerforf;
    private ImageButton signout;
    private TextView emailname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        registerforlostt=(Button)findViewById(R.id.registerforlost);
        searchforlost=(Button)findViewById(R.id.searchfolost);

        registerforf=(Button)findViewById(R.id.registerforfound);
        searchforf=(Button)findViewById(R.id.searchforfound);

        emailname=(TextView)findViewById(R.id.emmmmai);


        signout=(ImageButton)findViewById(R.id.imageButton);
        valfromlogin=getIntent().getExtras().getString("emailvalue");
        emailname.setText(valfromlogin);



        registerforlostt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rege = new Intent(Afterlogin.this,PostActivity.class);
                startActivity(rege);
            }
        });

        searchforlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent khogya = new Intent(Afterlogin.this,MainActivity.class);
                startActivity(khogya);
            }
        });

        registerforf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rege = new Intent(Afterlogin.this,Postforfound.class);
                startActivity(rege);
            }
        });

        searchforf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent khogya = new Intent(Afterlogin.this,MainActivityf.class);
                startActivity(khogya);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






       /* searchforfound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mila = new Intent(Afterlogin.this,.class);
                startActivity(mila);
            }
        });*/


    }
}

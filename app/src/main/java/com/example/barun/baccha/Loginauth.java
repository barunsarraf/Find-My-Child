package com.example.barun.baccha;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginauth extends AppCompatActivity{
    private ProgressDialog mprogress;
    private EditText emaili;
    private EditText passi;
    private Button loglog;
    private Button regreg;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginauth);
        emaili = (EditText) findViewById(R.id.emailid);
        passi = (EditText) findViewById(R.id.pwd);

        loglog = (Button) findViewById(R.id.button2);
        regreg = (Button) findViewById(R.id.button4);

        //getting problem here for email to login email
        /*
        String val;
        val=getIntent().getExtras().getString("value");
        emaili.setText(val);*/

        firebaseAuth=FirebaseAuth.getInstance();
        mprogress=new ProgressDialog(Loginauth.this);

        regreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Loginauth.this,Registerauth.class);
                startActivity(i);
            }
        });

        loglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String e=emaili.getText().toString().trim();
                String p=passi.getText().toString().trim();


                if(TextUtils.isEmpty(e) || TextUtils.isEmpty(p))
                {
                    Toast.makeText(Loginauth.this,"Fill all the details",Toast.LENGTH_LONG).show();
                    //check thsi return in future if error exits
                    return;
                }
                mprogress.setMessage("Logging in..."+ e);
                mprogress.show();

                firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(Loginauth.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            mprogress.dismiss();
                            finish();


                            Intent back=new Intent(Loginauth.this,Afterlogin.class);
                            back.putExtra("emailvalue",e);
                            startActivity(back);
                        }

                        else{
                            mprogress.dismiss();
                            Toast.makeText(Loginauth.this,"Failed To Login.Try again",Toast.LENGTH_LONG).show();}

                    }
                });



            }
        });

            }
}

package com.example.barun.baccha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registerauth extends AppCompatActivity {
    private Button regme;
    private EditText emailidreg;
    private EditText passreg,naam;
    private ProgressDialog mprogress;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerauth);
        mprogress=new ProgressDialog(Registerauth.this);
        emailidreg=(EditText)findViewById(R.id.emailll);
        naam=(EditText)findViewById(R.id.naami);
        passreg=(EditText)findViewById(R.id.paswww);
        regme=(Button)findViewById(R.id.button5);
        firebaseAuth=FirebaseAuth.getInstance();

        regme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailregi = emailidreg.getText().toString();
                String passregi = passreg.getText().toString().trim();
                final String name=naam.getText().toString().trim();
                if(TextUtils.isEmpty(emailregi) || TextUtils.isEmpty(passregi) || TextUtils.isEmpty(name))
                {

                    Toast.makeText(Registerauth.this,"Fill all the details",Toast.LENGTH_LONG).show();
                    //check thsi return in future if error exits
                    return;
                }
                mprogress.setMessage("Registering "+name);
                mprogress.show();

                firebaseAuth.createUserWithEmailAndPassword(emailregi,passregi).addOnCompleteListener(Registerauth.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            mprogress.dismiss();
                            Toast.makeText(Registerauth.this,name+" is registered.",Toast.LENGTH_LONG).show();
                            Intent back=new Intent(Registerauth.this,Loginauth.class);
                            back.putExtra("value",emailregi);
                            startActivity(back);
                        }

                        else{
                            mprogress.dismiss();
                            Toast.makeText(Registerauth.this,"Check your Internet Connection and Try again",Toast.LENGTH_LONG).show();}

                    }
                });



            }
        });
    }
}

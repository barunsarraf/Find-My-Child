package com.example.barun.baccha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {
    private Uri imageUri;
    private DatabaseReference mdatabaseref;
    private StorageReference mStorageRef;
    private ImageButton imageButton;
    private static final int GALLERY_REQUEST = 1;
    private ProgressDialog mprogress;
    private EditText name,address,childname,contactaddress,haircolor,childlostat,clothes,des;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mdatabaseref= FirebaseDatabase.getInstance().getReference().child("LOST");
        imageButton=(ImageButton)findViewById(R.id.imageButton2);
        name=(EditText) findViewById(R.id.name);
        address=(EditText) findViewById(R.id.address);
        childname=(EditText) findViewById(R.id.childname);
        contactaddress=(EditText) findViewById(R.id.phone);
        haircolor=(EditText) findViewById(R.id.haircolor);
        childlostat=(EditText) findViewById(R.id.location);
        clothes=(EditText) findViewById(R.id.clothes);
        des=(EditText) findViewById(R.id.description);
        submit=(Button)findViewById(R.id.button3);
        mprogress=new ProgressDialog(this);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryintent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,GALLERY_REQUEST);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPosting();
            }
        });

    }


    private void startPosting(){
        final String name_val=name.getText().toString().trim();
        final String address_val=address.getText().toString().trim();
        final String childname_val=childname.getText().toString().trim();
        final String contactnumber_val=contactaddress.getText().toString().trim();
        final String haircolor_val=haircolor.getText().toString().trim();
        final String childlostat_val=childlostat.getText().toString().trim();
        final String clothes_val=clothes.getText().toString().trim();
        final String des_val=des.getText().toString().trim();

        //for images to upload getting error progress keeps on loading...
        if (!TextUtils.isEmpty(name_val) && !TextUtils.isEmpty(address_val) && !(imageUri == null) &&
                !TextUtils.isEmpty(name_val) &&
                !TextUtils.isEmpty(childname_val) &&
                !TextUtils.isEmpty(contactnumber_val) &&
                !TextUtils.isEmpty(haircolor_val) &&
                !TextUtils.isEmpty(childlostat_val) &&
                !TextUtils.isEmpty(clothes_val)&&!TextUtils.isEmpty(des_val))
        {
            mprogress.setMessage("We are submitting details of "+childname_val);
            mprogress.show();
            StorageReference filepath = mStorageRef.child("LOST").child(imageUri.getLastPathSegment());
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    String down=downloadUrl.toString();
                    DatabaseReference newpost = mdatabaseref.push();
                    newpost.child("Parent_Name").setValue(name_val);
                    newpost.child("Address").setValue(address_val);
                    newpost.child("Child_Name").setValue(childname_val);
                    newpost.child("Contact Number").setValue(contactnumber_val);
                    newpost.child("Hair Colour").setValue(haircolor_val);
                    newpost.child("Child lost at").setValue(childlostat_val);
                    newpost.child("Clothes").setValue(clothes_val);
                    newpost.child("Description").setValue(des_val);
                    newpost.child("image").setValue(down);
                    mprogress.dismiss();
                    Toast.makeText(PostActivity.this, "Details Submitted",Toast.LENGTH_LONG).show();



                    Intent i = new Intent(PostActivity.this,Afterlogin.class);
                    startActivity(i);





                }
            });
        }

        else
        {
            Toast.makeText(PostActivity.this, "Please Fill All The Details",Toast.LENGTH_LONG).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            imageUri = data.getData();
            imageButton.setImageURI(imageUri);

        }

    }
}





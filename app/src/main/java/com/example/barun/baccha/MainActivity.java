package com.example.barun.baccha;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.barun.baccha.PostActivity;
import com.example.barun.baccha.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mlostlist;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Please Wait",Toast.LENGTH_LONG).show();
        mlostlist=(RecyclerView) findViewById(R.id.lost_list);
        mlostlist.setHasFixedSize(true);
        mlostlist.setLayoutManager(new LinearLayoutManager(this));
        mDatabase= FirebaseDatabase.getInstance().getReference().child("LOST");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Datacollect,Dataviewholder>
                firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Datacollect, Dataviewholder>(

                Datacollect.class,R.layout.lostlistrow,
                Dataviewholder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(final Dataviewholder viewHolder, final Datacollect model, final int position) {
                viewHolder.setChild_Name(model.getChild_Name());
                viewHolder.setParent_Name(model.getParent_Name());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "This feature will be implemented soon",Toast.LENGTH_LONG).show();

                    }
                });
            }
        };

        mlostlist.setAdapter(firebaseRecyclerAdapter);


    }
    public static class Dataviewholder extends RecyclerView.ViewHolder
    {
        View mview;
        public Dataviewholder(View itemView) {
            super(itemView);

            mview=itemView;
        }


        public void setChild_Name(String Child_Name){
            TextView chi=(TextView)mview.findViewById(R.id.childnamelost);
            chi.setText(Child_Name);
        }
        public void setParent_Name(String Parent_Name){
            TextView parent_Name=(TextView)mview.findViewById(R.id.parentlost);
            parent_Name.setText(Parent_Name);
        }
        public void setImage(Context ctx,String image) {
            ImageView postimage=(ImageView)mview.findViewById(R.id.imagelost);
            Picasso.with(ctx).load(image).into(postimage);
        }


/*  This for user authen emaild id will be here in recylerview[firebase user]
        public void (String parent_Name){
            TextView parentt_Name=(TextView)mview.findViewById(R.id.parentlost);
            parentt_Name.setText(parent_Name);
        }*/

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.action_add){
            startActivity(new Intent(MainActivity.this,PostActivity.class));

        }
        //include filterbutton option in future here content

        return super.onOptionsItemSelected(item);
    }*/


}

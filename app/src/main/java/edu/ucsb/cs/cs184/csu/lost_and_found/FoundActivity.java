package edu.ucsb.cs.cs184.csu.lost_and_found;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class FoundActivity extends AppCompatActivity {
    private static DatabaseReference my_db_ref;
    Button submitButton, submitImage;
    FoundItem item = new FoundItem();
    EditText itemName, time, description;
    String itemKey = "";
    Context cur = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        //Firebase Set Up
        my_db_ref = FirebaseDatabase.getInstance().getReference("item");

        //get the user name of the user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        item.username = currentUser.getUid();

        //textView set up
        itemName = findViewById(R.id.itemName);
        time = findViewById(R.id.time);
        description = findViewById(R.id.descriptionEditText);

        //button set up
        submitButton = findViewById(R.id.submitButton);
        submitImage = findViewById(R.id.submitImageButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemName.getText().toString().length()>0){
                    item.itemName = itemName.getText().toString();
                }else {
                    Toast.makeText(cur, "Empty item", Toast.LENGTH_SHORT).show();
                }
                if(time.getText().toString().length()>0){
                    item.time = time.getText().toString();
                }else{
                    item.time = "null";
                }
                if(description.getText().toString().length()>0){
                    item.description = description.getText().toString();
                }else{
                    item.description = "null";
                }

                //Put in the database
                itemKey = my_db_ref.push().getKey();
                item.itemKey = itemKey;
                my_db_ref.child(itemKey).setValue(item);



                if(itemName.getText().toString().length()>0) {
                    Intent home = new Intent(FoundActivity.this, HomeActivity.class);
                    startActivity(home);
                }

            }
        });





    }

    public void homeButtonClick(View view) {
        Intent intToHome = new Intent(FoundActivity.this, HomeActivity.class);
        startActivity(intToHome);
    }
}


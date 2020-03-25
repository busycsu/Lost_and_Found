package edu.ucsb.cs.cs184.csu.lost_and_found;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




    }

    public void foundButtonClick(View view){
        Intent intToFound = new Intent(HomeActivity.this, FoundActivity.class);
        startActivity(intToFound);
    }

    public void lostButtonClick(View view){
        Intent intToLost = new Intent(HomeActivity.this, LostActivity.class);
        startActivity(intToLost);
    }

    public void logOutButtonClick(View view){
        Intent intToLogIn = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intToLogIn);
    }

    /*
    //test database output
        my_db_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        Log.v("keys", userSnapshot.getKey());
                        if (userSnapshot.getKey().equals(itemKey)) {
                            Log.v("fbfind", "find it");
                            FoundItem temp = userSnapshot.getValue(FoundItem.class);
                            String tempName = temp.itemName;

                            Log.v("itemname", tempName);

                        } else {
                            Log.v("fbfind", "didnt find it " + itemKey);

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //test end
     */

}

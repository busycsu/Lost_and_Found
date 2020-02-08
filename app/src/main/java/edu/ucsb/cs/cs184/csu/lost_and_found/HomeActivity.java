package edu.ucsb.cs.cs184.csu.lost_and_found;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.content.Intent;

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
}

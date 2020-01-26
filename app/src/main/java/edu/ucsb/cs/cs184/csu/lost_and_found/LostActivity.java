package edu.ucsb.cs.cs184.csu.lost_and_found;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
    }
    public void homeButtonClick(View view) {
        Intent intToHome = new Intent(LostActivity.this, HomeActivity.class);
        startActivity(intToHome);
    }
}

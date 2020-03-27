package edu.ucsb.cs.cs184.csu.lost_and_found;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LostResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_result);

        if(LostActivity.foundItems.size() != 0) {
            ListView foundListView = findViewById(R.id.foundListView);
            ArrayList<String> itemNames = new ArrayList<String>();
            Log.i("size", String.valueOf(LostActivity.foundItems.size()));

            for (FoundItem foundItem : LostActivity.foundItems) {
                String info = foundItem.itemName + " found on " + foundItem.time + " with description " + foundItem.description;
                itemNames.add(info);
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemNames);

            foundListView.setAdapter(arrayAdapter);
        }else{
            String noResultText = "No Results Found.";
            Toast toast = Toast.makeText(LostResultActivity.this, noResultText, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0, 0);
            toast.show();
        }


        Button homeButton = findViewById(R.id.homeButton);
        Button backButton = findViewById(R.id.backButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(LostResultActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lostIntent = new Intent(LostResultActivity.this, LostActivity.class);
                startActivity(lostIntent);
            }
        });
    }
}

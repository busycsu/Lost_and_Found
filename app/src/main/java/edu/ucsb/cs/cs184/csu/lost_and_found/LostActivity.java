package edu.ucsb.cs.cs184.csu.lost_and_found;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LostActivity extends AppCompatActivity {
    private static DatabaseReference my_db_ref;
    public static List<FoundItem> foundItems = new ArrayList<FoundItem>();
    Calendar date;
    Button submitButton, submitImage;
    EditText itemName, time, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        //Firebase Set Up
        my_db_ref = FirebaseDatabase.getInstance().getReference("item");

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
                my_db_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        foundItems.clear();
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child: children){
                            FoundItem value = child.getValue(FoundItem.class);
                            if(itemName.getText().toString().equals(value.itemName)) {
                                try {
                                    String lostTime = time.getText().toString();
                                    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:dd:yyyy", Locale.US);
                                    Date lostDate = formatter.parse(lostTime);
                                    if(lostDate.compareTo(formatter.parse(value.time)) < 0){
                                        foundItems.add(value);
                                    }
                                } catch (ParseException e) {
                                }
                            }
                        }
                        Intent lostResultIntent = new Intent(LostActivity.this, LostResultActivity.class);
                        startActivity(lostResultIntent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void homeButtonClick(View view) {
        Intent intToHome = new Intent(LostActivity.this, HomeActivity.class);
        startActivity(intToHome);
    }
}

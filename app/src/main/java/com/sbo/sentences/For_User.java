package com.sbo.sentences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class For_User extends AppCompatActivity {
    private TextView option1, option2, option3, sentences;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user_sentences");
    private ArrayList<String> the_sentence = new ArrayList();
    private String[] spilt_sentence;
    private ArrayList<String> items_to_remove = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for__user);

        initialize();
        Pull_From_Database();
    }
    private void initialize(){
        option1 = findViewById(R.id.textOption1);
        option2 = findViewById(R.id.textOption2);
        option3 = findViewById(R.id.textOption3);
        sentences = findViewById(R.id.textDisplySentence);
    }
    private void Pull_From_Database(){

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                the_sentence.add(map.get("lecture_Sentences") + "");
                HowTheSentenceSequenceWork();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void HowTheSentenceSequenceWork(){
        //split sentence in array and sends the split sentence to a new array
        for (String split_item: the_sentence) {
            spilt_sentence = split_item.split(" ");
        }

        //Displays the options by setting the specific name or position but they will be randomly placed.
        Random rand = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();

        for (int i = 0; i < spilt_sentence.length; i++){
            int random_number = rand.nextInt(spilt_sentence.length);
            int random_number2 = rand2.nextInt(spilt_sentence.length);
            int random_number3 = rand3.nextInt(spilt_sentence.length);

            option1.setText(spilt_sentence[random_number] + "");
            option2.setText(spilt_sentence[random_number2] + "");
            option3.setText(spilt_sentence[random_number3] + "");
        }

        //Adds the split items into an array list
        for (String items: spilt_sentence) {
            items_to_remove.add(items);
        }

        //If the array list contains the items from the options then it will remove those items and display the remaining items
        if(items_to_remove.contains(option1.getText()) && items_to_remove.contains(option2.getText()) && items_to_remove.contains(option3.getText())){
            items_to_remove.remove(option1.getText());
            items_to_remove.remove(option2.getText());
            items_to_remove.remove(option3.getText());
            sentences.setText(items_to_remove + "");
        }
    }
}
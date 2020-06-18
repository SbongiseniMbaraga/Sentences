package com.sbo.sentences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user_sentences");
    TextView sentences;
    Button remove, next;
    EditText userInput;
    TextView display_text, display_text2, display_text3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sentences = findViewById(R.id.senteces_text_view);
        remove = findViewById(R.id.removeBtn);
        userInput = findViewById(R.id.user_entry_editText);
        next = findViewById(R.id.nextPage);

        remove_selection();
        nextpage();
    }

    private void remove_selection(){
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_to_database();
            }
        });
    }
    private void save_to_database(){
        UserSentences userSentences = new UserSentences();
        userSentences.setID_Sentences(myRef.push().getKey());
        userSentences.setLecture_Sentences(userInput.getText() + "");
        sentences.setText(userSentences.getLecture_Sentences());

        //add to database
        myRef.push().setValue(userSentences);
        Toast.makeText(this, "Added to database", Toast.LENGTH_SHORT).show();
    }
    private void nextpage(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), For_User.class);
                startActivity(intent);
            }
        });
    }
}
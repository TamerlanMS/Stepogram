package com.example.stepogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewMessageActivity extends AppCompatActivity {
    private EditText editTextMessage;
    private Button buttonSend;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        // Инициализация Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            // Здесь можно добавить код для сохранения сообщения в базе данных
            mDatabase.child("messages").push().setValue(message);
            Toast.makeText(this, "Сообщение отправлено", Toast.LENGTH_SHORT).show();
            finish(); // Закрыть активность после отправки
        } else {
            Toast.makeText(this, "Введите сообщение", Toast.LENGTH_SHORT).show();
        }
    }
}

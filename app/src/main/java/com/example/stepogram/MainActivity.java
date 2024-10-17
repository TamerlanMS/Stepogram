package com.example.stepogram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView textView;
    private Handler handler;
    private Runnable updateTask;
    private int counter = 0;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Проверка аутентификации пользователя
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Перейти к экрану входа, если пользователь не авторизован
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Инициализация TextView
        textView = findViewById(R.id.textView);

        // Инициализация Handler
        handler = new Handler(Looper.getMainLooper());

        // Создание задачи, которая будет обновлять TextView
        updateTask = new Runnable() {
            @Override
            public void run() {
                counter++;
                textView.setText("Обновление #" + counter);
                // Повторный запуск задачи через 3 секунды
                handler.postDelayed(this, 3000);
            }
        };

        handler.post(updateTask);

        // Отправка сообщения через Handler
        Message message = handler.obtainMessage(1, "Привет от Handler!");
        handler.sendMessageDelayed(message, 2000);

        // Инициализация и регистрация NetworkChangeReceiver
        networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, filter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Переход на экран создания сообщения
                startActivity(new Intent(MainActivity.this, NewMessageActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Отмена регистрации NetworkChangeReceiver
        unregisterReceiver(networkChangeReceiver);
        handler.removeCallbacks(updateTask);
    }


}

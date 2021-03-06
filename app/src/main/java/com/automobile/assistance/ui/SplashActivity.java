package com.automobile.assistance.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.automobile.assistance.R;
import com.automobile.assistance.app.App;
import com.automobile.assistance.data.Repository;
import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.ui.auth.AuthActivity;
import com.automobile.assistance.ui.client.ClientActivity;
import com.automobile.assistance.ui.mechanic.MechanicActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent().inject(this);
        setContentView(R.layout.activity_splash);

        Intent intent;

        if (repo.user().isLoggedIn()) {
            if (repo.user().getUser().getRole().equals("customer")) {
                intent = new Intent(SplashActivity.this, ClientActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, MechanicActivity.class);
            }
        } else {
            intent = new Intent(SplashActivity.this, AuthActivity.class);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}

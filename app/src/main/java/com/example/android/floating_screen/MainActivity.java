package com.example.android.floating_screen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onUserLeaveHint() {
        PictureInPictureParams pictureParams = new PictureInPictureParams.Builder()
                .setAspectRatio(new Rational(2,2))
                .setActions(createAction())
        .build();

        enterPictureInPictureMode(pictureParams);
        super.onUserLeaveHint();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<RemoteAction> createAction() {
        Intent intent = new Intent(this,Reciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                1,
                intent,
                PendingIntent.FLAG_ONE_SHOT
                );

        RemoteAction remoteAction = new RemoteAction(
                Icon.createWithResource(this,R.drawable.ic_baseline_message_24),
                "Null",
                "hello",
                pendingIntent
        );
        List<RemoteAction> list = new ArrayList<>();
        list.add(remoteAction);

        return list;

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {

        if(isInPictureInPictureMode)
            getSupportActionBar().hide();
        else
            getSupportActionBar();
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }
}
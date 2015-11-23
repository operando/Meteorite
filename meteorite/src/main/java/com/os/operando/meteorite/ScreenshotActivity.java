package com.os.operando.meteorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.os.operando.meteor.Meteor;

public class ScreenshotActivity extends AppCompatActivity {

    public static Intent createIntent(Context context, String screenshotKey) {
        Intent i = new Intent(context, ScreenshotActivity.class);
        i.putExtra(Meteor.Screenshot_key, screenshotKey);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_screenshot);
        ((ImageView) findViewById(R.id.image_screen_shot)).setImageBitmap(Meteor.getScreenshotBitmap(getIntent()));
    }
}

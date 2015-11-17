package com.os.operando.meteorite.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TextInputActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, TextInputActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
    }
}

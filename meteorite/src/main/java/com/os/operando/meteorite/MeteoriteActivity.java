package com.os.operando.meteorite;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ImageView;

import com.os.operando.meteor.Meteor;
import com.os.operando.meteoroid.Meteoroid;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

public class MeteoriteActivity extends AppCompatActivity implements Callback {

    public static ComponentName createComponentName(Context context) {
        return new ComponentName(context.getPackageName(), MeteoriteActivity.class.getName());
    }

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        mBitmap = Meteor.getScreenshotBitmap(getIntent());
        ((ImageView) findViewById(R.id.screen_shot)).setImageBitmap(mBitmap);
    }

    public void onPost(View v) {
        try {
            File directory = IOUtils.getCacheDirectory(this);
            String cacheDirectoryPath = directory.getAbsolutePath();
            File bitmapFile = IOUtils.newUniqueTempFile(cacheDirectoryPath, ".jpg");
            IOUtils.saveBitmap(mBitmap, bitmapFile);

            new Meteoroid.Builder()
                    .channels("#general")
                    .title("test")
                    .initialComment(((AppCompatEditText) findViewById(R.id.post_message)).getText().toString())
                    .uploadFile(bitmapFile)
                    .token(Meteorite.getToken(this))
                    .build()
                    .post(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}

package com.os.operando.meteorite;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    public void onPost(View v) {
        ProgressDialogFragment.show(getFragmentManager());
        String inputChannel = ((AppCompatEditText) findViewById(R.id.post_channel)).getText().toString();
        String channel = TextUtils.isEmpty(inputChannel) ? "#general" : '#' != inputChannel.charAt(0) ? "#" + inputChannel : inputChannel;
        try {
            File directory = IOUtils.getCacheDirectory(this);
            String cacheDirectoryPath = directory.getAbsolutePath();
            File bitmapFile = IOUtils.newUniqueTempFile(cacheDirectoryPath, ".jpg");
            IOUtils.saveBitmap(mBitmap, bitmapFile);

            new Meteoroid.Builder()
                    .channels(channel)
                    .title(((AppCompatEditText) findViewById(R.id.post_title)).getText().toString())
                    .initialComment(((AppCompatEditText) findViewById(R.id.post_message)).getText().toString())
                    .uploadFile(bitmapFile)
                    .token(Meteorite.getToken(this))
                    .build()
                    .post(this);
        } catch (IOException e) {
            e.printStackTrace();
            ProgressDialogFragment.dismiss(getFragmentManager());
        }
    }

    public void onScreenshot(View v) {
        startActivity(ScreenshotActivity.createIntent(this, getIntent().getStringExtra(Meteor.Screenshot_key)));
    }

    @Override
    public void onFailure(Request request, IOException e) {
        Toast.makeText(this, "Fail upload screenshot", Toast.LENGTH_SHORT).show();
        ProgressDialogFragment.dismiss(getFragmentManager());
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            finish();
        }
        ProgressDialogFragment.dismiss(getFragmentManager());
    }

}

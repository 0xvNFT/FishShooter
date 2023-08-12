package com.vvv.fishshooter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    ImageView fb_untouched, zl_untouched, tg_untouched, mg_untouched, fb_touched, zl_touched, tg_touched, mg_touched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_contact);

        fb_untouched = findViewById(R.id.fb_untouched);
        zl_untouched = findViewById(R.id.zl_untouched);
        tg_untouched = findViewById(R.id.tg_untouched);
        mg_untouched = findViewById(R.id.mg_untouched);
        fb_touched = findViewById(R.id.fb_touched);
        zl_touched = findViewById(R.id.zl_touched);
        tg_touched = findViewById(R.id.tg_touched);
        mg_touched = findViewById(R.id.mg_touched);

        fb_untouched.setOnClickListener(v -> {
            openLink("https://www.facebook.com");
            fb_untouched.setVisibility(View.INVISIBLE);
            fb_touched.setVisibility(View.VISIBLE);
        });

        zl_untouched.setOnClickListener(v -> {
            openLink("https://www.zalo.com");
            zl_untouched.setVisibility(View.INVISIBLE);
            zl_touched.setVisibility(View.VISIBLE);
        });

        tg_untouched.setOnClickListener(v -> {
            openLink("https://www.telegram.org");
            tg_untouched.setVisibility(View.INVISIBLE);
            tg_touched.setVisibility(View.VISIBLE);
        });

        mg_untouched.setOnClickListener(v -> {
            openLink("https://www.messenger.com");
            mg_untouched.setVisibility(View.INVISIBLE);
            mg_touched.setVisibility(View.VISIBLE);
        });
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}

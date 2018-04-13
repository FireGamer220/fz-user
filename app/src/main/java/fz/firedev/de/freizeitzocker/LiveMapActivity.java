package fz.firedev.de.freizeitzocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class LiveMapActivity extends AppCompatActivity {

    private WebView livemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_map);

        livemap = (WebView) findViewById(R.id.livemap);
        livemap.loadUrl("http://freizeit-zocker.com:8123/");
        livemap.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LiveMapActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

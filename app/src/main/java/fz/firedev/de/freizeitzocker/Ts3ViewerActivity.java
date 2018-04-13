package fz.firedev.de.freizeitzocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Ts3ViewerActivity extends AppCompatActivity {

    private WebView ts3view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ts3_viewer);

        ts3view = (WebView) findViewById(R.id.ts3view);
        ts3view.loadUrl("https://firebasestorage.googleapis.com/v0/b/fz-ti-f2f24.appspot.com/o/TS3View.html?alt=media&token=bf37c68a-3bd9-400d-b0cc-8562f6ce813e");
        ts3view.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Ts3ViewerActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

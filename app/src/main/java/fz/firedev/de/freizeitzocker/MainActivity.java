package fz.firedev.de.freizeitzocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardView CV_TK, CV_TL, CV_LM, CV_SS, CV_TS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CV_TL = (CardView) findViewById(R.id.teamliste);
        CV_TL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, teamActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CV_TK = (CardView) findViewById(R.id.ticket);
        CV_TK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ticketLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CV_LM = (CardView) findViewById(R.id.livemap);
        CV_LM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LiveMapActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CV_SS = (CardView) findViewById(R.id.serverstatus);
        CV_SS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ServerStatusActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CV_TS = (CardView) findViewById(R.id.ts3);
        CV_TS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Ts3ViewerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onStart() {
        super.onStart();
        AppUpdater updater = new AppUpdater(this).setUpdateFrom(UpdateFrom.GITHUB).setDisplay(Display.SNACKBAR).setGitHubUserAndRepo("FireGamer220", "fz-userapp").showEvery(10).showAppUpdated(true).setContentOnUpdateNotAvailable("Die App ist auf dem Neusten Stand :D");
        updater.start();
    }

}

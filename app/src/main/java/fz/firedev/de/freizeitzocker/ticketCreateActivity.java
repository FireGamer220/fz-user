package fz.firedev.de.freizeitzocker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ticketCreateActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Button BtnCreate, BtnCancle;
    private EditText EdText, EdThema;
    private RadioButton radioButtonPrio, radioButtonErledigt;
    private RadioGroup radioGroupPrio, radioGroupErledigt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_create);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        BtnCreate = (Button) findViewById(R.id.createTK);
        BtnCancle = (Button) findViewById(R.id.cancleTK);
        EdText = (EditText) findViewById(R.id.text);
        EdThema = (EditText) findViewById(R.id.theme);

        BtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ticketCreateActivity.this, ticketMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        BtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroupPrio = (RadioGroup) findViewById(R.id.radioPrio);
                radioGroupErledigt = (RadioGroup) findViewById(R.id.radioErledigt);

                int selectIdPrio = radioGroupPrio.getCheckedRadioButtonId();
                int selectIdErledigt = radioGroupErledigt.getCheckedRadioButtonId();

                radioButtonPrio = (RadioButton) findViewById(selectIdPrio);
                radioButtonErledigt = (RadioButton) findViewById(selectIdErledigt);

                String fzid = FirebaseDatabase.getInstance().getReference().child("/Tickets").push().getKey();
                String username = mAuth.getCurrentUser().getDisplayName();
                FirebaseDatabase.getInstance().getReference().child("/Tickets").child(fzid).child("username").setValue(username);
                FirebaseDatabase.getInstance().getReference().child("/Tickets").child(fzid).child("fzid").setValue(fzid);
                FirebaseDatabase.getInstance().getReference().child("/Tickets").child(fzid).child("prio").setValue(radioButtonPrio.getText());
                FirebaseDatabase.getInstance().getReference().child("/Tickets").child(fzid).child("erledigt").setValue(radioButtonErledigt.getText());
                FirebaseDatabase.getInstance().getReference().child("/Tickets").child(fzid).child("text").setValue(EdText.getText().toString().trim());
                FirebaseDatabase.getInstance().getReference().child("/Tickets").child(fzid).child("thema").setValue(EdThema.getText().toString().trim());
                Intent intent = new Intent(ticketCreateActivity.this, ticketMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ticketCreateActivity.this, ticketMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mUser.getUid() == null) {
            Intent intent = new Intent(ticketCreateActivity.this, ticketLoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}

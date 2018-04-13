package fz.firedev.de.freizeitzocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class teamActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String mUsername;

    DatabaseReference myRef ;
    RecyclerView recycle;
    private CardView cv_edit;
    private RadioButton RbErledigt, RbNErledigt;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getSupportActionBar().setTitle("Teamliste");

        recycle = (RecyclerView)findViewById(R.id.recycler);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        myRef = FirebaseDatabase.getInstance().getReference().child("/Team");

        FirebaseRecyclerAdapter<Team,teamActivity.TeamViewHolder> adapter =new FirebaseRecyclerAdapter<Team, teamActivity.TeamViewHolder>(
                Team.class,
                R.layout.activity_team_card,
                teamActivity.TeamViewHolder.class,
                myRef

        ) {
            @Override
            protected void populateViewHolder(final teamActivity.TeamViewHolder viewHolder, final Team model, final int position) {
                viewHolder.setName(model.getName());
                viewHolder.setRolle(model.getRolle());
            }
        };

        recycle.setAdapter(adapter);
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView string_name,string_rolle;
        public TeamViewHolder(View itemView){
            super(itemView);
            string_name=(TextView)itemView.findViewById(R.id.mitglied);
            string_rolle=(TextView)itemView.findViewById(R.id.rang);
        }

        public void setName(String name) {
            string_name.setText(name);
        }

        public void setRolle(String rolle) {
            string_rolle.setText(rolle);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(teamActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

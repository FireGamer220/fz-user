package fz.firedev.de.freizeitzocker;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.media.Image;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ticketMainActivity extends AppCompatActivity {
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

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        getSupportActionBar().setTitle(mAuth.getCurrentUser().getDisplayName());
        setContentView(R.layout.activity_ticket_main);

        cv_edit = (CardView) findViewById(R.id.editCard);
        update = (Button) findViewById(R.id.update);
        RbErledigt = (RadioButton) findViewById(R.id.selectErledigt);
        RbNErledigt = (RadioButton) findViewById(R.id.selectNErledigt);

        recycle = (RecyclerView)findViewById(R.id.recyclerView);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        myRef = FirebaseDatabase.getInstance().getReference().child("/Tickets");

        FirebaseRecyclerAdapter<Ticket,TicketViewHolder> adapter =new FirebaseRecyclerAdapter<Ticket, TicketViewHolder>(
                Ticket.class,
                R.layout.ticket_cards_layout,
                TicketViewHolder.class,
                myRef

        ) {
            @Override
            protected void populateViewHolder(final TicketViewHolder viewHolder, final Ticket model, final int position) {
                viewHolder.setThema(model.getThema());
                viewHolder.setText(model.getText());
                viewHolder.setErledigt(model.getErledigt());
                viewHolder.setFzid(model.getFzid());
                viewHolder.setUsername(model.getUsername());
                viewHolder.ImgEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cv_edit.setVisibility(View.VISIBLE);
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (RbErledigt.isSelected()) {

                                }
                            }
                        });
                    }
                });
                viewHolder.ImgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myRef.child(model.getFzid().toString()).removeValue();
                    }
                });
            }
        };

        recycle.setAdapter(adapter);
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView string_theme,string_text,string_fzid,string_supporter;
        ImageView ImgErledigt,ImgDelete,ImgEdit;
        public TicketViewHolder(View itemView){
            super(itemView);
            string_theme=(TextView)itemView.findViewById(R.id.card_thema);
            string_text=(TextView)itemView.findViewById(R.id.card_text);
            string_fzid=(TextView)itemView.findViewById(R.id.fzid);
            string_supporter=(TextView)itemView.findViewById(R.id.card_von);
            ImgErledigt=(ImageView)itemView.findViewById(R.id.erledigt);
            ImgDelete=(ImageView)itemView.findViewById(R.id.delete);
            ImgEdit=(ImageView)itemView.findViewById(R.id.edit);
        }

        public void setThema(String thema) {
            string_theme.setText(thema);
        }

        public void setText(String text) {
            string_text.setText(text);
        }

        public void setErledigt(String erledigt) {
            if (erledigt.equals("Erledigt")){
                ImgErledigt.setImageResource(R.drawable.ic_action_erledigt);
            }else{
                ImgErledigt.setImageResource(R.drawable.ic_action_noterledigt);
            }
        }

        public void setFzid(String fzid) {
            string_fzid.setText(fzid);
        }

        public void setUsername(String Username) {
            string_supporter.setText(Username);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mMenu = getMenuInflater();
        mMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            case R.id.create:
                create();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ticketMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void create() {
        Intent intent = new Intent(ticketMainActivity.this, ticketCreateActivity.class);
        startActivity(intent);
        finish();
    }

    public void logout() {
        mAuth.signOut();
        Intent intent = new Intent(ticketMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mUser.getUid() == null) {
            Intent intent = new Intent(ticketMainActivity.this, ticketLoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

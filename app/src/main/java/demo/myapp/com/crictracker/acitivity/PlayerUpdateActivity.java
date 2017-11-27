package demo.myapp.com.crictracker.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import demo.myapp.com.crictracker.R;
import demo.myapp.com.crictracker.model.Player;
import demo.myapp.com.crictracker.other.UpdatePlayerList;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerUpdateActivity extends AppCompatActivity {

    //view objects
    EditText editTextName;
    Spinner spinnerGenre;
    Button buttonSignIn;
    Button buttonAddPlayer;

    Dialog myDialog;
    ListView listViewPlayers;

    //a list to store all the player from firebase database
    List<Player> players;

    //our database reference object
    DatabaseReference databasePlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getting the reference of players node
        databasePlayers = FirebaseDatabase.getInstance().getReference("players");


        editTextName = (EditText) findViewById(R.id.new_input_playername);
        spinnerGenre = (Spinner) findViewById(R.id.spinnerGenres);
        listViewPlayers = (ListView) findViewById(R.id.listViewUpdatePlayers);
        players = new ArrayList<>();


        myDialog = new Dialog(this);

        listViewPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*TextView p_batMatches = (TextView) findViewById(R.id.p_batMatches);
                TextView p_batInnings = (TextView) findViewById(R.id.p_batInnings);
                TextView p_batRuns = (TextView) findViewById(R.id.p_batRuns);
                TextView p_Fours = (TextView) findViewById(R.id.p_fours);
                TextView p_Sixes = (TextView) findViewById(R.id.p_sixes);
                TextView p_0s = (TextView) findViewById(R.id.p_0s);
                TextView p_batHigest = (TextView) findViewById(R.id.p_batHigest);

                TextView p_bowMatches = (TextView) findViewById(R.id.p_bowMatches);
                TextView p_bowInnings = (TextView) findViewById(R.id.p_bowInnings);
                TextView p_bowOvers = (TextView) findViewById(R.id.p_bowOvers);
                TextView p_wkts = (TextView) findViewById(R.id.p_wkts);
                TextView p_maidens = (TextView) findViewById(R.id.p_maidens);
                TextView p_bowBest = (TextView) findViewById(R.id.p_bowBest);*/


                Player player = players.get(position);

                /*TextView p_name = (TextView) findViewById(R.id.playerName);
                TextView p_genre = (TextView) findViewById(R.id.playerGenre);
                ImageView imageView = (ImageView) findViewById(R.id.playerImage);

                TextView p_batMatches = (TextView) findViewById(R.id.p_batMatches);
                TextView p_batInnings = (TextView) findViewById(R.id.p_batInnings);
                TextView p_batRuns = (TextView) findViewById(R.id.p_batRuns);
                TextView p_Fours = (TextView) findViewById(R.id.p_fours);
                TextView p_Sixes = (TextView) findViewById(R.id.p_sixes);
                TextView p_0s = (TextView) findViewById(R.id.p_0s);
                TextView p_batHigest = (TextView) findViewById(R.id.p_batHigest);

                TextView p_bowMatches = (TextView) findViewById(R.id.p_bowMatches);
                TextView p_bowInnings = (TextView) findViewById(R.id.p_bowInnings);
                TextView p_bowOvers = (TextView) findViewById(R.id.p_bowOvers);
                TextView p_wkts = (TextView) findViewById(R.id.p_wkts);
                TextView p_maidens = (TextView) findViewById(R.id.p_maidens);
                TextView p_bowBest = (TextView) findViewById(R.id.p_bowBest);


                // Set results to the TextViews
                p_name.setText(player.getPlayerName());
                p_genre.setText(player.getPlayerGenre());

                /*p_batMatches.setText(playerbatMatches);
                p_batInnings.setText(playerbatInnings);
                p_batRuns.setText(playerbatRuns);
                p_Fours.setText(playerFours);
                p_Sixes.setText(playerSixes);
                p_0s.setText(player0s);

                p_batHigest.setText(playerbatHigest);
                p_bowInnings.setText(playerbowInnings);
                p_bowMatches.setText(playerbowMatches);
                p_bowOvers.setText(playerbowOvers);
                p_wkts.setText(playerwkts);
                p_maidens.setText(playermaidens);
                p_bowBest.setText(playerbowBest);*/

                /*Picasso.with(getApplicationContext())  //Here, this is context.
                        .load(player.getPlayerImage())  //Url of the image to load.
                        .into(imageView);*/

                showUpdateDeleteDialog(player.getPlayerId(), player.getPlayerName(), player.getPlayerGenre(), player.getPlayerImage(), player.getP_batMatches(),
                        player.getP_batInnings(), player.getP_batRuns(), player.getP_Fours(), player.getP_Sixes(), player.getP_0s(), player.getP_batHigest(),
                        player.getP_bowMatches(), player.getP_bowInnings(), player.getP_bowOvers(), player.getP_wkts(), player.getP_maidens(), player.getP_bowBest());

            }
        });

        /*listViewPlayers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player player = players.get(i);
                showUpdateDeleteDialog(player.getPlayerId(), player.getPlayerName(), player.getPlayerImage());
                return true;
            }
        });*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowPopup();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

    }

    public void ShowPopup() {
       /* TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.layout_player_add_popup);
       /* txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });*/
       /* myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();*/

        //getting views
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_player_add_popup, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.new_input_playername);
        final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinnerGenres);
        final Button buttonAdd = (Button) dialogView.findViewById(R.id.addPlayer);


        dialogBuilder.setTitle("New Player");
        final AlertDialog b = dialogBuilder.create();
        b.show();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenre.getSelectedItem().toString();

                if (!TextUtils.isEmpty(name)) {
                    addPlayer(name, genre);
                    b.dismiss();
                    Toast.makeText(getApplicationContext(), "Player added successfully", Toast.LENGTH_SHORT).show();
                    //updatePlayer(playerId, name, genre, playerImage);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter player name", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databasePlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous player list
                players.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting player
                    Player player = postSnapshot.getValue(Player.class);
                    //adding player to the list
                    players.add(player);
                }

                //creating adapter
                UpdatePlayerList playerAdapter = new UpdatePlayerList(PlayerUpdateActivity.this, players);
                //attaching adapter to the listview
                listViewPlayers.setAdapter(playerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void addPlayer(String playerName, String playerGenre) {
        //getting the values to save
        /* String name = editTextName.getText().toString().trim();
        String genre = spinnerGenre.getSelectedItem().toString();*/
        String playerImage = "http://res.cloudinary.com/dzm4ywsb2/image/upload/v1510752949/cricket.png";

        String p_batMatches = "0";
        String p_batInnings= "0";
        String p_batRuns= "0";
        String p_Fours= "0";
        String p_Sixes= "0";
        String p_0s= "0";
        String p_batHigest= "0";

        String p_bowMatches= "0";
        String p_bowInnings= "0";
        String p_bowOvers= "0";
        String p_wkts= "0";
        String p_maidens= "0";
        String p_bowBest= "0";

        //checking if the value is provided
        if (!TextUtils.isEmpty(playerName)) {
            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Player
            String playerId = databasePlayers.push().getKey();

            //creating an Player Object
            Player player = new Player(playerId, playerName, playerGenre, playerImage, p_batMatches,
                    p_batInnings, p_batRuns, p_Fours, p_Sixes, p_0s, p_batHigest,
                    p_bowMatches, p_bowInnings, p_bowOvers, p_wkts, p_maidens, p_bowBest);


            //Saving the Player
            databasePlayers.child(playerId).setValue(player);

//displaying a success toast
            Toast.makeText(this, "Player added successfully", Toast.LENGTH_SHORT).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    private boolean updatePlayer(String playerId, String playerName, String playerGenre, String playerImage, String p_batMatches,
                                 String p_batInnings, String p_batRuns, String p_Fours, String p_Sixes, String p_0s, String p_batHigest,
                                 String p_bowMatches, String p_bowInnings, String p_bowOvers, String p_wkts, String p_maidens, String p_bowBest) {
        //getting the specified player reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("players").child(playerId);

        //updating player


        Player player = new Player(playerId, playerName, playerGenre, playerImage, p_batMatches,
                p_batInnings, p_batRuns, p_Fours, p_Sixes, p_0s, p_batHigest,
                p_bowMatches, p_bowInnings, p_bowOvers, p_wkts, p_maidens, p_bowBest);
        dR.setValue(player);
        Toast.makeText(getApplicationContext(), "Player updated successfully", Toast.LENGTH_LONG).show();
        return true;
    }


    private void showUpdateDeleteDialog(final String playerId, final String playerName, final String playerGenre, final String playerImage, String p_batMatches,
                                        String p_batInnings, String p_batRuns, String p_Fours, String p_Sixes, String p_0s, String p_batHigest,
                                        String p_bowMatches, String p_bowInnings, String p_bowOvers, String p_wkts, String p_maidens, String p_bowBest) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_player_update_popup, null);

        dialogBuilder.setView(dialogView);

        TextView player_name = (TextView) dialogView.findViewById(R.id.playerName);

        TextView player_genre = (TextView) dialogView.findViewById(R.id.playerGenre);
        ImageView player_image = (ImageView) dialogView.findViewById(R.id.playerImage);

        EditText player_batMatches = (EditText) dialogView.findViewById(R.id.p_batMatches);
        EditText player_batInnings = (EditText) dialogView.findViewById(R.id.p_batInnings);
        EditText player_batRuns = (EditText) dialogView.findViewById(R.id.p_batRuns);
        EditText player_Fours = (EditText) dialogView.findViewById(R.id.p_fours);
        EditText player_Sixes = (EditText) dialogView.findViewById(R.id.p_sixes);
        EditText player_0s = (EditText) dialogView.findViewById(R.id.p_0s);
        EditText player_batHigest = (EditText) dialogView.findViewById(R.id.p_batHigest);

        EditText player_bowMatches = (EditText) dialogView.findViewById(R.id.p_bowMatches);
        EditText player_bowInnings = (EditText) dialogView.findViewById(R.id.p_bowInnings);
        EditText player_bowOvers = (EditText) dialogView.findViewById(R.id.p_bowOvers);
        EditText player_wkts = (EditText) dialogView.findViewById(R.id.p_wkts);
        EditText player_maidens = (EditText) dialogView.findViewById(R.id.p_maidens);
        EditText player_bowBest = (EditText) dialogView.findViewById(R.id.p_bowBest);


        // Set results to the TextViews
        player_name.setText(playerName);
        player_genre.setText(playerGenre);

        player_batMatches.setText(p_batMatches);
        player_batInnings.setText(p_batInnings);
        player_batRuns.setText(p_batRuns);
        player_Fours.setText(p_Fours);
        player_Sixes.setText(p_Sixes);
        player_0s.setText(p_0s);

        player_batHigest.setText(p_batHigest);
        player_bowInnings.setText(p_bowInnings);
        player_bowMatches.setText(p_bowMatches);
        player_bowOvers.setText(p_bowOvers);
        player_wkts.setText(p_wkts);
        player_maidens.setText(p_maidens);
        player_bowBest.setText(p_bowBest);

        Picasso.with(this)  //Here, this is context.
                .load(playerImage)  //Url of the image to load.
                .into(player_image);

        //final TextView editTextName = (TextView) dialogView.findViewById(R.id.playerName);
        //final TextView spinnerGenre = (TextView) dialogView.findViewById(R.id.playerGenre);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.updatePlayer);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deletePlayer);

        final EditText play_batMatches = (EditText) dialogView.findViewById(R.id.p_batMatches);
        final EditText play_batInnings = (EditText) dialogView.findViewById(R.id.p_batInnings);
        final EditText play_batRuns = (EditText) dialogView.findViewById(R.id.p_batRuns);
        final EditText play_Fours = (EditText) dialogView.findViewById(R.id.p_fours);
        final EditText play_Sixes = (EditText) dialogView.findViewById(R.id.p_sixes);
        final EditText play_0s = (EditText) dialogView.findViewById(R.id.p_0s);
        final EditText play_batHigest = (EditText) dialogView.findViewById(R.id.p_batHigest);

        final EditText play_bowMatches = (EditText) dialogView.findViewById(R.id.p_bowMatches);
        final EditText play_bowInnings = (EditText) dialogView.findViewById(R.id.p_bowInnings);
        final EditText play_bowOvers = (EditText) dialogView.findViewById(R.id.p_bowOvers);
        final EditText play_wkts = (EditText) dialogView.findViewById(R.id.p_wkts);
        final EditText play_maidens = (EditText) dialogView.findViewById(R.id.p_maidens);
        final EditText play_bowBest = (EditText) dialogView.findViewById(R.id.p_bowBest);

        final TextView play_popClose = (TextView) dialogView.findViewById(R.id.popClose);

        //dialogBuilder.setTitle(playerName);


        final AlertDialog b = dialogBuilder.create();
        b.show();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String playerName = editTextName.getText().toString().trim();
                //String playerGenre = spinnerGenre.getText().toString();
                String p_batMatches = play_batMatches.getText().toString().trim();
                String p_batInnings= play_batInnings.getText().toString().trim();
                String p_batRuns= play_batRuns.getText().toString().trim();
                String p_Fours= play_Fours.getText().toString().trim();
                String p_Sixes= play_Sixes.getText().toString().trim();
                String p_0s= play_0s.getText().toString().trim();
                String p_batHigest= play_batHigest.getText().toString().trim();

                String p_bowMatches= play_bowMatches.getText().toString().trim();
                String p_bowInnings= play_bowInnings.getText().toString().trim();
                String p_bowOvers= play_bowOvers.getText().toString().trim();
                String p_wkts= play_wkts.getText().toString().trim();
                String p_maidens= play_maidens.getText().toString().trim();
                String p_bowBest= play_bowBest.getText().toString().trim();

                if (!TextUtils.isEmpty(playerName)) {
                    updatePlayer(playerId, playerName, playerGenre, playerImage, p_batMatches,
                            p_batInnings, p_batRuns, p_Fours, p_Sixes, p_0s, p_batHigest,
                            p_bowMatches, p_bowInnings, p_bowOvers, p_wkts, p_maidens, p_bowBest);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletePlayer(playerId);
                b.dismiss();
            }
        });
        play_popClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

    }

    private boolean deletePlayer(String id) {
        //getting the specified player reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("players").child(id);

        //removing player
        dR.removeValue();

       /* //getting the tracks reference for the specified player
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        //removing all tracks
        drTracks.removeValue();*/
        Toast.makeText(getApplicationContext(), "Player deleted successfully", Toast.LENGTH_LONG).show();

        return true;
    }

}

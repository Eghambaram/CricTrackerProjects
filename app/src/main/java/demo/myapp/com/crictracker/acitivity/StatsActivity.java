package demo.myapp.com.crictracker.acitivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import demo.myapp.com.crictracker.R;
import demo.myapp.com.crictracker.model.Player;
import demo.myapp.com.crictracker.other.PlayerList;

public class StatsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //view objects
    ListView listViewPlayers;

    //a list to store all the player from firebase database
    List<Player> players;

    //our database reference object
    DatabaseReference databasePlayers;
    Dialog myDialog;
    ImageView sortImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getting the reference of players node
        databasePlayers = FirebaseDatabase.getInstance().getReference("players");

        listViewPlayers = (ListView) findViewById(R.id.listViewPlayers);
        players = new ArrayList<>();

        myDialog = new Dialog(this);
        sortImg = (ImageView) findViewById(R.id.imageSort);
        sortImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowPopup();
                /*Toast.makeText(StatsActivity.this,
                        "The favorite list would appear on clicking this icon",
                        Toast.LENGTH_LONG).show();*/

            }
        });
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent homeIntent = new Intent(StatsActivity.this, MainActivity.class);
            startActivity(homeIntent);
        } else if (id == R.id.nav_player) {
            Intent homeIntent = new Intent(StatsActivity.this, PlayerActivity.class);
            startActivity(homeIntent);

        } else if (id == R.id.nav_stats) {
            Intent homeIntent = new Intent(StatsActivity.this, StatsActivity.class);
            startActivity(homeIntent);

        } else if (id == R.id.nav_login) {
            Intent homeIntent = new Intent(StatsActivity.this, LoginActivity.class);
            startActivity(homeIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
        TextView filterBy = (TextView) findViewById(R.id.filterBy);
         final String filter = filterBy.getText().toString().trim();
        Toast.makeText(getApplicationContext(), "Checked "+filter, Toast.LENGTH_SHORT).show();
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
                    if(filter.equals("Max. Runs")){
                        Collections.sort(players, new Comparator<Player>() {
                            @Override
                            public int compare(Player p1, Player p2) {
                                return (Integer.parseInt(p2.getP_batRuns())  - Integer.parseInt(p1.getP_batRuns()));
                            }


                        });

                    }
                    else if(filter.equals("Max. Wickets")){
                        Collections.sort(players, new Comparator<Player>() {
                            @Override
                            public int compare(Player p1, Player p2) {
                                return (Integer.parseInt(p2.getP_wkts())  - Integer.parseInt(p1.getP_wkts()));
                            }


                        });


                    }
                    else if(filter.equals("Max. 4s")){
                        Collections.sort(players, new Comparator<Player>() {
                            @Override
                            public int compare(Player p1, Player p2) {
                                return (Integer.parseInt(p2.getP_Fours())  - Integer.parseInt(p1.getP_Fours()));
                            }


                        });

                    }
                    else if(filter.equals("Max. 6s")){
                        Collections.sort(players, new Comparator<Player>() {
                            @Override
                            public int compare(Player p1, Player p2) {
                                return (Integer.parseInt(p2.getP_Sixes())  - Integer.parseInt(p1.getP_Sixes()));
                            }

                        });


                    }

                    else{

                    }

                  /* Collections.sort(players, new Comparator<Player>() {
                        @Override
                        public int compare(Player p1, Player p2) {
                            return p1.getPlayerName().compareToIgnoreCase(p2.getPlayerName());


                        }


                    });*/


                }

                //creating adapter
                PlayerList playerAdapter = new PlayerList(StatsActivity.this, players);
                //attaching adapter to the listview

                listViewPlayers.setAdapter(playerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void ShowPopup() {

        //getting views
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_player_sort_popup, null);
        dialogBuilder.setView(dialogView);

        /*final EditText editTextName = (EditText) dialogView.findViewById(R.id.new_input_playername);
        final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinnerGenres);*/
        TextView filterBy = (TextView) findViewById(R.id.filterBy);
        String selectRadio =filterBy.getText().toString().trim();

        final Button buttonFilter = (Button) dialogView.findViewById(R.id.btnFilter);
        final RadioButton radioRuns = (RadioButton) dialogView.findViewById(R.id.radioRuns);
        final RadioButton radioWickets = (RadioButton) dialogView.findViewById(R.id.radioWickets);
        final RadioButton radio4s = (RadioButton) dialogView.findViewById(R.id.radio4s);
        final RadioButton radio6s = (RadioButton) dialogView.findViewById(R.id.radio6s);


        if(selectRadio.equalsIgnoreCase("Max. Runs")){
                radioRuns.setChecked(true);
            }
        else if(selectRadio.equalsIgnoreCase("Max. Wickets")){
            radioWickets.setChecked(true);
        }

        else if(selectRadio.equalsIgnoreCase("Max. 4s")){
            radio4s.setChecked(true);
        }

        else if(selectRadio.equalsIgnoreCase("Max. 6s")){
            radio6s.setChecked(true);
        }

        dialogBuilder.setTitle("Filter By");
        final AlertDialog b = dialogBuilder.create();
        b.show();
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rRuns = radioRuns.getText().toString().trim();
                String rWickets = radioWickets.getText().toString().trim();
                String r4s = radio4s.getText().toString().trim();
                String r6s = radio6s.getText().toString().trim();
                TextView filterBy = (TextView) findViewById(R.id.filterBy);
                if(radio4s.isChecked())
                {
                    filterBy.setText(r4s);

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

                                Collections.sort(players, new Comparator<Player>() {
                                    @Override
                                    public int compare(Player p1, Player p2) {
                                        return (Integer.parseInt(p2.getP_Fours())  - Integer.parseInt(p1.getP_Fours()));
                                    }


                                });


                            }

                            //creating adapter
                            PlayerList playerAdapter = new PlayerList(StatsActivity.this, players);
                            //attaching adapter to the listview

                            listViewPlayers.setAdapter(playerAdapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    // is checked
                    b.dismiss();
                    Toast.makeText(getApplicationContext(), "Checked "+r4s, Toast.LENGTH_SHORT).show();

                }
                else if(radio6s.isChecked())
                {
                    filterBy.setText(r6s);

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

                                Collections.sort(players, new Comparator<Player>() {
                                    @Override
                                    public int compare(Player p1, Player p2) {
                                        return (Integer.parseInt(p2.getP_Sixes())  - Integer.parseInt(p1.getP_Sixes()));
                                    }


                                });


                            }
                            //creating adapter
                            PlayerList playerAdapter = new PlayerList(StatsActivity.this, players);
                            //attaching adapter to the listview
                            listViewPlayers.setAdapter(playerAdapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    b.dismiss();
                    Toast.makeText(getApplicationContext(), "Checked "+r6s, Toast.LENGTH_SHORT).show();

                }
                else if(radioRuns.isChecked())
                {
                    filterBy.setText(rRuns);
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

                                Collections.sort(players, new Comparator<Player>() {
                                    @Override
                                    public int compare(Player p1, Player p2) {
                                        return (Integer.parseInt(p2.getP_batRuns())  - Integer.parseInt(p1.getP_batRuns()));
                                    }


                                });


                            }
                            //creating adapter
                            PlayerList playerAdapter = new PlayerList(StatsActivity.this, players);
                            //attaching adapter to the listview
                            listViewPlayers.setAdapter(playerAdapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    b.dismiss();
                    Toast.makeText(getApplicationContext(), "Checked "+rRuns, Toast.LENGTH_SHORT).show();

                }
                else if(radioWickets.isChecked())
                {
                    filterBy.setText(rWickets);
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

                                Collections.sort(players, new Comparator<Player>() {
                                    @Override
                                    public int compare(Player p1, Player p2) {
                                        return (Integer.parseInt(p2.getP_wkts())  - Integer.parseInt(p1.getP_wkts()));
                                    }


                                });


                            }
                            //creating adapter
                            PlayerList playerAdapter = new PlayerList(StatsActivity.this, players);
                            //attaching adapter to the listview
                            listViewPlayers.setAdapter(playerAdapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    b.dismiss();
                    Toast.makeText(getApplicationContext(), "Checked "+r6s, Toast.LENGTH_SHORT).show();

                }
                else
                {
                    // not checked
                    Toast.makeText(getApplicationContext(), "Not checked"+r4s+ "  "+r6s, Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}

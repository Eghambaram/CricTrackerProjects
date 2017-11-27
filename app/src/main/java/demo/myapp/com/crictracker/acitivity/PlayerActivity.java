package demo.myapp.com.crictracker.acitivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import demo.myapp.com.crictracker.R;
import demo.myapp.com.crictracker.model.Player;
import demo.myapp.com.crictracker.other.PlayerList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //view objects
    ListView listViewPlayers;

    //a list to store all the player from firebase database
    List<Player> players;

    //our database reference object
    DatabaseReference databasePlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getting the reference of players node
        databasePlayers = FirebaseDatabase.getInstance().getReference("players");

        listViewPlayers = (ListView) findViewById(R.id.listViewPlayers);
        players = new ArrayList<>();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent homeIntent = new Intent(PlayerActivity.this, MainActivity.class);
            startActivity(homeIntent);
        } else if (id == R.id.nav_player) {
            Intent homeIntent = new Intent(PlayerActivity.this, PlayerActivity.class);
            startActivity(homeIntent);

        } else if (id == R.id.nav_stats) {
            Intent homeIntent = new Intent(PlayerActivity.this, StatsActivity.class);
            startActivity(homeIntent);
        } else if (id == R.id.nav_login) {
            Intent homeIntent = new Intent(PlayerActivity.this, LoginActivity.class);
            startActivity(homeIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

                   /* Comparator<Player> p1 = new Comparator<Player>() {
                        @Override
                        public int compare(Player p1, Player p2) {
                            return (Integer.parseInt(p1.getP_Fours())  - Integer.parseInt(p2.getP_Fours()));
                        }
                    };*/

                  /* Collections.sort(players, new Comparator<Player>() {
                        @Override
                        public int compare(Player p1, Player p2) {
                            return p1.getPlayerName().compareToIgnoreCase(p2.getPlayerName());


                        }


                    });*/

                     Collections.sort(players, new Comparator<Player>() {
                        @Override
                        public int compare(Player p1, Player p2) {
                            return (Integer.parseInt(p2.getP_Fours())  - Integer.parseInt(p1.getP_Fours()));
                        }


                    });


                }

                //creating adapter
                PlayerList playerAdapter = new PlayerList(PlayerActivity.this, players);
                //attaching adapter to the listview

                listViewPlayers.setAdapter(playerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

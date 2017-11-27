package demo.myapp.com.crictracker.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import demo.myapp.com.crictracker.R;

public class PlayerInfoActivity extends AppCompatActivity {

    String playerName;
    String playerImage;
    String playerGenre;
    String playerbatMatches;
    String playerbatInnings;
    String playerbatRuns;
    String playerFours;
    String playerSixes;
    String player0s;
    String playerbatHigest;

    String playerbowMatches;
    String playerbowInnings;
    String playerbowOvers;
    String playerwkts;
    String playermaidens;
    String playerbowBest;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();

        // Get the result of player
        playerName = i.getStringExtra("playerName");
        playerImage = i.getStringExtra("playerImage");
        playerGenre = i.getStringExtra("playerGenre");

        playerbatMatches = i.getStringExtra("p_batMatches");
        playerbatInnings = i.getStringExtra("p_batInnings");
        playerbatRuns = i.getStringExtra("p_batRuns");
        playerFours = i.getStringExtra("p_Fours");
        playerSixes = i.getStringExtra("p_Sixes");
        player0s = i.getStringExtra("p_0s");
        playerbatHigest = i.getStringExtra("p_batHigest");

        playerbowMatches = i.getStringExtra("p_bowMatches");
        playerbowInnings = i.getStringExtra("p_bowInnings");
        playerbowOvers = i.getStringExtra("p_bowOvers");
        playerwkts = i.getStringExtra("p_wkts");
        playermaidens = i.getStringExtra("p_maidens");
        playerbowBest = i.getStringExtra("p_bowBest");


        // Locate the TextViews in singleitemview.xml

        TextView p_name = (TextView) findViewById(R.id.playerName);
        TextView p_genre = (TextView) findViewById(R.id.playerGenre);
        imageView = (ImageView) findViewById(R.id.playerImage);

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
        p_name.setText(playerName);
        p_genre.setText(playerGenre);

        p_batMatches.setText(playerbatMatches);
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
        p_bowBest.setText(playerbowBest);

        Picasso.with(this)  //Here, this is context.
                .load(playerImage)  //Url of the image to load.
                .into(imageView);


    }

}

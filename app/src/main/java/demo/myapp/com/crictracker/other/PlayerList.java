package demo.myapp.com.crictracker.other;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import demo.myapp.com.crictracker.R;
import demo.myapp.com.crictracker.model.Player;

import demo.myapp.com.crictracker.acitivity.PlayerInfoActivity;

/**
 * Created by Belal on 2/26/2017.
 */

public class PlayerList extends ArrayAdapter<Player> {
    private Activity context;
    List<Player> players;

    public PlayerList(Activity context, List<Player> players) {
        super(context, R.layout.layout_player_list, players);
        this.context = context;
        this.players = players;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout;

        LayoutInflater inflater = context.getLayoutInflater();
        final  View listViewItem = inflater.inflate(R.layout.layout_player_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        ImageView imageViewPlayer = (ImageView) listViewItem.findViewById(R.id.playerIcon);

        linearLayout= (LinearLayout) listViewItem.findViewById(R.id.linerLayout);

        final Player player = players.get(position);
        textViewName.setText(player.getPlayerName());
        textViewGenre.setText(player.getPlayerGenre());
        Picasso.with(context)
                .load(player.getPlayerImage())
                .resize(50,50)
                .into(imageViewPlayer);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(listViewItem.getContext(),PlayerInfoActivity.class);

                intent.putExtra("playerName",player.getPlayerName());
                intent.putExtra("playerImage",player.getPlayerImage());
                intent.putExtra("playerGenre",player.getPlayerGenre());

                intent.putExtra("p_batMatches",player.getP_batMatches());
                intent.putExtra("p_batInnings",player.getP_batInnings());
                intent.putExtra("p_batRuns",player.getP_batRuns());
                intent.putExtra("p_Fours",player.getP_Fours());
                intent.putExtra("p_Sixes",player.getP_Sixes());
                intent.putExtra("p_0s",player.getP_0s());
                intent.putExtra("p_batHigest",player.getP_batHigest());

                intent.putExtra("p_bowMatches",player.getP_bowMatches());
                intent.putExtra("p_bowInnings",player.getP_bowInnings());
                intent.putExtra("p_bowOvers",player.getP_bowOvers());
                intent.putExtra("p_wkts",player.getP_wkts());
                intent.putExtra("p_maidens",player.getP_maidens());
                intent.putExtra("p_bowBest",player.getP_bowBest());

                context.startActivity(intent);

                //Toast.makeText(context,"You clicked " + player.getPlayerName(),Toast.LENGTH_SHORT).show();

            }
        });

        return listViewItem;
    }


}
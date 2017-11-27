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

import java.util.List;

import demo.myapp.com.crictracker.R;
import demo.myapp.com.crictracker.model.Player;

import demo.myapp.com.crictracker.acitivity.PlayerInfoActivity;

/**
 * Created by Belal on 2/26/2017.
 */

public class UpdatePlayerList extends ArrayAdapter<Player> {
    private Activity context;
    List<Player> players;

    public UpdatePlayerList(Activity context, List<Player> players) {
        super(context, R.layout.layout_player_list, players);
        this.context = context;
        this.players = players;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout;

        LayoutInflater inflater = context.getLayoutInflater();
        final  View listViewItem = inflater.inflate(R.layout.layout_player_update_delete_list, null, true);

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

       /* linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(listViewItem.getContext(),PlayerInfoActivity.class);

                intent.putExtra("playerName",player.getPlayerName());
                intent.putExtra("playerImage",player.getPlayerImage());
                intent.putExtra("playerGenre",player.getPlayerGenre());
                context.startActivity(intent);

                Toast.makeText(context,"You clicked " + player.getPlayerName(),Toast.LENGTH_SHORT).show();

            }
        });*/


        return listViewItem;
    }
}
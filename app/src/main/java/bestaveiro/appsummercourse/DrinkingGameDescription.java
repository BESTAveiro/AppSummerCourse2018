package bestaveiro.appsummercourse;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DrinkingGameDescription extends AppCompatActivity {

    String[] all_drinking_names;

    String[] all_min_players;

    String[] all_max_players;

    String[] all_materials;

    String[] all_descriptions;


    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinking_game_description);

        all_drinking_names=getResources().getStringArray(R.array.all_drinking_games);

        all_min_players=getResources().getStringArray(R.array.all_min_players);

        all_max_players=getResources().getStringArray(R.array.all_max_players);

        all_materials=getResources().getStringArray(R.array.all_materials);

        all_descriptions=getResources().getStringArray(R.array.all_descriptions);

        position=getIntent().getIntExtra("position",0);

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/regular.ttf");
        Typeface tf2=Typeface.createFromAsset(getAssets(),"fonts/light.ttf");
        //Botão
        Button bt=(Button) findViewById(R.id.buttonBeerRoulette);
        bt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/neo.ttf"));
        bt.setText(all_drinking_names[position]);

        //texto estatico
        TextView tv1=(TextView) findViewById(R.id.MinPlayers);
        tv1.setTypeface(tf);
        TextView tv2=(TextView) findViewById(R.id.MaxPlayers);
        tv2.setTypeface(tf);
        TextView tv3=(TextView) findViewById(R.id.Materials);
        tv3.setTypeface(tf);

        //Texto não estatico!
        TextView tv4=(TextView) findViewById(R.id.MinPlayers2);
        tv4.setTypeface(tf2);
        tv4.setText(all_min_players[position]);

        TextView tv5=(TextView) findViewById(R.id.MaxPlayers2);
        tv5.setTypeface(tf2);
        tv5.setText(all_max_players[position]);

        TextView tv6=(TextView) findViewById(R.id.Materials2);
        tv6.setTypeface(tf2);
        tv6.setText(all_materials[position]);

        TextView tv7=(TextView) findViewById(R.id.Description);
        tv7.setTypeface(tf2);
        tv7.setText(all_descriptions[position]);

    }
}

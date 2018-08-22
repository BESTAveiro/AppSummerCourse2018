package bestaveiro.appsummercourse;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class main_drinking_and_get2know_games extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drinking_and_get2know_games);

        main_drinking_and_get2know_games.this.setTitle("Drinking Games and G2K");


        Typeface tf=Typeface.createFromAsset(main_drinking_and_get2know_games.this.getAssets(), "fonts/neo.ttf");
        TextView tv=(TextView) findViewById(R.id.DrinkingGameFont);
        tv.setTypeface(tf);
        TextView tv2=(TextView) findViewById(R.id.Get2KnowFont);
        tv2.setTypeface(tf);

        ImageButton g2k_button=(ImageButton) findViewById(R.id.Get2KnowButton);
        ImageButton drinking_button = (ImageButton) findViewById(R.id.DrinkingGameButton);

        g2k_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(main_drinking_and_get2know_games.this, Get2Know.class);
                startActivity(intent);
            }
        });
        drinking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(main_drinking_and_get2know_games.this, DrinkingGame.class);
                startActivity(intent);
            }
        });
    }
}

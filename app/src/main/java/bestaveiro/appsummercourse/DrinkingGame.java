package bestaveiro.appsummercourse;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DrinkingGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinking_game);

        ListView lv = (ListView) findViewById(R.id.list_view_drinking);
        lv.setAdapter(new ListViewAdapter());
        lv.setDivider(null);


        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/neo.ttf");
        TextView tv=(TextView) findViewById(R.id.DrinkingGameTitleFont);
        tv.setTypeface(tf);
    }

    class ListViewAdapter extends BaseAdapter
    {

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/neo.ttf");
        String[] all_games;


        public ListViewAdapter() {

            all_games=getResources().getStringArray(R.array.all_drinking_games);

        }

        @Override
        public int getCount() {
            return 16;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            View v = view;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getBaseContext());
                v = vi.inflate(R.layout.list_view_item, null);
            }
            ((Button)v.findViewById(R.id.buttonBeerRoulette)).setText(all_games[position]);
            ((Button)v.findViewById(R.id.buttonBeerRoulette)).setTypeface(tf);
            ((Button)v.findViewById(R.id.buttonBeerRoulette)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tmp=new Intent(DrinkingGame.this, DrinkingGameDescription.class);
                    tmp.putExtra("position",position);
                    startActivity(tmp);
                }
            });
            return v;
        }
    }
}

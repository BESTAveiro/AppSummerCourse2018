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

public class Get2Know extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get2_know);

        ListView lv= (ListView) findViewById(R.id.list_view_get2know);
        lv.setAdapter(new ListViewAdapter());
        lv.setDivider(null);

        Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/neo.ttf");
        TextView tv=(TextView) findViewById(R.id.Get2KnowGameTitleFont);
        tv.setTypeface(tf);
    }

    class ListViewAdapter extends BaseAdapter {
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/neo.ttf");
        String[] all_games;

        public ListViewAdapter() {
            all_games=getResources().getStringArray(R.array.get2know_games);
        }

        @Override
        public int getCount() {
            return 11;
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View v=view;
            if(v==null){
                LayoutInflater vi=LayoutInflater.from(getBaseContext());
                v=vi.inflate(R.layout.list_view_item,null);
            }
            Button g2kButtons= (Button) v.findViewById(R.id.buttonBeerRoulette);
            g2kButtons.setText(all_games[i]);
            g2kButtons.setTypeface(tf);
            g2kButtons.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent tmp=new Intent(Get2Know.this, Get2KnowDescription.class);
                    tmp.putExtra("position", i);
                    startActivity(tmp);
                }
            });
            return v;
        }
    }
}

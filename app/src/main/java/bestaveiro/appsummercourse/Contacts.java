package bestaveiro.appsummercourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static bestaveiro.appsummercourse.R.id.lblListItem;
import static bestaveiro.appsummercourse.R.id.lvExp;

//https://www.youtube.com/watch?v=jZxZIFnJ9jE

public class Contacts extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(getBaseContex0201
                        t(), People.class);

                //intent.putExtra("position", il);
                startActivity(intent);
                return false;
            }
        });
    }


    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Core Team");
        listDataHeader.add("Organisers");
        listDataHeader.add("Participants");

        List<String> edmtDev = new ArrayList<String>();
        edmtDev.add("This is Expandable ListView");

        List<String> androidStudio = new ArrayList<String>();
        androidStudio.add("Expandable ListView");
        androidStudio.add("Google Map");
        androidStudio.add("Chat Application");
        androidStudio.add("Firebase ");

        List<String> group3 = new ArrayList<String>();
        group3.add("Expandable ListView");
        group3.add("Google Map");
        group3.add("Chat Application");
        group3.add("Firebase ");


        listHash.put(listDataHeader.get(0),edmtDev);
        listHash.put(listDataHeader.get(1),androidStudio);
        listHash.put(listDataHeader.get(2),group3);


    }
}

package bestaveiro.appsummercourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static bestaveiro.appsummercourse.R.id.lblListItem;
import static bestaveiro.appsummercourse.R.id.lvExp;

//https://www.youtube.com/watch?v=jZxZIFnJ9jE

public class Contacts extends AppCompatActivity {
    private final String TAG="Contacts";
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    String[] nomeDasPessoas_CoreTeam;
    String[] nomeDasPessoas_Organisers;
    String[] nomeDasPessoas_Participantes;


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
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Intent intent = new Intent(getBaseContext(), People.class);

                childPosition = 1 + childPosition;
                groupPosition = 1 + groupPosition;

                int position = groupPosition*100 + childPosition;

                Log.w(TAG,"Position: "+Integer.toString(position));

                intent.putExtra("position", position);
                startActivity(intent);
                return true;
            }
        });
    }


    private void initData() {
        listDataHeader = new ArrayList<String>();
        listHash = new HashMap<>();

        listDataHeader.add("Core Team");
        listDataHeader.add("Organisers");
        listDataHeader.add("Participants");

        nomeDasPessoas_CoreTeam = getResources().getStringArray(R.array.CoreTeam);
        List<String> CoreTeam = Arrays.asList(nomeDasPessoas_CoreTeam);
        nomeDasPessoas_Organisers = getResources().getStringArray(R.array.Organisers);
        List<String> Organisers = Arrays.asList(nomeDasPessoas_Organisers);
        nomeDasPessoas_Participantes = getResources().getStringArray(R.array.Participants);
        List<String> Participants = Arrays.asList(nomeDasPessoas_Participantes);

        listHash.put(listDataHeader.get(0),CoreTeam);
        listHash.put(listDataHeader.get(1),Organisers);
        listHash.put(listDataHeader.get(2),Participants);


    }
}

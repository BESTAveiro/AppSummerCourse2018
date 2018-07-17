package bestaveiro.appsummercourse;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SearchView;

import static android.content.ContentValues.TAG;

public class Contacts extends AppCompatActivity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ContactsParent> contactsParentList = new ArrayList<ContactsParent>();

    Drawable[] drawables;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        this.setTitle("Contacts"); // Nome no separador

        displayList();

        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_contacts, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) menu.findItem(R.id.searchmenu).getActionView();
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        return true;
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {

        //display the list
        loadSomeData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(Contacts.this, contactsParentList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

    }
    ImageView imageView;
    private void loadSomeData() {

        String[] coreTeamStrings = getResources().getStringArray(R.array.CoreTeam);
        String[] organisersStrings = getResources().getStringArray(R.array.Organisers);
        String[] participantsStrings = getResources().getStringArray(R.array.Participants);


        drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.valente), // CoreTeam 1
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente)
        };



        ArrayList<ContactsChild> contactsChildList = new ArrayList<ContactsChild>();
        ContactsChild contactsChild;
        for(int i = 0; i<coreTeamStrings.length; i++){
            contactsChild = new ContactsChild(coreTeamStrings[i], drawables[i]);
            contactsChildList.add(contactsChild);

            Log.w(TAG,"Position merdasdasdq asdsad"+ coreTeamStrings[i]);

        }

        ContactsParent contactsParent = new ContactsParent("Core Team", contactsChildList);
        contactsParentList.add(contactsParent);

        drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.valente), // Organiser 1
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente)
        };

        contactsChildList = new ArrayList<ContactsChild>();
        for(int i = 0; i<coreTeamStrings.length; i++){
            contactsChild = new ContactsChild(organisersStrings[i], drawables[i]);
            contactsChildList.add(contactsChild);
        }

        contactsParent = new ContactsParent("Organisers", contactsChildList);
        contactsParentList.add(contactsParent);

        drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.valente), // Participant 1
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente),
                getResources().getDrawable(R.drawable.valente)
        };

        contactsChildList = new ArrayList<ContactsChild>();
        for(int i = 0; i<participantsStrings.length; i++){
            contactsChild = new ContactsChild(participantsStrings[i], drawables[i]);
            contactsChildList.add(contactsChild);
        }

        contactsParent = new ContactsParent("Participants", contactsChildList);
        contactsParentList.add(contactsParent);

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }
}
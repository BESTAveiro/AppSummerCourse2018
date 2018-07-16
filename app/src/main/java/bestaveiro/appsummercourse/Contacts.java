package bestaveiro.appsummercourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import static android.content.ContentValues.TAG;

public class Contacts extends AppCompatActivity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ContactsParent> contactsParentList = new ArrayList<ContactsParent>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        //display the list
        displayList();
        //expand all Groups

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

    private void loadSomeData() {

        String[] coreTeamStrings = getResources().getStringArray(R.array.CoreTeam);
        String[] organisersStrings = getResources().getStringArray(R.array.Organisers);
        String[] participantsStrings = getResources().getStringArray(R.array.Participants);


        ArrayList<ContactsChild> contactsChildList = new ArrayList<ContactsChild>();
        ContactsChild contactsChild;
        for(int i = 0; i<coreTeamStrings.length; i++){
            contactsChild = new ContactsChild(coreTeamStrings[i]);
            contactsChildList.add(contactsChild);
        }

        ContactsParent contactsParent = new ContactsParent("Core Team", contactsChildList);
        contactsParentList.add(contactsParent);

        contactsChildList = new ArrayList<ContactsChild>();
        for(int i = 0; i<coreTeamStrings.length; i++){
            contactsChild = new ContactsChild(organisersStrings[i]);
            contactsChildList.add(contactsChild);
        }

        contactsParent = new ContactsParent("Organisers", contactsChildList);
        contactsParentList.add(contactsParent);

        contactsChildList = new ArrayList<ContactsChild>();
        for(int i = 0; i<participantsStrings.length; i++){
            contactsChild = new ContactsChild(participantsStrings[i]);
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
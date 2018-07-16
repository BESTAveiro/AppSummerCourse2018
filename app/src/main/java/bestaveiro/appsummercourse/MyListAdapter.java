package bestaveiro.appsummercourse;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ContactsParent> contactsParentList;
    private ArrayList<ContactsParent> originalList;

    public MyListAdapter(Context context, ArrayList<ContactsParent> contactsParentList) {
        this.context = context;
        this.contactsParentList = new ArrayList<ContactsParent>();
        this.contactsParentList.addAll(contactsParentList);
        this.originalList = new ArrayList<ContactsParent>();
        this.originalList.addAll(contactsParentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ContactsChild> contactsChildList = contactsParentList.get(groupPosition).getContactsChildList();
        return contactsChildList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        ContactsChild contactsChild = (ContactsChild) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView code = (TextView) view.findViewById(R.id.code);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView population = (TextView) view.findViewById(R.id.population);
        code.setText(contactsChild.getCode().trim());
        name.setText(contactsChild.getName().trim());
        population.setText(NumberFormat.getNumberInstance(Locale.US).format(contactsChild.getPopulation()));

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<ContactsChild> contactsChildList = contactsParentList.get(groupPosition).getContactsChildList();
        return contactsChildList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return contactsParentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return contactsParentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        ContactsParent contactsParent = (ContactsParent) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.parent_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(contactsParent.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(contactsParentList.size()));
        contactsParentList.clear();

        if(query.isEmpty()){
            contactsParentList.addAll(originalList);
        }
        else {

            for(ContactsParent contactsParent : originalList){

                ArrayList<ContactsChild> contactsChildList = contactsParent.getContactsChildList();
                ArrayList<ContactsChild> newList = new ArrayList<ContactsChild>();
                for(ContactsChild contactsChild : contactsChildList){
                    if(contactsChild.getCode().toLowerCase().contains(query) ||
                            contactsChild.getName().toLowerCase().contains(query)){
                        newList.add(contactsChild);
                    }
                }
                if(newList.size() > 0){
                    ContactsParent nContactsParent = new ContactsParent(contactsParent.getName(),newList);
                    contactsParentList.add(nContactsParent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(contactsParentList.size()));
        notifyDataSetChanged();

    }

}


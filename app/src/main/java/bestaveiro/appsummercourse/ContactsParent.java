package bestaveiro.appsummercourse;

import java.util.ArrayList;

public class ContactsParent {

    private String group;
    private ArrayList<ContactsChild> contactsChildList;

    public ContactsParent(String group, ArrayList<ContactsChild> contactsChildList) {
        super();
        this.group = group;
        this.contactsChildList = contactsChildList;
    }
    public String getGroup() {
        return group;
    }
    public void setName(String group) {
        this.group = group;
    }
    public ArrayList<ContactsChild> getContactsChildList() {
        return contactsChildList;
    }
    public void setContactsChildList(ArrayList<ContactsChild> contactsChildList) {
        this.contactsChildList = contactsChildList;
    };


}
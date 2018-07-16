package bestaveiro.appsummercourse;

import java.util.ArrayList;

public class ContactsParent {

    private String name;
    private ArrayList<ContactsChild> contactsChildList = new ArrayList<ContactsChild>();

    public ContactsParent(String name, ArrayList<ContactsChild> contactsChildList) {
        super();
        this.name = name;
        this.contactsChildList = contactsChildList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<ContactsChild> getContactsChildList() {
        return contactsChildList;
    }
    public void setContactsChildList(ArrayList<ContactsChild> contactsChildList) {
        this.contactsChildList = contactsChildList;
    };


}

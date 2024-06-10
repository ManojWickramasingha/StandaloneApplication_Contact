import java.util.*;

class ContactDBConnection {
    public static ContactDBConnection contactDBConnection;
    private ArrayList<Contact> contactList;

    private ContactDBConnection() {
        contactList = new ArrayList<Contact>();
    }

    public static ContactDBConnection Instance() {
        if (contactDBConnection == null) {
            contactDBConnection = new ContactDBConnection();
        }
        return contactDBConnection;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }
}

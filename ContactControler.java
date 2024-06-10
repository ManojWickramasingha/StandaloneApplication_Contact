import java.util.*;

class ContactControler {

    public static boolean addContact(Contact contact) {
        ArrayList<Contact> contactList = ContactDBConnection.Instance().getContactList();
        return contactList.add(contact);
    }

    public static boolean updateContact() {
        return true;
    }

    public static Contact[] Search(String textValue) {
        ArrayList<Contact> contactList = ContactDBConnection.Instance().getContactList();
        Contact[] seArray = new Contact[0];
        for (int i = 0; i < contactList.size(); i++) {
            if (textValue.equals(contactList.get(i).getName())
                    || textValue.equals(contactList.get(i).getNumber())) {

                Contact[] temp = new Contact[seArray.length + 1];
                // ----copy peverse data----
                for (int j = 0; j < seArray.length; j++) {
                    temp[j] = seArray[j];
                }
                seArray = temp;
                seArray[seArray.length - 1] = contactList.get(i);

            }
        }
        return seArray;

    }

    public static String ok() {
        return "OK";
    }

    public static void update(Contact[] contactList, String newValue, String option) {

        switch (option) {
            case "name":
                contactList[0].setName(newValue);
                break;
            case "number":
                contactList[0].setNumber(newValue);
                break;
            case "company":
                contactList[0].setCompany(newValue);
                break;
            case "salary":

                break;
            case "BDay":
                contactList[0].setBirthday(newValue);
                break;

            default:
                break;
        }

    }

    public static void updateSalary(Contact[] contactList, double newValue) {

        contactList[0].setSalary(newValue);
    }

    private static ArrayList<Contact> CopyArr() {
        int len = ContactDBConnection.Instance().getContactList().size();

        ArrayList<Contact> tempary = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            tempary.add(ContactDBConnection.Instance().getContactList().get(i));
        }
        return tempary;
    }

    public static ArrayList<Contact> sortName() {
        ArrayList<Contact> sortList = CopyArr();

        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = 0; j < sortList.size() - 1 - i; j++) {
                if (sortList.get(i).getName().compareTo(sortList.get(i).getName()) > 0) {
                    Contact contact = sortList.get(i);
                    sortList.add(i, sortList.get(i + 1));
                    sortList.add((i + 1), contact);

                }
            }
        }
        return sortList;

    }

    public static ArrayList<Contact> sortBday() {
        ArrayList<Contact> sortList = CopyArr();

        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = 0; j < sortList.size() - 1 - i; j++) {
                if (sortList.get(i).getBirthday().compareTo(sortList.get(i).getBirthday()) > 0) {
                    Contact contact = sortList.get(i);
                    sortList.add(i, sortList.get(i + 1));
                    sortList.add((i + 1), contact);

                }
            }
        }
        return sortList;

    }

    public static ArrayList<Contact> sortSalary() {
        ArrayList<Contact> sortList = CopyArr();

        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = 0; j < sortList.size() - 1 - i; j++) {
                if (sortList.get(i).getSalary() > sortList.get(i + 1).getSalary()) {
                    Contact contact = sortList.get(i);
                    sortList.add(i, sortList.get(i + 1));
                    sortList.add((i + 1), contact);

                }
            }
        }
        return sortList;

    }

    public static void delete(Contact[] arr) {
        ArrayList<Contact> contactList = ContactDBConnection.Instance().getContactList();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i) == arr[0]) {
                contactList.remove(i);
            }
        }
    }
}

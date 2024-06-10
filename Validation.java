import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Validation {

    public boolean isduplicate(String ID) {
        ArrayList<Contact> contactList = ContactDBConnection.Instance().getContactList();
        int length = contactList.size();
        for (int i = 0; i < length; i++) {
            if (ID.equals(contactList.get(i).getContactID())
                    || ID.equals(contactList.get(i).getNumber())) {
                return true;
            }
        }
        return false;
    }

    public String displayID(int count) {

        String ID = AddContactForm.GenarateContactID(++count);
        System.out.println(" " + ID);
        System.out.println(" ======");
        String check = "C";

        if (check.equals(ID)) {
            System.out.println("\n\n");
            return "Full contact Number!";
        }

        if (isduplicate(ID)) {
            return "Duplicate";
        }

        return ID;
    }

    public boolean validateNumber(String PhoneNumber) {
        char[] original = new char[0];
        int count = 0;
        for (int i = 0; i < PhoneNumber.length(); i++) {
            char[] temp = new char[original.length + 1];
            // ---------------copy to data--------------
            for (int j = 0; j < original.length; j++) {
                temp[j] = original[j];
            }
            if (PhoneNumber.charAt(0) != '0') {
                return false;
            } else {
                original = temp;
                original[original.length - 1] = PhoneNumber.charAt(i);
                count++;
            }
        }
        return count == 10;

    }

    public boolean validateBDay(String BDay) {
        SimpleDateFormat sam = new SimpleDateFormat("YYY-MM-DD");
        String nowDate = sam.format(new Date());
        try {
            Date d1 = sam.parse(BDay);
            Date d2 = sam.parse(nowDate);
            if (d2.before(d1)) {
                return false;
            }
        } catch (ParseException e) {

        }

        return true;
    }

}

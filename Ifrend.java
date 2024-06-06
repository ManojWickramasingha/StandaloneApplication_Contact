import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;

class Contact {
    private String contactID;
    private String name;
    private String number;
    private String company;
    private double salary;
    private String birthday;

    public Contact(String contactID, String name, String number, String company, double salary, String birthday) {
        this.contactID = contactID;
        this.name = name;
        this.number = number;
        this.company = company;
        this.salary = salary;
        this.birthday = birthday;
    }

    // create getters-------------------------------
    public String getContactID() {
        return contactID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCompany() {
        return company;
    }

    public double getSalary() {
        return salary;
    }

    public String getBirthday() {
        return birthday;
    }

    // create setters-----------------------------
    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}

class ContactHomeForm extends JFrame {

    JLabel homelabel;
    JButton addnewcontact;
    JButton updatecontact;
    JButton searchcontact;
    JButton deletecontact;
    JButton viewcontact;
    JButton exit;

    ContactHomeForm() {
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel ContainParentPanner = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel ContentPannel = new JPanel(new GridLayout(7, 1));

        JPanel homeContactpannel = new JPanel(new FlowLayout());

        homelabel = new JLabel("Home Page");
        homelabel.setFont(new Font("", 1, 30));

        homeContactpannel.add(homelabel);
        ContentPannel.add(homeContactpannel);

        JPanel addNewContact = new JPanel(new FlowLayout());

        addnewcontact = new JButton("Add New Contact");
        addnewcontact.setFont(new Font("", 1, 20));
        addnewcontact.setHorizontalAlignment(JButton.CENTER);

        addNewContact.add(addnewcontact);
        ContentPannel.add(addNewContact);

        JPanel updatecontactPannel = new JPanel(new FlowLayout());

        updatecontact = new JButton("Update Contact");
        updatecontact.setFont(new Font("", 1, 20));
        updatecontact.setHorizontalAlignment(JButton.CENTER);

        updatecontactPannel.add(updatecontact);
        ContentPannel.add(updatecontactPannel);

        JPanel searchcontactPannel = new JPanel(new FlowLayout());

        searchcontact = new JButton("Search Contact");
        searchcontact.setFont(new Font("", 1, 20));
        searchcontact.setHorizontalAlignment(JButton.CENTER);

        searchcontactPannel.add(searchcontact);
        ContentPannel.add(searchcontactPannel);

        JPanel deletecontactPannel = new JPanel(new FlowLayout());

        deletecontact = new JButton("Delete Contact");
        deletecontact.setFont(new Font("", 1, 20));
        deletecontact.setHorizontalAlignment(JButton.CENTER);

        deletecontactPannel.add(deletecontact);
        ContentPannel.add(deletecontactPannel);

        JPanel viewcontactPannel = new JPanel(new FlowLayout());

        viewcontact = new JButton("View Contact");
        viewcontact.setFont(new Font("", 1, 20));
        viewcontact.setHorizontalAlignment(JButton.CENTER);

        viewcontactPannel.add(viewcontact);
        ContentPannel.add(viewcontactPannel);

        JPanel exitPannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        exit = new JButton("Exit");
        exit.setFont(new Font("", 1, 20));
        // exit.setHorizontalAlignment(JButton.CENTE);
        exitPannel.add(exit);
        ContentPannel.add(exitPannel);

        ContainParentPanner.add(ContentPannel);

        add("North", ContentPannel);
    }

}

class Ifrend {
    public static void main(String[] args) {
        ContactHomeForm contactHomeForm = new ContactHomeForm();
        contactHomeForm.setVisible(true);
    }
}
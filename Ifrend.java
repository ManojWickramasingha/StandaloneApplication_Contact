import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

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

// -----------------------Add New Contact Form---------------------

class AddContactForm extends JFrame {
    private JLabel titleLabel;

    private JButton btnAdd;
    private JButton btnCancel;
    private JButton btnBack;

    private JLabel lblName;
    private JLabel lblContactNumber;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBirthday;

    private JTextField textName;
    private JTextField textContactNumber;
    private JTextField textCompany;
    private JTextField textSalary;
    private JTextField textBirthday;
    JTextField id;
    private ContactHomeForm addcontactHomeform;

    public static String GenarateContactID(int count) {
        int[] temp = new int[4];
        int j = temp.length - 1;
        String s = "C";
        while (count != 0) {
            int rem = count % 10;
            if (j >= 0) {
                temp[j] = rem;
                j--;
            } else {
                System.out.print("	memory full!");
                return s;
            }
            count /= 10;

        }
        for (int i = 0; i < temp.length; i++) {
            s += temp[i];
        }

        return s;
    }

    public static boolean isduplicate(String ID) {

        int length = ContactHomeForm.contactList.size();
        for (int i = 0; i < length; i++) {
            if (ID.equals(ContactHomeForm.contactList.get(i).getContactID())
                    || ID.equals(ContactHomeForm.contactList.get(i).getContactID())) {
                return true;
            }
        }
        return false;
    }

    // static int count = 0;
    public static String displayID(int count) {

        String ID = GenarateContactID(++count);
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

    // ----validate contact number------
    public static boolean validateNumber(String PhoneNumber) {
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

    public static boolean validateBDay(String BDay) {
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

    AddContactForm(int count) {
        setSize(500, 300);
        setTitle("Add Contact Form");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Add Contact");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("", 1, 30));
        add("North", titleLabel);

        JPanel btnGrid = new JPanel(new GridLayout(2, 1));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnAdd = new JButton("ADD Contact");
        btnAdd.setFont(new Font("", 1, 20));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String Id = id.getText();
                String name = textName.getText();
                String contactnumber = textContactNumber.getText();
                if (validateNumber(contactnumber)) {
                    if (!isduplicate(contactnumber)) {
                        String company = textCompany.getText();
                        double salary = Double.parseDouble(textSalary.getText());
                        if (salary > 0) {
                            String birthday = textBirthday.getText();
                            if (validateBDay(birthday)) {
                                Contact contact = new Contact(Id, name, contactnumber, company, salary, birthday);
                                ContactHomeForm.contactList.add(contact);
                            } else {
                                JOptionPane.showMessageDialog(null, "Not a Validate Birthday",
                                        "agin added the new Birthday",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Not a Negative Salary", "agin added the new Salary",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Duplicate Contact Number", "agin added the new number",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "not valide Contact Number", "agin added the new number",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        btnPanel.add(btnAdd);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 20));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AddContactForm.this.dispose();
            }
        });

        btnPanel.add(btnCancel);

        JPanel backPannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnBack = new JButton("Back   To   Home  Page");
        btnBack.setFont(new Font("", 1, 20));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evnt) {
                if (addcontactHomeform == null) {
                    addcontactHomeform = new ContactHomeForm();
                }
                addcontactHomeform.setVisible(true);
            }
        });
        backPannel.add(btnBack);
        btnPanel.add(backPannel);

        btnGrid.add(btnPanel);
        btnGrid.add(backPannel);

        add("South", btnGrid);

        // JPanel lblandtextPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel lblPannel = new JPanel(new GridLayout(6, 1));

        JLabel ID = new JLabel("Contact ID");
        ID.setFont(new Font("", 1, 20));
        lblPannel.add(ID);

        lblName = new JLabel("Name");
        lblName.setFont(new Font("", 1, 20));
        lblPannel.add(lblName);

        lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setFont(new Font("", 1, 20));
        lblPannel.add(lblContactNumber);

        lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("", 1, 20));
        lblPannel.add(lblCompany);

        lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("", 1, 20));
        lblPannel.add(lblSalary);

        lblBirthday = new JLabel("Birthday");
        lblBirthday.setFont(new Font("", 1, 20));
        lblPannel.add(lblBirthday);

        add("West", lblPannel);

        JPanel textPannel = new JPanel(new GridLayout(6, 1));
        String newID = AddContactForm.displayID(count);
        JLabel TETid = new JLabel(newID);
        id = new JTextField(newID);
        TETid.setFont(new Font("", 1, 20));
        // JPanel idTextJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // idTextJPanel.add(TETid);
        textPannel.add(TETid);

        textName = new JTextField(10);
        textName.setFont(new Font("", 1, 20));
        JPanel nameTextPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameTextPannel.add(textName);
        textPannel.add(nameTextPannel);

        textContactNumber = new JTextField(10);
        textContactNumber.setFont(new Font("", 1, 20));
        JPanel contactTextPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contactTextPannel.add(textContactNumber);
        textPannel.add(contactTextPannel);

        textCompany = new JTextField(10);
        textCompany.setFont(new Font("", 1, 20));
        JPanel companyTextPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        companyTextPannel.add(textCompany);
        textPannel.add(companyTextPannel);

        textSalary = new JTextField(6);
        textSalary.setFont(new Font("", 1, 20));
        JPanel salaryTextPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        salaryTextPannel.add(textSalary);
        textPannel.add(salaryTextPannel);

        textBirthday = new JTextField(6);
        textBirthday.setFont(new Font("", 1, 20));
        JPanel birthdayTextPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        birthdayTextPannel.add(textBirthday);
        textPannel.add(birthdayTextPannel);

        // lblandtextPannel.add(lblPannel);
        // lblandtextPannel.add(textPannel);

        add("Center", textPannel);

    }
}

class ViewContactForm extends JFrame {
    private JTable ContactTable;
    private DefaultTableModel dtm;

    private JLabel tiLabel;

    private JButton backhome;

    ViewContactForm() {
        setSize(400, 300);
        setTitle("View Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tiLabel = new JLabel("List Contact By Name");
        tiLabel.setHorizontalAlignment(JLabel.CENTER);
        tiLabel.setFont(new Font("", 1, 30));

        add("North", tiLabel);

        String[] columnName = { "Contact ID", "Name", "Contact Number", "Company", "Salary", "Birthday" };
        dtm = new DefaultTableModel(columnName, 0);

        ContactTable = new JTable(dtm);

        JScrollPane tablepane = new JScrollPane(ContactTable);

        add("Center", tablepane);

        JPanel btnPannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        backhome = new JButton("Reload");
        backhome.setFont(new Font("", 1, 20));
        backhome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                for (int i = 0; i < ContactHomeForm.contactList.size(); i++) {
                    Contact contact = ContactHomeForm.contactList.get(i);
                    Object[] datarow = { contact.getContactID(), contact.getName(), contact.getNumber(),
                            contact.getCompany(), contact.getSalary(), contact.getBirthday() };
                    dtm.addRow(datarow);
                }
            }
        });
        btnPannel.add(backhome);
        add("South", btnPannel);

    }

}

class ContactHomeForm extends JFrame {
    static int count = 0;
    public static ArrayList<Contact> contactList = new ArrayList<>();
    JLabel title1;
    JLabel title2;
    JLabel home;
    private AddContactForm addcontactform;
    private ViewContactForm viewcontactform;

    private JButton btnAddContact;
    private JButton btnUpdateContact;
    private JButton btnSearchContact;
    private JButton btnDeleteContact;
    private JButton btnViewContact;
    private JButton btnExit;

    ContactHomeForm() {
        setSize(500, 300);
        setTitle("Contact management System");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        JPanel btnPannel = new JPanel(new GridLayout(7, 1, 10, 10));

        JPanel homepage = new JPanel(new FlowLayout(FlowLayout.CENTER));

        home = new JLabel("Home page");
        home.setFont(new Font("", 1, 30));
        homepage.add(home);

        btnPannel.add(homepage);

        btnAddContact = new JButton("Add New Contact");
        btnAddContact.setFont(new Font("", 1, 25));
        btnAddContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // if (addcontactform == null) {
                addcontactform = new AddContactForm(count++);
                // }
                addcontactform.setVisible(true);
            }
        });
        btnPannel.add(btnAddContact);

        btnUpdateContact = new JButton("Update Contact");
        btnUpdateContact.setFont(new Font("", 1, 25));
        btnPannel.add(btnUpdateContact);

        btnSearchContact = new JButton("Search Contact");
        btnSearchContact.setFont(new Font("", 1, 25));
        btnPannel.add(btnSearchContact);

        btnDeleteContact = new JButton("Delete Contact");
        btnDeleteContact.setFont(new Font("", 1, 25));
        btnPannel.add(btnDeleteContact);

        btnViewContact = new JButton("View Contact");
        btnViewContact.setFont(new Font("", 1, 25));
        btnViewContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewcontactform = new ViewContactForm();
                viewcontactform.setVisible(true);
            }
        });
        btnPannel.add(btnViewContact);

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("", 1, 25));
        exitPanel.add(btnExit);

        btnPannel.add(exitPanel);

        JPanel imagepannel = new JPanel(new GridLayout(2, 1));

        JPanel subimgpannel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel lbltitles = new JPanel(new GridLayout(2, 1));

        JPanel title1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel title2panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        title1 = new JLabel("Ifrends");
        title1.setFont(new Font("", 1, 30));
        title1Panel.add(title1);

        title2 = new JLabel("Contact Managment");
        title2.setFont(new Font("", 1, 30));
        title2panel.add(title2);

        lbltitles.add(title1Panel);
        lbltitles.add(title2panel);

        subimgpannel1.add(lbltitles);
        imagepannel.add(subimgpannel1);

        JPanel photo = new JPanel(new FlowLayout());

        JButton btnddd = new JButton("button");
        photo.add(btnddd);

        imagepannel.add(photo);

        add(imagepannel);
        add(btnPannel);

        pack();

    }
}

class Ifrend {
    public static void main(String args[]) {
        // new AddContactForm().setVisible(true);
        // new ViewContactForm().setVisible(true);
        new ContactHomeForm().setVisible(true);
    }
}

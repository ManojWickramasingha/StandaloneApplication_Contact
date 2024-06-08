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
        ArrayList<Contact> contactList = ContactDBConnection.Instance().getContactList();
        int length = contactList.size();
        for (int i = 0; i < length; i++) {
            if (ID.equals(contactList.get(i).getContactID())
                    || ID.equals(contactList.get(i).getContactID())) {
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
            ArrayList<Contact> contactList = ContactDBConnection.Instance().getContactList();

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
                                ContactControler.addContact(contact);

                                JOptionPane.showMessageDialog(null, "Add new Contact SuccessFully",
                                        "Success...",
                                        JOptionPane.INFORMATION_MESSAGE);

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
        pack();

    }
}

class ViewContactNameForm extends JFrame {
    private JTable ContactTable;
    private DefaultTableModel dtm;

    private JLabel tiLabel;

    private JButton backhome;

    ViewContactNameForm() {
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
            // ArrayList<Contact> contactList =
            // ContactDBConnection.Instance().getContactList();
            ArrayList<Contact> contactList = ContactControler.sortName();

            public void actionPerformed(ActionEvent evt) {
                for (int i = 0; i < contactList.size(); i++) {
                    Contact contact = contactList.get(i);
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

class ViewContactBdayForm extends JFrame {
    private JTable ContactTable;
    private DefaultTableModel dtm;

    private JLabel tiLabel;

    private JButton backhome;

    ViewContactBdayForm() {
        setSize(400, 300);
        setTitle("View Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tiLabel = new JLabel("List Contact By BDay");
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
            // ArrayList<Contact> contactList =
            // ContactDBConnection.Instance().getContactList();
            ArrayList<Contact> contactList = ContactControler.sortBday();

            public void actionPerformed(ActionEvent evt) {
                for (int i = 0; i < contactList.size(); i++) {
                    Contact contact = contactList.get(i);
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

class ViewContactSalaryForm extends JFrame {
    private JTable ContactTable;
    private DefaultTableModel dtm;

    private JLabel tiLabel;

    private JButton backhome;

    ViewContactSalaryForm() {
        setSize(400, 300);
        setTitle("View Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tiLabel = new JLabel("List Contact By Salary");
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
            // ArrayList<Contact> contactList =
            // ContactDBConnection.Instance().getContactList();
            ArrayList<Contact> contactList = ContactControler.sortSalary();

            public void actionPerformed(ActionEvent evt) {
                for (int i = 0; i < contactList.size(); i++) {
                    Contact contact = contactList.get(i);
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

class SearchContactForm extends JFrame {

    private JLabel lblTitle;

    private JLabel lblContactID;
    private JLabel lblName;
    private JLabel lblContactNumber;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBirthday;

    private JTextField textSearch;
    private JButton Search;
    private JButton backtohome;
    private JButton Next;

    private ContactHomeForm contacthomeform;

    private String ContactID;
    private String Name;
    private String ContactNumber;
    private String Company;
    private double Salary;
    private String Birthday;
    private JLabel s;

    JPanel detailsmain;

    // private Contact arr;

    // public static Contact Search(String textValue) {

    // return null;
    // }

    SearchContactForm() {
        setSize(800, 600);
        setTitle("Search Contact Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel titlepannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        lblTitle = new JLabel("SEARCH CONTACT");
        lblTitle.setFont(new Font("", 1, 30));
        titlepannel.add(lblTitle);

        add("North", titlepannel);

        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel maingrid = new JPanel(new GridLayout(1, 1));

        JPanel searchpannel = new JPanel(new FlowLayout());

        textSearch = new JTextField(20);
        textSearch.setFont(new Font("", 1, 20));
        searchpannel.add(textSearch);

        Next = new JButton("Next");
        Next.setFont(new Font("", 1, 20));

        Search = new JButton("Search");
        Search.setFont(new Font("", 1, 20));
        Search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                final String textValue = textSearch.getText();
                Contact[] contactList = ContactControler.Search(textValue);

                if (contactList.length == 1) {
                    ContactID = contactList[0].getContactID();
                    lblContactID.setText(ContactID);
                    Name = contactList[0].getName();
                    lblName.setText(Name);
                    ContactNumber = contactList[0].getNumber();
                    lblContactNumber.setText(ContactNumber);
                    Company = contactList[0].getCompany();
                    lblCompany.setText(Company);
                    Salary = contactList[0].getSalary();
                    lblSalary.setText("" + Salary);
                    Birthday = contactList[0].getBirthday();
                    lblBirthday.setText(Birthday);
                } else {
                    for (int i = 0; i < contactList.length; i++) {
                        ContactID = contactList[i].getContactID();
                        lblContactID.setText(ContactID);
                        Name = contactList[i].getName();
                        lblName.setText(Name);
                        ContactNumber = contactList[i].getNumber();
                        lblContactNumber.setText(ContactNumber);
                        Company = contactList[i].getCompany();
                        lblCompany.setText(Company);
                        Salary = contactList[i].getSalary();
                        lblSalary.setText("" + Salary);
                        Birthday = contactList[i].getBirthday();
                        lblBirthday.setText(Birthday);
                        s = new JLabel("Not OK");
                        Next.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                s.setText(ContactControler.ok());
                            }
                        });
                        if (s.getText().equals("OK")) {
                            // continue;
                        } else {
                            break;
                        }
                    }
                }

            }
        });
        searchpannel.add(Search);
        searchpannel.add(Next);

        // maingrid.add(searchpannel);
        detailsmain = new JPanel(new GridLayout(7, 1, 10, 10));

        detailsmain.add(searchpannel);
        lblContactID = new JLabel("ContactId -> " + ContactID);
        lblContactID.setFont(new Font("", 1, 20));
        detailsmain.add(lblContactID);

        lblName = new JLabel();
        lblName.setFont(new Font("", 1, 20));
        detailsmain.add(lblName);

        lblContactNumber = new JLabel();
        lblContactNumber.setFont(new Font("", 1, 20));
        detailsmain.add(lblContactNumber);

        lblCompany = new JLabel();
        lblCompany.setFont(new Font("", 1, 20));
        detailsmain.add(lblCompany);

        lblSalary = new JLabel();
        lblSalary.setFont(new Font("", 1, 20));
        detailsmain.add(lblSalary);

        lblBirthday = new JLabel();
        lblBirthday.setFont(new Font("", 1, 20));
        detailsmain.add(lblBirthday);

        JPanel btnpanner = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        backtohome = new JButton("Back To Home Page");
        backtohome.setFont(new Font("", 1, 20));
        backtohome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (contacthomeform == null) {
                    contacthomeform = new ContactHomeForm();
                }
                contacthomeform.setVisible(true);
            }
        });
        btnpanner.add(backtohome);

        // detailsmain.add(btnpanner);

        maingrid.add(detailsmain);

        main.add(maingrid);

        add("Center", main);
        add("South", btnpanner);
        // pack();

    }
}

class UpdateName extends JFrame {
    private JLabel name;
    private JTextField nametext;
    private JButton update;
    private JButton cancel;
    private JLabel title;

    UpdateName() {
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("Update Name");
        title.setFont(new Font("", 1, 20));
        add("North", title);

        JPanel textPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        name = new JLabel("Name");
        name.setFont(new Font("", 1, 20));
        textPannel.add(name);

        nametext = new JTextField(10);
        nametext.setFont(new Font("", 1, 20));
        textPannel.add(nametext);

        add("Center", textPannel);

        JPanel btnpannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        update = new JButton("Update");
        update.setFont(new Font("", 1, 20));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final String newname = nametext.getText();
                ContactControler.update(UpdateContactForm.contactList, newname, "name");
                JOptionPane.showMessageDialog(null, "Name update SuccessFully",
                        "Success...",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnpannel.add(update);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("", 1, 20));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UpdateName.this.dispose();
            }
        });
        btnpannel.add(cancel);

        add("South", btnpannel);
        pack();

    }
}

class UpdateContactNumber extends JFrame {

    private JLabel name;
    private JTextField nametext;
    private JButton update;
    private JButton cancel;
    private JLabel title;

    UpdateContactNumber() {
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("Update ContactNumber");
        title.setFont(new Font("", 1, 20));
        add("North", title);

        JPanel textPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        name = new JLabel("ContactNumber");
        name.setFont(new Font("", 1, 20));
        textPannel.add(name);

        nametext = new JTextField(10);
        nametext.setFont(new Font("", 1, 20));
        textPannel.add(nametext);

        add("Center", textPannel);

        JPanel btnpannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        update = new JButton("Update");
        update.setFont(new Font("", 1, 20));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final String newname = nametext.getText();
                ContactControler.update(UpdateContactForm.contactList, newname, "number");
                JOptionPane.showMessageDialog(null, "Update contact number SuccessFully",
                        "Success...",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnpannel.add(update);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("", 1, 20));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent avt) {
                UpdateContactNumber.this.dispose();
            }
        });
        btnpannel.add(cancel);

        add("South", btnpannel);
        pack();

    }
}

class UpdateSalary extends JFrame {

    private JLabel name;
    private JTextField nametext;
    private JButton update;
    private JButton cancel;
    private JLabel title;

    UpdateSalary() {
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("Update Salary");
        title.setFont(new Font("", 1, 20));
        add("North", title);

        JPanel textPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        name = new JLabel("Salary");
        name.setFont(new Font("", 1, 20));
        textPannel.add(name);

        nametext = new JTextField(10);
        nametext.setFont(new Font("", 1, 20));
        textPannel.add(nametext);

        add("Center", textPannel);

        JPanel btnpannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        update = new JButton("Update");
        update.setFont(new Font("", 1, 20));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final double newname = Double.parseDouble(nametext.getText());
                ContactControler.updateSalary(UpdateContactForm.contactList, newname);
                JOptionPane.showMessageDialog(null, "Update Salary SuccessFully",
                        "Success...",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnpannel.add(update);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("", 1, 20));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent avt) {
                UpdateSalary.this.dispose();
            }
        });
        btnpannel.add(cancel);

        add("South", btnpannel);
        pack();

    }
}

class UpdateCompany extends JFrame {

    private JLabel name;
    private JTextField nametext;
    private JButton update;
    private JButton cancel;
    private JLabel title;

    UpdateCompany() {
        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("Update Company");
        title.setFont(new Font("", 1, 20));
        add("North", title);

        JPanel textPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        name = new JLabel("Company");
        name.setFont(new Font("", 1, 20));
        textPannel.add(name);

        nametext = new JTextField(10);
        nametext.setFont(new Font("", 1, 20));
        textPannel.add(nametext);

        add("Center", textPannel);

        JPanel btnpannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        update = new JButton("Update");
        update.setFont(new Font("", 1, 20));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final String newname = nametext.getText();
                ContactControler.update(UpdateContactForm.contactList, newname, "company");
                JOptionPane.showMessageDialog(null, "Update Company name SuccessFully",
                        "Success...",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnpannel.add(update);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("", 1, 20));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent avt) {
                UpdateCompany.this.dispose();
            }
        });
        btnpannel.add(cancel);

        add("South", btnpannel);
        pack();

    }
}

class UpdateBDay extends JFrame {

    private JLabel name;
    private JTextField nametext;
    private JButton update;
    private JButton cancel;
    private JLabel title;

    UpdateBDay() {

        setSize(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("Update BDay");
        title.setFont(new Font("", 1, 20));
        add("North", title);

        JPanel textPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        name = new JLabel("BDay");
        name.setFont(new Font("", 1, 20));
        textPannel.add(name);

        nametext = new JTextField(10);
        nametext.setFont(new Font("", 1, 20));
        textPannel.add(nametext);

        add("Center", textPannel);

        JPanel btnpannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        update = new JButton("Update");
        update.setFont(new Font("", 1, 20));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final String newname = nametext.getText();
                ContactControler.update(UpdateContactForm.contactList, newname, "BDay");
                JOptionPane.showMessageDialog(null, "Update BDay SuccessFully",
                        "Success...",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnpannel.add(update);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("", 1, 20));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent avt) {
                UpdateBDay.this.dispose();
            }
        });
        btnpannel.add(cancel);

        add("South", btnpannel);
        pack();

    }
}

class SubUpdateForm extends JFrame {
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    private JButton bt4;
    private JButton bt5;

    private UpdateName updatename;
    private UpdateContactNumber updatecontactnumber;
    private UpdateCompany updatecontactcompany;
    private UpdateBDay updatebday;
    private UpdateSalary updatesalary;

    SubUpdateForm() {
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        bt1 = new JButton("Update Name");
        bt1.setFont(new Font("", 1, 20));
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (updatename == null) {
                    updatename = new UpdateName();
                }
                updatename.setVisible(true);
            }
        });
        add(bt1);

        bt2 = new JButton("Update ContactNumber");
        bt2.setFont(new Font("", 1, 20));
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (updatecontactnumber == null) {
                    updatecontactnumber = new UpdateContactNumber();
                }
                updatecontactnumber.setVisible(true);
            }
        });
        add(bt2);

        bt3 = new JButton("Update Company");
        bt3.setFont(new Font("", 1, 20));
        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (updatecontactcompany == null) {
                    updatecontactcompany = new UpdateCompany();
                }
                updatecontactcompany.setVisible(true);
            }
        });
        add(bt3);

        bt4 = new JButton("Update Salary");
        bt4.setFont(new Font("", 1, 20));
        bt4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (updatesalary == null) {
                    updatesalary = new UpdateSalary();
                }
                updatesalary.setVisible(true);
            }
        });
        add(bt4);

        bt5 = new JButton("Update Birthday");
        bt5.setFont(new Font("", 1, 20));
        bt5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (updatebday == null) {
                    updatebday = new UpdateBDay();
                }
                updatebday.setVisible(true);
            }
        });
        add(bt5);

    }
}

class UpdateContactForm extends JFrame {
    private JLabel lblTitle;
    private JTextField textSearch;
    private JButton Search;

    private JLabel lblContactID;
    private JLabel lblName;
    private JLabel lblContactNumber;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBirthday;

    private String ContactID;
    private String Name;
    private String ContactNumber;
    private String Company;
    private double Salary;
    private String Birthday;

    private JButton backtohome;
    private JButton Delete;
    private JButton Cancel;
    private JButton Next;
    public static Contact[] contactList;

    private String textValue;
    private ContactHomeForm contacthomeform;
    private SubUpdateForm subupdateform;

    UpdateContactForm() {
        setSize(800, 600);
        setTitle("Update contact Form");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel titlepannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        lblTitle = new JLabel("SEARCH CONTACT");
        lblTitle.setFont(new Font("", 1, 30));
        titlepannel.add(lblTitle);

        add("North", titlepannel);

        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel maingrid = new JPanel(new GridLayout(1, 1));

        JPanel searchpannel = new JPanel(new FlowLayout());

        textSearch = new JTextField(20);
        textSearch.setFont(new Font("", 1, 20));
        searchpannel.add(textSearch);

        Next = new JButton("Next");
        Next.setFont(new Font("", 1, 20));

        Search = new JButton("Search");
        Search.setFont(new Font("", 1, 20));
        Search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                textValue = textSearch.getText();
                contactList = ContactControler.Search(textValue);

                ContactID = contactList[0].getContactID();
                lblContactID.setText(ContactID);
                Name = contactList[0].getName();
                lblName.setText(Name);
                ContactNumber = contactList[0].getNumber();
                lblContactNumber.setText(ContactNumber);
                Company = contactList[0].getCompany();
                lblCompany.setText(Company);
                Salary = contactList[0].getSalary();
                lblSalary.setText("" + Salary);
                Birthday = contactList[0].getBirthday();
                lblBirthday.setText(Birthday);

            }
        });
        searchpannel.add(Search);
        searchpannel.add(Next);

        JPanel detailsmain = new JPanel(new GridLayout(7, 1, 10, 10));

        detailsmain.add(searchpannel);
        lblContactID = new JLabel("ContactId -> " + ContactID);
        lblContactID.setFont(new Font("", 1, 20));
        detailsmain.add(lblContactID);

        lblName = new JLabel();
        lblName.setFont(new Font("", 1, 20));
        detailsmain.add(lblName);

        lblContactNumber = new JLabel();
        lblContactNumber.setFont(new Font("", 1, 20));
        detailsmain.add(lblContactNumber);

        lblCompany = new JLabel();
        lblCompany.setFont(new Font("", 1, 20));
        detailsmain.add(lblCompany);

        lblSalary = new JLabel();
        lblSalary.setFont(new Font("", 1, 20));
        detailsmain.add(lblSalary);

        lblBirthday = new JLabel();
        lblBirthday.setFont(new Font("", 1, 20));
        detailsmain.add(lblBirthday);

        JPanel btnpanner = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel btnpanner1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        Delete = new JButton("Update");
        Delete.setFont(new Font("", 1, 20));
        Delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evnt) {
                if (subupdateform == null) {
                    subupdateform = new SubUpdateForm();
                }
                subupdateform.setVisible(true);
            }
        });
        Cancel = new JButton("Cancel");
        Cancel.setFont(new Font("", 1, 20));
        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent env) {
                UpdateContactForm.this.dispose();
            }
        });

        backtohome = new JButton("Back To Home Page");
        backtohome.setFont(new Font("", 1, 20));
        backtohome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (contacthomeform == null) {
                    contacthomeform = new ContactHomeForm();
                }
                contacthomeform.setVisible(true);
            }
        });

        JPanel gr = new JPanel(new GridLayout(2, 1));
        btnpanner1.add(Delete);
        btnpanner1.add(Cancel);
        btnpanner.add(backtohome);

        gr.add(btnpanner1);
        gr.add(btnpanner);

        // detailsmain.add(btnpanner);

        maingrid.add(detailsmain);

        main.add(maingrid);

        add("Center", main);
        add("South", gr);

    }
}

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
}

class ListClass extends JFrame {

    private JButton sortListName;
    private JButton sortListSalary;
    private JButton sortListBDay;
    private ViewContactNameForm viewcontactnameform;
    private ViewContactBdayForm viewcontactbdayform;
    private ViewContactSalaryForm viewsalarycontactform;

    ListClass() {
        setSize(400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        sortListName = new JButton("List By Name");
        sortListName.setFont(new Font("", 1, 20));
        sortListName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (viewcontactnameform == null) {
                    viewcontactnameform = new ViewContactNameForm();
                }
                viewcontactnameform.setVisible(true);
            }
        });
        add(sortListName);

        sortListSalary = new JButton("List By Salary");
        sortListSalary.setFont(new Font("", 1, 20));
        sortListSalary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (viewsalarycontactform == null) {
                    viewsalarycontactform = new ViewContactSalaryForm();
                }
                viewsalarycontactform.setVisible(true);
            }
        });
        add(sortListSalary);

        sortListBDay = new JButton("List By BDay");
        sortListBDay.setFont(new Font("", 1, 20));
        sortListBDay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (viewcontactbdayform == null) {
                    viewcontactbdayform = new ViewContactBdayForm();
                }
                viewcontactbdayform.setVisible(true);
            }
        });
        add(sortListBDay);
    }
}

class ContactHomeForm extends JFrame {
    static int count = 0;
    // public static ArrayList<Contact> contactList = new ArrayList<>();
    public static Contact arr;
    JLabel title1;
    JLabel title2;
    JLabel home;
    private AddContactForm addcontactform;
    // private ViewContactForm viewcontactform;
    private SearchContactForm searchcontactform;
    private UpdateContactForm updatecontactform;
    private ListClass listclass;

    private JButton btnAddContact;
    private JButton btnUpdateContact;
    private JButton btnSearchContact;
    private JButton btnDeleteContact;
    private JButton btnViewContact;
    private JButton btnExit;

    ContactHomeForm() {
        setSize(500, 300);
        setTitle("Contact management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        btnUpdateContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evnt) {
                if (updatecontactform == null) {
                    updatecontactform = new UpdateContactForm();
                }
                updatecontactform.setVisible(true);
            }
        });
        btnPannel.add(btnUpdateContact);

        btnSearchContact = new JButton("Search Contact");
        btnSearchContact.setFont(new Font("", 1, 25));
        btnSearchContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evn) {
                if (searchcontactform == null) {
                    searchcontactform = new SearchContactForm();
                }
                searchcontactform.setVisible(true);
            }
        });
        btnPannel.add(btnSearchContact);

        btnDeleteContact = new JButton("Delete Contact");
        btnDeleteContact.setFont(new Font("", 1, 25));
        btnPannel.add(btnDeleteContact);

        btnViewContact = new JButton("View Contact");
        btnViewContact.setFont(new Font("", 1, 25));
        btnViewContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (listclass == null) {
                    listclass = new ListClass();
                }
                listclass.setVisible(true);
            }
        });
        btnPannel.add(btnViewContact);

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("", 1, 25));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ContactHomeForm.this.dispose();
            }
        });
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
        // new SearchContactForm().setVisible(true);
        // new UpdateContactForm().setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.awt.event.*;

public class AddContactForm extends JFrame {
    Validation validate = new Validation();
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

    // static int count = 0;

    // ----validate contact number------

    AddContactForm(int count) {
        setSize(500, 300);
        setTitle("Add Contact Form");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
                if (validate.validateNumber(contactnumber)) {
                    if (!validate.isduplicate(contactnumber)) {
                        String company = textCompany.getText();
                        double salary = Double.parseDouble(textSalary.getText());
                        if (salary > 0) {
                            String birthday = textBirthday.getText();
                            if (validate.validateBDay(birthday)) {
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
        String newID = validate.displayID(count);
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

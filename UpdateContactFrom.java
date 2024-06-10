import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

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
        titlepannel.setBackground(Color.blue);

        lblTitle = new JLabel("UPDATE CONTACT");
        lblTitle.setForeground(Color.WHITE);
        ;
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
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);

        Search = new JButton("Search");
        Search.setBackground(Color.BLUE);
        Search.setForeground(Color.WHITE);
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
        detailsmain.setBackground(Color.ORANGE);
        detailsmain.setBorder(new EmptyBorder(10, 10, 10, 10));

        detailsmain.add(searchpannel);
        lblContactID = new JLabel(ContactID);
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
        Delete.setBackground(Color.BLUE);
        Delete.setForeground(Color.WHITE);
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
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent env) {
                UpdateContactForm.this.dispose();
            }
        });

        backtohome = new JButton("Back To Home Page");
        backtohome.setFont(new Font("", 1, 20));
        backtohome.setBackground(Color.BLUE);
        backtohome.setForeground(Color.WHITE);
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

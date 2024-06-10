import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        titlepannel.setBackground(Color.BLUE);

        lblTitle = new JLabel("SEARCH CONTACT");
        lblTitle.setForeground(Color.WHITE);
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
        Search.setFont(new Font("", 1, 20));
        Search.setBackground(Color.BLUE);
        Search.setForeground(Color.WHITE);
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
        detailsmain.setBackground(Color.ORANGE);

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

        backtohome = new JButton("Back To Home Page");
        backtohome.setFont(new Font("", 1, 20));
        backtohome.setBackground(Color.BLACK);
        backtohome.setForeground(Color.WHITE);
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

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.text.View;

import java.awt.event.*;
import java.util.*;

class ViewContactBdayForm extends JFrame {
    private JTable ContactTable;
    private DefaultTableModel dtm;

    private JLabel tiLabel;

    private JButton backhome;
    private JButton Cancel;

    ViewContactBdayForm() {
        setSize(400, 300);
        setTitle("View Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));

        tiLabel = new JLabel("List Contact By BDay");
        tiLabel.setHorizontalAlignment(JLabel.CENTER);
        tiLabel.setForeground(Color.WHITE);
        tiLabel.setFont(new Font("", 1, 30));
        title.add(tiLabel);
        title.setBackground(Color.BLUE);

        add("North", title);

        String[] columnName = { "Contact ID", "Name", "Contact Number", "Company", "Salary", "Birthday" };
        dtm = new DefaultTableModel(columnName, 0);

        ContactTable = new JTable(dtm);

        JScrollPane tablepane = new JScrollPane(ContactTable);
        tablepane.setBackground(Color.BLUE);

        add("Center", tablepane);

        JPanel btnPannel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        backhome = new JButton("Reload");
        backhome.setFont(new Font("", 1, 20));
        backhome.setBackground(Color.BLUE);
        backhome.setForeground(Color.WHITE);
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

        Cancel = new JButton("Cancel");
        Cancel.setFont(new Font("", 1, 20));
        Cancel.setBackground(Color.BLUE);
        Cancel.setForeground(Color.WHITE);
        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewContactBdayForm.this.dispose();
            }
        });
        btnPannel.add(Cancel);
        add("South", btnPannel);

    }

}

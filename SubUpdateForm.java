import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

class SubUpdateForm extends JFrame {
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    private JButton bt4;
    private JButton bt5;
    private JButton Cancel;

    private JLabel tilbl;

    private UpdateName updatename;
    private UpdateContactNumber updatecontactnumber;
    private UpdateCompany updatecontactcompany;
    private UpdateBDay updatebday;
    private UpdateSalary updatesalary;

    SubUpdateForm() {
        setSize(400, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 5, 5));
        // setBounds(new Rectangle(10, 10, 10, 10));
        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));

        title.setBackground(Color.BLUE);
        tilbl = new JLabel("Update Option Panel");
        tilbl.setFont(new Font("", 1, 20));
        title.add(tilbl);

        add(title);

        JPanel can = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        Cancel = new JButton("Cancel");
        Cancel.setFont(new Font("", 1, 20));
        Cancel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SubUpdateForm.this.dispose();
            }
        });
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);

        can.add(Cancel);

        bt1 = new JButton("Update Name");
        bt1.setFont(new Font("", 1, 20));
        bt1.setBackground(Color.ORANGE);
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
        bt2.setBackground(Color.ORANGE);
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
        bt3.setBackground(Color.ORANGE);
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
        bt4.setBackground(Color.ORANGE);
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
        bt5.setBackground(Color.ORANGE);
        bt5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (updatebday == null) {
                    updatebday = new UpdateBDay();
                }
                updatebday.setVisible(true);
            }
        });
        add(bt5);

        add(can);

        pack();

    }
}

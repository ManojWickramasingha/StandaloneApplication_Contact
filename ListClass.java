import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ListClass extends JFrame {

    private JButton sortListName;
    private JButton sortListSalary;
    private JButton sortListBDay;
    private ViewContactNameForm viewcontactnameform;
    private ViewContactBdayForm viewcontactbdayform;
    private ViewContactSalaryForm viewsalarycontactform;
    private JLabel listCata;

    private JButton Cancel;

    ListClass() {
        setSize(500, 400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel GRID = new JPanel(new GridLayout(3, 1, 10, 10));

        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
        title.setBackground(Color.BLUE);

        listCata = new JLabel("List Option Pannel");
        listCata.setFont(new Font("", 1, 20));
        listCata.setForeground(Color.WHITE);

        title.add(listCata);
        add("North", title);

        JPanel namePannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel salaryPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel birthdayPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        sortListName = new JButton("List By Name");
        sortListName.setFont(new Font("", 1, 20));
        sortListName.setBackground(Color.ORANGE);
        sortListName.setForeground(Color.WHITE);
        sortListName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (viewcontactnameform == null) {
                    viewcontactnameform = new ViewContactNameForm();
                }
                viewcontactnameform.setVisible(true);
            }
        });
        namePannel.add(sortListName);
        GRID.add(namePannel);

        sortListSalary = new JButton("List By Salary");
        sortListSalary.setFont(new Font("", 1, 20));
        sortListSalary.setBackground(Color.ORANGE);
        sortListSalary.setForeground(Color.WHITE);
        sortListSalary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (viewsalarycontactform == null) {
                    viewsalarycontactform = new ViewContactSalaryForm();
                }
                viewsalarycontactform.setVisible(true);
            }
        });
        salaryPannel.add(sortListSalary);
        GRID.add(salaryPannel);

        sortListBDay = new JButton("List By BDay");
        sortListBDay.setFont(new Font("", 1, 20));
        sortListBDay.setBackground(Color.ORANGE);
        sortListBDay.setForeground(Color.WHITE);
        sortListBDay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (viewcontactbdayform == null) {
                    viewcontactbdayform = new ViewContactBdayForm();
                }
                viewcontactbdayform.setVisible(true);
            }
        });
        birthdayPannel.add(sortListBDay);
        GRID.add(birthdayPannel);

        main.add(GRID);
        add("Center", main);

        JPanel down = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        Cancel = new JButton("Cancel");
        Cancel.setFont(new Font("", 1, 20));
        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ListClass.this.dispose();
            }
        });
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);

        down.add(Cancel);
        add("South", down);

    }
}

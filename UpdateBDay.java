import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UpdateBDay extends JFrame {

    Validation validate = new Validation();

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
                if (validate.validateBDay(newname)) {
                    ContactControler.update(UpdateContactForm.contactList, newname, "BDay");
                    JOptionPane.showMessageDialog(null, "Update BDay SuccessFully",
                            "Success...",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Note valide number it's future days",
                            "Not a Success...",
                            JOptionPane.INFORMATION_MESSAGE);
                }

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

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class GridBagLayoutDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3, 2));

        JButton bt1 = new JButton("Button 1");
        JButton bt2 = new JButton("Button 2");
        JButton bt3 = new JButton("Button 3");
        JButton bt4 = new JButton("Button 4");
        JButton bt5 = new JButton("Button 5");

        frame.add(bt1);
        frame.add(bt2);
        frame.add(bt3);
        frame.add(bt4);
        frame.add(bt5);

        frame.setVisible(true);
    }
}
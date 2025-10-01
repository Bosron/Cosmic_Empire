package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

public class WelcomeWindow extends javax.swing.JFrame {

    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel picture = new JLabel();
    private JLabel txtTitle = new JLabel();
    private JButton btnProceed = new JButton();

    public WelcomeWindow() {

        this.setBounds(0, 0, 914, 757);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setTitle("Cosmic empire database");
        layeredPane.setBounds(0, 0, 900, 720);

        picture.setBounds(0, 0, 900, 700);
        picture.setOpaque(true);
        picture.setIcon(new ImageIcon("src/images/cosmos.png"));

        txtTitle.setBounds(282, 155, 336, 70);
        txtTitle.setOpaque(false);
        txtTitle.setFont(new Font("Cosmic", Font.PLAIN, 50));
        txtTitle.setText("Welcome back");
        txtTitle.setForeground(new Color(130, 0, 130));

        btnProceed.setBounds(375, 360, 150, 50);
        btnProceed.setOpaque(false);
        btnProceed.setFont(new Font("Cosmic", Font.BOLD, 20));
        btnProceed.setText("Proceed");

        this.add(layeredPane);
        layeredPane.add(picture, Integer.valueOf(1));
        layeredPane.add(btnProceed, Integer.valueOf(2));
        layeredPane.add(txtTitle, Integer.valueOf(2));
        layeredPane.revalidate();
        this.revalidate();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        btnProceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new FilterDelete().setVisible(true);
                dispose();
            }
        });
    }
}

package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddEdit extends javax.swing.JFrame {

    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel picture = new JLabel();
    private JTextField txtName = new JTextField();
    private JTextField txtPopulation = new JTextField();
    private JComboBox<String> cmbGovernment = new JComboBox();
    private JComboBox<String> cmbPurpose = new JComboBox();
    private JComboBox<String> cmbType = new JComboBox();
    private JComboBox<String> cmbCelestialBody = new JComboBox();
    private JButton back = new JButton();
    private JButton confirm = new JButton();

    public AddEdit() {
        //polzvam konstruktora po podrazbirane za da otvorya prozoreca v regim dobavyane na zapis
        this.defaultConfiguration();

        txtName.setEnabled(true);
        confirm.setText("Add");

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (checkRegex(txtPopulation.getText()) && !txtName.getText().equals("") && !hasSQLInjection(txtName.getText()) && cmbGovernment.getSelectedIndex() != 0 && cmbPurpose.getSelectedIndex() != 0 && cmbType.getSelectedIndex() != 0 && cmbCelestialBody.getSelectedIndex() != 0) {
                    DBHandler.addColony(txtName.getText(), Integer.parseInt(txtPopulation.getText()), cmbGovernment.getSelectedIndex(), cmbPurpose.getSelectedIndex(), cmbType.getSelectedIndex(), cmbCelestialBody.getSelectedIndex());
                    new FilterDelete().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Enter valid values!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public AddEdit(String name) {
        //tozi konstruktor se vika za da se otvori prozoreca v regim redakciya na zapis, kato poleto name e imeto na redaktiraniya zapis
        this.defaultConfiguration();

        txtName.setText(name);
        txtName.setEditable(false);

        confirm.setText("Edit");

        LinkedList<String> colonies = DBHandler.selectColony(name, 0, 0, 0, 0, 0, 0);
        txtPopulation.setText(colonies.get(1));
        cmbGovernment.setSelectedIndex(Integer.parseInt(colonies.get(2)));
        cmbPurpose.setSelectedIndex(Integer.parseInt(colonies.get(3)));
        cmbType.setSelectedIndex(Integer.parseInt(colonies.get(4)));
        cmbCelestialBody.setSelectedIndex(Integer.parseInt(colonies.get(5)));

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (checkRegex(txtPopulation.getText())) {
                    DBHandler.updateColony(name, Integer.parseInt(txtPopulation.getText()), cmbGovernment.getSelectedIndex(), cmbPurpose.getSelectedIndex(), cmbType.getSelectedIndex(), cmbCelestialBody.getSelectedIndex());
                    new FilterDelete().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Enter valid population!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void defaultConfiguration() {
        //na praktika tova e konstruktor po podrazbirane
        this.setBounds(0, 0, 914, 757);
        this.setResizable(false);
        this.setTitle("Cosmic empire database");
        setLocationRelativeTo(null);

        layeredPane.setBounds(0, 0, 900, 720);

        picture.setBounds(0, 0, 900, 700);
        picture.setOpaque(true);
        picture.setIcon(new ImageIcon("src/images/cosmos.png"));

        txtName.setBounds(600, 100, 200, 40);
        txtName.setOpaque(true);

        txtPopulation.setBounds(600, 170, 200, 40);
        txtPopulation.setOpaque(true);

        cmbGovernment.setBounds(600, 240, 200, 40);
        cmbGovernment.setOpaque(true);

        cmbPurpose.setBounds(600, 310, 200, 40);
        cmbPurpose.setOpaque(true);

        cmbType.setBounds(600, 380, 200, 40);
        cmbType.setOpaque(true);

        cmbCelestialBody.setBounds(600, 450, 200, 40);
        cmbCelestialBody.setOpaque(true);

        confirm.setBounds(625, 600, 150, 75);
        confirm.setOpaque(true);

        back.setBounds(100, 600, 150, 75);
        back.setText("Back");
        back.setOpaque(true);

        cmbGovernment.addItem("Government");
        cmbPurpose.addItem("Purpose");
        cmbType.addItem("Type");
        cmbCelestialBody.addItem("Celestial body");

        for (int i = 1; i <= 7; i++) {
            if(!DBHandler.denormalize(1, i).equals("")){
                cmbGovernment.addItem(DBHandler.denormalize(1, i));
            }
            if(!DBHandler.denormalize(2, i).equals("")){
                cmbPurpose.addItem(DBHandler.denormalize(2, i));
            }
            if(!DBHandler.denormalize(3, i).equals("")){
                cmbType.addItem(DBHandler.denormalize(3, i));
            }
            if(!DBHandler.denormalize(4, i).equals("")){
                cmbCelestialBody.addItem(DBHandler.denormalize(4, i));
            }
        }

        this.add(layeredPane);
        layeredPane.add(picture, Integer.valueOf(1));
        layeredPane.add(txtName, Integer.valueOf(2));
        layeredPane.add(txtPopulation, Integer.valueOf(2));
        layeredPane.add(cmbGovernment, Integer.valueOf(2));
        layeredPane.add(cmbPurpose, Integer.valueOf(2));
        layeredPane.add(cmbType, Integer.valueOf(2));
        layeredPane.add(cmbCelestialBody, Integer.valueOf(2));
        layeredPane.add(confirm, Integer.valueOf(2));
        layeredPane.add(back, Integer.valueOf(2));
        layeredPane.revalidate();
        this.revalidate();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new FilterDelete().setVisible(true);
                dispose();
            }
        });
    }

    private boolean checkRegex(String expression) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    private boolean hasSQLInjection(String expression){
        Pattern p = Pattern.compile("(\"|-){1,}");
        Matcher m = p.matcher(expression);
        return m.find();
    }
}

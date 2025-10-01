package src;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FilterDelete extends javax.swing.JFrame {

    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel lblMoreThan = new JLabel();
    private JLabel lblLessThan = new JLabel();
    private JLabel picture = new JLabel();
    private JTextField txtMoreThan = new JTextField();
    private JTextField txtLessThan = new JTextField();
    private JButton filter = new JButton();
    private JButton print = new JButton();
    private JButton addColony = new JButton();
    private JButton editColony = new JButton();
    private JButton deleteColony = new JButton();
    private JTextArea txtList = new JTextArea();
    private JScrollPane scroll = new JScrollPane(txtList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JComboBox<String> cmbName = new JComboBox();
    private JComboBox<String> cmbPurpose = new JComboBox();
    private JComboBox<String> cmbType = new JComboBox();
    private JComboBox<String> cmbGovernment = new JComboBox();
    private JComboBox<String> cmbCelestialBody = new JComboBox();
    private LinkedList<Colony> colonies = new LinkedList();

    public FilterDelete() {
        //konstruktor po podrazbirane, tuk e izpolzvan po obiknoven nachin
        this.setBounds(0, 0, 914, 757);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setTitle("Cosmic empire database");
        layeredPane.setBounds(0, 0, 900, 720);

        picture.setBounds(0, 0, 900, 700);
        picture.setOpaque(true);
        picture.setIcon(new ImageIcon("src/images/cosmos.png"));

        lblMoreThan.setBounds(740, 455, 150, 30);
        lblMoreThan.setOpaque(false);
        lblMoreThan.setText("Population > ");
        lblMoreThan.setForeground(Color.white);

        lblLessThan.setBounds(740, 550, 150, 30);
        lblLessThan.setOpaque(false);
        lblLessThan.setText("Population < ");
        lblLessThan.setForeground(Color.white);

        txtMoreThan.setBounds(700, 485, 150, 30);
        txtMoreThan.setOpaque(true);

        txtLessThan.setBounds(700, 520, 150, 30);
        txtLessThan.setOpaque(true);

        filter.setBounds(725, 40, 100, 50);
        filter.setOpaque(true);
        filter.setText("FILTER");

        print.setBounds(60, 620, 150, 40);
        print.setText("Print");
        print.setOpaque(false);

        addColony.setBounds(270, 620, 150, 40);
        addColony.setOpaque(true);
        addColony.setText("Add colony");

        editColony.setBounds(480, 620, 150, 40);
        editColony.setOpaque(true);
        editColony.setText("Edit colony");

        deleteColony.setBounds(690, 620, 150, 40);
        deleteColony.setOpaque(true);
        deleteColony.setText("Delete colony");

        scroll.setBounds(50, 50, 625, 500);
        scroll.setOpaque(false);
        scroll.setBackground(Color.blue);
        scroll.getViewport().setOpaque(false);

        txtList.setBounds(50, 50, 625, 500);
        txtList.setOpaque(false);
        txtList.setEditable(false);
        txtList.setFont(new Font("Cosmic", Font.PLAIN, 15));
        txtList.setForeground(Color.white);

        cmbName.setBounds(700, 120, 150, 30);
        cmbName.setOpaque(true);
        cmbName.addItem("Colony name");

        cmbGovernment.setBounds(700, 190, 150, 30);
        cmbGovernment.setOpaque(true);
        cmbGovernment.addItem("Government");

        cmbPurpose.setBounds(700, 260, 150, 30);
        cmbPurpose.setOpaque(true);
        cmbPurpose.addItem("Purpose");

        cmbType.setBounds(700, 330, 150, 30);
        cmbType.setOpaque(true);
        cmbType.addItem("Type");

        cmbCelestialBody.setBounds(700, 400, 150, 30);
        cmbCelestialBody.setOpaque(true);
        cmbCelestialBody.addItem("Celestial body");

        for (int i = 1; i <= 7; i++) {
            if (!DBHandler.denormalize(1, i).equals("")) {
                cmbGovernment.addItem(DBHandler.denormalize(1, i));
            }
            if (!DBHandler.denormalize(2, i).equals("")) {
                cmbPurpose.addItem(DBHandler.denormalize(2, i));
            }
            if (!DBHandler.denormalize(3, i).equals("")) {
                cmbType.addItem(DBHandler.denormalize(3, i));
            }
            if (!DBHandler.denormalize(4, i).equals("")) {
                cmbCelestialBody.addItem(DBHandler.denormalize(4, i));
            }
        }

        executeFilter();
        refreshNames();

        this.add(layeredPane);
        layeredPane.add(picture, Integer.valueOf(1));
        layeredPane.add(lblMoreThan, Integer.valueOf(2));
        layeredPane.add(lblLessThan, Integer.valueOf(2));
        layeredPane.add(txtMoreThan, Integer.valueOf(2));
        layeredPane.add(txtLessThan, Integer.valueOf(2));
        layeredPane.add(filter, Integer.valueOf(2));
        layeredPane.add(print, Integer.valueOf(2));
        layeredPane.add(addColony, Integer.valueOf(2));
        layeredPane.add(editColony, Integer.valueOf(2));
        layeredPane.add(deleteColony, Integer.valueOf(2));
        layeredPane.add(scroll, Integer.valueOf(2));
        layeredPane.add(cmbName, Integer.valueOf(2));
        layeredPane.add(cmbGovernment, Integer.valueOf(2));
        layeredPane.add(cmbPurpose, Integer.valueOf(2));
        layeredPane.add(cmbType, Integer.valueOf(2));
        layeredPane.add(cmbCelestialBody, Integer.valueOf(2));

        layeredPane.revalidate();
        this.revalidate();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                executeFilter();
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    JTextField txtColony = new JTextField();
                    String feed = "";
                    for (Colony i : colonies) {
                        feed += i.toString() + "\n";
                    }
                    txtColony.setText(feed);
                    txtColony.print();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });

        addColony.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddEdit().setVisible(true);
                dispose();
            }
        });

        editColony.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cmbName.getSelectedIndex() != 0) {
                    new AddEdit(cmbName.getItemAt(cmbName.getSelectedIndex())).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Specify colony name!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteColony.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cmbName.getSelectedIndex() != 0) {
                    DBHandler.deleteColony(cmbName.getItemAt(cmbName.getSelectedIndex()));
                    refreshNames();
                    executeFilter();
                } else {
                    JOptionPane.showMessageDialog(null, "Specify colony name!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private static boolean checkRegex(String expression) {
        //proveryava dali izraza e chislo
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    private void executeFilter() {
        //metod, koito izpulnyava filtrite zadadeni chrez comboboksovete i txtfieldovete otdyasno
        boolean invalidInput = false;
        if (!checkRegex(txtMoreThan.getText()) && !txtMoreThan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter valid population!", "Alert", JOptionPane.WARNING_MESSAGE);
            invalidInput = true;
        } else if (txtMoreThan.getText().equals("")) {
            txtMoreThan.setText("0");
        }
        if (!checkRegex(txtLessThan.getText()) && !txtLessThan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter valid population!", "Alert", JOptionPane.WARNING_MESSAGE);
            invalidInput = true;
        } else if (txtLessThan.getText().equals("")) {
            txtLessThan.setText("0");
        }
        String colony_name = cmbName.getItemAt(cmbName.getSelectedIndex());
        if (cmbName.getSelectedIndex() == 0) {
            colony_name = "";
        }
        if (!invalidInput) {
            txtList.setText("");
            colonies.clear();
            LinkedList<String> colonyRetrieve = DBHandler.selectColony(colony_name, Integer.parseInt(txtMoreThan.getText()), Integer.parseInt(txtLessThan.getText()), cmbGovernment.getSelectedIndex(), cmbPurpose.getSelectedIndex(), cmbType.getSelectedIndex(), cmbCelestialBody.getSelectedIndex());
            for (int i = 0; i < colonyRetrieve.size() / 6; i++) {
                String txtListFeed = "  ";
                for (int j = 0; j < 6; j++) {
                    if (j < 2) {
                        txtListFeed += colonyRetrieve.get(i * 6 + j) + "\t";
                    } else if (j < 5) {
                        txtListFeed += DBHandler.denormalize(j - 1, Integer.parseInt(colonyRetrieve.get(i * 6 + j))) + "\t";
                    } else {
                        txtListFeed += DBHandler.denormalize(j - 1, Integer.parseInt(colonyRetrieve.get(i * 6 + 5)));
                    }
                }
                String[] colonyString = txtListFeed.split("\t");
                colonies.add(new Colony(txtListFeed));
                txtList.setText(txtList.getText() + "\n" + txtListFeed);
            }
        }
    }
    private void refreshNames(){
        //refrshva imenata v komboboksa
        cmbName.removeAllItems();
        cmbName.addItem("Colony name");
        LinkedList<String> allColonyList = DBHandler.selectColony("", 0, 0, 0, 0, 0, 0);
        for (int i = 0; i < allColonyList.size() / 6; i++) {
            cmbName.addItem(allColonyList.get(i * 6));
        }
    }
}

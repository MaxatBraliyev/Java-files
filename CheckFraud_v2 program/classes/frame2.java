package kz.ch.classes;

import kz.ch.model.dbDao;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.Vector;

public class frame2 extends JFrame {

    void showDialog() {
        JFrame frame2 = new JFrame("Групповые операции");
        JPanel widgetPanel2 = new JPanel(null);

        URL url = Main.class.getResource("/3.png");

        //MODEL Aggr1
        CheckFraudGui.model11 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            ;
        };

        //TABLE AGGR1 COLUMS ADD
        CheckFraudGui.model11.addColumn("Номер");
        CheckFraudGui.model11.addColumn("Направление");
        CheckFraudGui.model11.addColumn("Общее колл. звонков");
        CheckFraudGui.model11.addColumn("Уник. колл. звонков");
        CheckFraudGui.model11.addColumn("GPRS Трафик Mb");

        CheckFraudGui.t11 = new JTable(CheckFraudGui.model11);
        CheckFraudGui.t11.setRowSorter(new TableRowSorter<>(CheckFraudGui.model11)); //метод сортировки в таблице
        JScrollPane scroll11 = new JScrollPane(CheckFraudGui.t11);
        scroll11.setBounds(10, 100, 1700, 700); // x, y, width, height
        scroll11.setViewportView(CheckFraudGui.t11);

        CheckFraudGui.btnNewButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Frame3 frame3 = new Frame3();
                dbDao test4 = new dbDao();
                try {
                    test4.ClearNumber();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                CheckFraudGui.enterNumberText.setText("");
                CheckFraudGui.Label999.setText(String.valueOf(0));
                frame3.showDialog2();
                frame3.closeFrame3();
            }
        });

        CheckFraudGui.btnNewButton3.addActionListener(e -> {
            if (CheckFraudGui.Label999.getText().equals("")) {
                CheckFraudGui.btnNewButton4.setEnabled(false);
            } else {
                CheckFraudGui.btnNewButton4.setEnabled(true);
            }
        });

        CheckFraudGui.btnNewButton4.addActionListener(new ActionListener() {
            dbDao test3 = new dbDao();

            @Override
            public void actionPerformed(ActionEvent e) {
                CheckFraudGui.model11.setRowCount(0);
                try {
                    test3.Agg();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Vector<Vector<String>> nums345 = test3.getData345();
                for (Vector<String> num345 : nums345) {
                    CheckFraudGui.model11.addRow(num345);
                }
            }
        });

        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame2.setVisible(true);
        frame2.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        frame2.add(widgetPanel2);

        widgetPanel2.add(scroll11);
        widgetPanel2.add(CheckFraudGui.btnNewButton3);
        widgetPanel2.add(CheckFraudGui.btnNewButton4);
        widgetPanel2.add(CheckFraudGui.Label777);
        widgetPanel2.add(CheckFraudGui.Label999);
        widgetPanel2.setBackground(Color.PINK);
    }
    void closeFrame2()
    {
        closeFrame2();
    }
}

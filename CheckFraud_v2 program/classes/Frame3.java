package kz.ch.classes;

import kz.ch.model.dbDao;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

public class Frame3 extends JFrame {
     void showDialog2() {
        JFrame frame3 = new JFrame("Загрузка списка номеров");
        JPanel widgetPanel3  = new JPanel(null);

        URL url = Main.class.getResource("/3.png");

         CheckFraudGui.btnNewLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressAgg buttonPressAgg = new ButtonPressAgg();
                dbDao test66 = new dbDao();
                try {
                    buttonPressAgg.PressAgg();
                    test66.GetCount();
                    CheckFraudGui.Label999.setText(test66.getStatus_count());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame3.setVisible(false);
            }
        });

        frame3.setSize(600,600);
        frame3.setVisible(true);
        frame3.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        frame3.add(widgetPanel3);

        widgetPanel3.add(CheckFraudGui.btnNewLoad);
        widgetPanel3.add(CheckFraudGui.enterNumberText);
    }

    void closeFrame3() {
        closeFrame3();
    }
}

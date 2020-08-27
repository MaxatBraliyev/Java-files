package kz.ch.classes;

import kz.ch.model.dbDao;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

class NumberKeyListener extends KeyAdapter { // расширяем абстрактный класс KeyAdapter
    private dbDao test1 = new dbDao();
    String timeStamp = new SimpleDateFormat("MM_").format(Calendar.getInstance().getTime());
    String timeStamp2 = new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            CheckFraudGui.model.setRowCount(0);CheckFraudGui.model3.setRowCount(0);CheckFraudGui.model4.setRowCount(0);CheckFraudGui.model5.setRowCount(0);CheckFraudGui.model6.setRowCount(0);CheckFraudGui.model10.setRowCount(0);
            String s1 = "";
            String s2 = "";
            String s3 = "";
            String s4 = "";
            if (!(CheckFraudGui.searchNumberTextField.getText() == null) && CheckFraudGui.searchNumberTextField.getText().length() == 10) {
                s1 = CheckFraudGui.searchNumberTextField.getText();
            } else {
                JOptionPane.showMessageDialog(null, "Вы не верно ввели номер! Попытайтесь еще раз.");
            }
            try {
                s2 = (String) CheckFraudGui.box.getSelectedItem();s3 = (String) CheckFraudGui.box2.getSelectedItem();
                if (s2=="Январь"){s2="01_";} else if (s2=="Февраль"){ s2="02_";} else if (s2=="Март"){ s2="03_";} else if (s2=="Апрель"){ s2="04_";} else if (s2=="Май"){ s2="05_";}
                else if (s2=="Июнь"){ s2="06_";} else if (s2=="Июль"){ s2="07_";} else if (s2=="Август"){ s2="08_";} else if (s2=="Сентябрь"){ s2="09_";} else if (s2=="Октябрь"){ s2="10_";}
                else if (s2=="Ноябрь"){ s2="11_";} else if (s2=="Декабрь"){ s2="12_";} else if (s2=="Выберите месяц"&s3=="Выберите год"){ s2= timeStamp; s3=timeStamp2;}s4=s2+s3;test1.Call(s4);test1.CallD(s4);
                test1.SetNumber(s1);
                test1.Charges();
                test1.Detail();
                test1.Detail_Lac_Cell();
                test1.Payment();
                test1.Aggr();
                test1.Block();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            CheckFraudGui.Label17.setText(test1.getName());
            CheckFraudGui.Label2.setText(test1.GetSubs());
            CheckFraudGui.Label4.setText(test1.getAccount());
            CheckFraudGui.Label6.setText(test1.getClnt_id());
            CheckFraudGui.Label8.setText(test1.getCt_name());
            CheckFraudGui.Label10.setText(test1.getStatus());
            CheckFraudGui.Label12.setText(test1.getTrpl_name());
            CheckFraudGui.Label14.setText(test1.getBalance_$().toString());

            Vector<Vector<String>> nums6 = test1.getData6();
            for (Vector<String> num6 : nums6) {
                CheckFraudGui.model6.addRow(num6);
            }

            Vector<Vector<String>> nums10 = test1.getData10();
            for (Vector<String> num10 : nums10) {
                CheckFraudGui.model10.addRow(num10);
            }

            Vector<Vector<String>> nums5 = test1.getData5();
            for (Vector<String> num5 : nums5) {
                CheckFraudGui.model5.addRow(num5);
            }

            Vector<Vector<String>> nums4 = test1.getData4();
            for (Vector<String> num4 : nums4) {
                CheckFraudGui.model4.addRow(num4);
            }

            Vector<Vector<String>> nums3 = test1.getData3();
            for (Vector<String> num3 : nums3) {
                CheckFraudGui.model3.addRow(num3);
            }

            Vector<Vector<String>> nums = test1.getData();
            for (Vector<String> num : nums) {
                CheckFraudGui.model.addRow(num);
            }
        }
    }
}

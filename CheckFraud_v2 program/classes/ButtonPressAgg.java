package kz.ch.classes;

import kz.ch.model.dbDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static javax.swing.JOptionPane.showMessageDialog;

class ButtonPressAgg {

    private dbDao test2 = new dbDao();
    String timeStamp = new SimpleDateFormat("MM_").format(Calendar.getInstance().getTime());
    String timeStamp2 = new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());


    public void PressAgg() throws SQLException {

        String s2 = "";
        String s3 = "";
        String s4 = "";

            if ( null != test2.data_from_buffer)
            {
                test2.data_from_buffer = null;
                test2.ClearNumber();
            }

            else if (CheckFraudGui.enterNumberText.getText().equals(""))
            {
                showMessageDialog(null, "Вы не ввели номер!");
            }

            else if (CheckFraudGui.enterNumberText.getText().length()>100)
            {
                    showMessageDialog(null, "Вы не ввели более 100 номеров!");
            }

            else { test2.data_from_buffer = CheckFraudGui.enterNumberText.getText().split("\n"); }

            try {
                if (s2=="Январь"){s2="01_";} else if (s2=="Февраль"){ s2="02_";} else if (s2=="Март"){ s2="03_";} else if (s2=="Апрель"){ s2="04_";} else if (s2=="Май"){ s2="05_";}
                else if (s2=="Июнь"){ s2="06_";} else if (s2=="Июль"){ s2="07_";} else if (s2=="Август"){ s2="08_";} else if (s2=="Сентябрь"){ s2="09_";} else if (s2=="Октябрь"){ s2="10_";}
                else if (s2=="Ноябрь"){ s2="11_";} else if (s2=="Декабрь"){ s2="12_";} else if (s2=="Выберите месяц"&s3=="Выберите год"){ s2= timeStamp; s3=timeStamp2;}s4=s2+s3;test2.Call(s4);test2.CallD(s4);
                test2.addNumbers();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
}

package kz.ch.classes;

import kz.ch.model.dbDao;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import static javax.swing.JOptionPane.*;

public class CheckFraudGui {

    static JTextField searchNumberTextField; static JLabel Label1; static JTextField Label2; static JLabel Label3; static JTextField Label4; static JLabel Label5; static JTextField Label6;
    static JLabel Label7; static JTextField Label8; static JLabel Label9; static JTextField Label10; static JLabel Label11; static JTextField Label12; static JLabel Label13;
    static JTextField Label14; static JLabel Label15; static JLabel Label16; static JTextField Label17; static JLabel Label18; static JLabel Label19; static JLabel Label20;
    static JComboBox<String> box; static JComboBox<String> box2; static JLabel Label21; static JLabel Label22; static JLabel Label23; static JTextField Label24; static JLabel Label25;
    static JTextField Label26; static JButton btnNewButton; static JButton btnNewButton2; static String TITLE_message = "Окно сообщения";
    ButtonMouseListener ButtonMouseListener = new ButtonMouseListener(); static JTable t; static JTable t3; static JTable t4; static JTable t5; static JTable t6; static JTable t10;
    static DefaultTableModel model; static DefaultTableModel model3; static DefaultTableModel model4; static DefaultTableModel model5; static DefaultTableModel model6;
    static DefaultTableModel model10;JFrame frame = new JFrame("CheckFraudV2"); static JTable t11; static DefaultTableModel model11;
    static JButton btnNewLoad; static JTextArea enterNumberText; static JButton btnNewButton3; static JButton btnNewButton4; static JLabel Label999; static JLabel Label777;
    ButtonPressAgg ButtonPressAgg = new ButtonPressAgg();

    public void CheckFraudCreateGui(){

        JFrame frame = new JFrame("CheckFraudV2");
        JPanel widgetPanel  = new JPanel(null);
        Font font = new Font("Helvetica", Font.PLAIN, 16);
        URL url = Main.class.getResource("/3.png");

        //Create MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        fileMenu.addSeparator();
        JMenuItem infoItem = new JMenuItem("Info");
        infoItem.setFont(font);
        fileMenu.add(infoItem); //Add Info in menu
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        fileMenu.add(exitItem); //Add Exit in menu

        //Add Textfield & Label's
        searchNumberTextField = new JTextField(10);
        searchNumberTextField.addKeyListener(new NumberKeyListener());
        searchNumberTextField.setBounds(20, 10, 100, 21);

        //Add Textfield for enter
        enterNumberText = new JTextArea();
        enterNumberText.setBounds(40, 40, 500, 400);

        Label23 = new JLabel("Дата активации : "); /*Задаем цвет текста*/Label23.setForeground(Color.RED); /*Задаем Шрифт*/Label23.setFont(font);
        Label23.setBounds(20,240,300,20);
        Label24 = new JTextField();
        Label24.setBounds(160,240,350,20); /*Задаем цвет текста*/Label24.setForeground(Color.BLACK); /*Задаем Шрифт*/Label24.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));

        Label25 = new JLabel("Дата первого звонка : "); /*Задаем цвет текста*/Label25.setForeground(Color.RED); /*Задаем Шрифт*/Label25.setFont(font);
        Label25.setBounds(20,265,300,20);
        Label26 = new JTextField();
        Label26.setBounds(200,265,310,20); /*Задаем цвет текста*/Label26.setForeground(Color.BLACK); /*Задаем Шрифт*/Label26.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));

        Label22 = new JLabel("Lac&Cell");
        Label22.setBounds(230,710,110,20); /*Задаем цвет текста*/Label22.setForeground(Color.BLACK); /*Задаем Шрифт*/Label22.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label21 = new JLabel("Комментарий");
        Label21.setBounds(455,590,110,20); /*Задаем цвет текста*/Label21.setForeground(Color.BLACK); /*Задаем Шрифт*/Label21.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label20 = new JLabel("Агрегация");
        Label20.setBounds(730,10,110,20); /*Задаем цвет текста*/Label20.setForeground(Color.BLACK); /*Задаем Шрифт*/Label20.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label19 = new JLabel("Платежи абонента");
        Label19.setBounds(700,300,150,20); /*Задаем цвет текста*/Label19.setForeground(Color.BLACK); /*Задаем Шрифт*/Label19.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label18 = new JLabel("Списания с абонента");
        Label18.setBounds(200,300,180,20); /*Задаем цвет текста*/Label18.setForeground(Color.BLACK); /*Задаем Шрифт*/Label18.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label16 = new JLabel("Ф.И.О");/*Задаем цвет текста*/Label16.setForeground(Color.RED); /*Задаем Шрифт*/Label16.setFont(font);
        Label16.setBounds(20,40,300,20);
        Label17 = new JTextField();
        Label17.setBounds(160,40,350,20); /*Задаем цвет текста*/Label17.setForeground(Color.BLACK); /*Задаем Шрифт*/Label17.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label1 = new JLabel("Subs_id :"); /*Задаем цвет текста*/Label1.setForeground(Color.RED); /*Задаем Шрифт*/Label1.setFont(font);
        Label1.setBounds(20,65,300,20);
        Label2 = new JTextField();
        Label2.setBounds(160,65,350,20); /*Задаем цвет текста*/Label2.setForeground(Color.BLACK); /*Задаем Шрифт*/Label2.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label3 = new JLabel("Лицевой счет :"); /*Задаем цвет текста*/Label3.setForeground(Color.RED); /*Задаем Шрифт*/Label3.setFont(font);
        Label3.setBounds(20,90,300,20);
        Label4 = new JTextField();
        Label4.setBounds(160,90,350,20); /*Задаем цвет текста*/Label4.setForeground(Color.BLACK); /*Задаем Шрифт*/Label4.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label5 = new JLabel("Clnt_id :"); /*Задаем цвет текста*/Label5.setForeground(Color.RED); /*Задаем Шрифт*/Label5.setFont(font);
        Label5.setBounds(20,115,300,20);
        Label6 = new JTextField();
        Label6.setBounds(160,115,350,20); /*Задаем цвет текста*/Label6.setForeground(Color.BLACK); /*Задаем Шрифт*/Label6.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label7 = new JLabel("Тип клиента :"); /*Задаем цвет текста*/Label7.setForeground(Color.RED); /*Задаем Шрифт*/Label7.setFont(font);
        Label7.setBounds(20,140,300,20);
        Label8 = new JTextField();
        Label8.setBounds(160,140,350,20); /*Задаем цвет текста*/Label8.setForeground(Color.BLACK); /*Задаем Шрифт*/Label8.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label9 = new JLabel("Статус :"); /*Задаем цвет текста*/Label9.setForeground(Color.RED); /*Задаем Шрифт*/Label9.setFont(font);
        Label9.setBounds(20,165,300,20);
        Label10 = new JTextField();
        Label10.setBounds(160,165,350,20); /*Задаем цвет текста*/Label10.setForeground(Color.BLACK); /*Задаем Шрифт*/Label10.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label11 = new JLabel("Тарифный план :"); /*Задаем цвет текста*/Label11.setForeground(Color.RED); /*Задаем Шрифт*/Label11.setFont(font);
        Label11.setBounds(20,190,300,20);
        Label12 = new JTextField();
        Label12.setBounds(160,190,350,20); /*Задаем цвет текста*/Label12.setForeground(Color.BLACK); /*Задаем Шрифт*/Label12.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label13 = new JLabel("Текущий баланс :"); /*Задаем цвет текста*/Label13.setForeground(Color.RED); /*Задаем Шрифт*/Label13.setFont(font);
        Label13.setBounds(20,215,300,20);
        Label14 = new JTextField();
        Label14.setBounds(160,215,350,20); /*Задаем цвет текста*/Label14.setForeground(Color.BLACK); /*Задаем Шрифт*/Label14.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));
        Label15 = new JLabel(" Детализация");
        Label15.setBounds(1455,10,110,20); /*Задаем цвет текста*/Label15.setForeground(Color.BLACK); /*Задаем Шрифт*/Label15.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 15));

        Label777 = new JLabel("Колличество загруженных номеров: ");
        Label777.setBounds(250,20, 250,20);

        Label999 = new JLabel();
        //Label999.setText(String.valueOf(0));
        Label999.setBounds(480,20, 100,20);

        //Add Button search
        btnNewButton = new JButton("Найти");
        btnNewButton.setBounds(410,10, 80,20);
        btnNewButton.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 12));

        //Add Button NewWindow
        btnNewButton2 = new JButton("Групповые операции");
        btnNewButton2.setBounds(1700,10, 200,20);
        btnNewButton2.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 12));

        //Add Button NewWindow
        btnNewButton3 = new JButton("Загрузить номера");
        btnNewButton3.setBounds(20,20, 200,20);
        btnNewButton3.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 12));

        //Add Button NewWindow
        btnNewButton4 = new JButton("Вывести список");
        btnNewButton4.setBounds(20,60, 200,20);
        btnNewButton4.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 12));

        //Add Button NewWindow Load
        btnNewLoad = new JButton("OK");
        btnNewLoad.setBounds(200,500, 200,20);
        btnNewLoad.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 12));

//        //Add Button NewWindow Load_test
//        btnNewLoad1 = new JButton("Открыть директорию");
//        btnNewLoad1.setBounds(340,40, 200,20);
//        btnNewLoad1.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 12));

        //MODEL DETAILS
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };

        //MODEL CHARGES
        model3 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };

        //MODEL Payment
        model4 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };

        //MODEL Aggr
        model5 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };

        //MODEL Block
        model6 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };

        //MODEL Detail Lac&Cell
        model10 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };

        //TABLE DETAILS COLUMS ADD
        model.addColumn("Услуга");
        model.addColumn("Тип вызова");
        model.addColumn("Куда/Откуда ");
        model.addColumn("Дата");
        model.addColumn("Длительность");
        model.addColumn("Траффик");
        model.addColumn("Цена");
        //model.addColumn("Лак А");
        //model.addColumn("Селл А");

        //TABLE CHARGES COLUMS ADD
        model3.addColumn("Услуга");
        model3.addColumn("Тип начисления");
        model3.addColumn("Дата");
        model3.addColumn("Сумма");

        //TABLE PAYMENT COLUMS ADD
        model4.addColumn("Тип платежа");
        model4.addColumn("Дата");
        model4.addColumn("Сумма");

        //TABLE AGGR COLUMS ADD
        model5.addColumn("Направление");
        model5.addColumn("Кол-во зв.");
        model5.addColumn("Кол-во уник. зв.");

        //TABLE BLOCK COLUMS ADD
        model6.addColumn("Тип");
        model6.addColumn("Дата");
        model6.addColumn("Тема");
        model6.addColumn("Коммент");

        //TABLE Lac&Cell COLUMS ADD
        model10.addColumn("Lac");
        model10.addColumn("Cell");
        model10.addColumn("Колличество");

        //add mounth
        box = new JComboBox<>();
        box.setBounds(130,10,130,20);
        box.addItem("Выберите месяц");
        box.addItem("Январь");
        box.addItem("Февраль");
        box.addItem("Март");
        box.addItem("Апрель");
        box.addItem("Май");
        box.addItem("Июнь");
        box.addItem("Июль");
        box.addItem("Август");
        box.addItem("Сентябрь");
        box.addItem("Октябрь");
        box.addItem("Ноябрь");
        box.addItem("Декабрь");

        //add year
        box2 = new JComboBox<>();
        box2.setBounds(270,10,130,20);
        box2.addItem("Выберите год");
        box2.addItem("2016");
        box2.addItem("2017");
        box2.addItem("2018");
        box2.addItem("2019");
        box2.addItem("2020");

        //TABLE DETAILS
        t = new JTable(model);
        JScrollPane scroll = new JScrollPane(t);
        scroll.setBounds( 1030, 40, 880, 940 ); // x, y, width, height
        scroll.setViewportView(t);
        t.setRowSorter(new TableRowSorter<>(model)); //метод сортировки в таблице

        //TABLE CHARGES
        t3 = new JTable(model3);
        JScrollPane scroll3 = new JScrollPane(t3);
        scroll3.setBounds( 10, 330, 500, 250 ); // x, y, width, height
        scroll3.setViewportView(t3);

        //TABLE PAYMENT
        t4 = new JTable(model4);
        JScrollPane scroll4 = new JScrollPane(t4);
        t4.setRowSorter(new TableRowSorter<>(model4)); //метод сортировки в таблице
        scroll4.setBounds( 520, 330, 500, 250 ); // x, y, width, height
        scroll4.setViewportView(t4);

        t5 = new JTable(model5);
        JScrollPane scroll5 = new JScrollPane(t5);
        scroll5.setBounds( 520, 40, 500, 250 ); // x, y, width, height
        scroll5.setViewportView(t5);
        t5.updateUI();

        t6 = new JTable(model6);
        JScrollPane scroll6 = new JScrollPane(t6);
        scroll6.setBounds( 10, 620, 1000, 78 ); // x, y, width, height
        scroll6.setViewportView(t6);

        t10 = new JTable(model10);
        JScrollPane scroll10 = new JScrollPane(t10);
        scroll10.setBounds( 10, 740, 500, 240 ); // x, y, width, height
        scroll10.setViewportView(t10);

        //Add in the panel
        widgetPanel.add(searchNumberTextField); widgetPanel.add(Label1); widgetPanel.add(Label2); widgetPanel.add(Label3); widgetPanel.add(Label4); widgetPanel.add(Label5);
        widgetPanel.add(Label6); widgetPanel.add(Label7); widgetPanel.add(Label8); widgetPanel.add(Label9); widgetPanel.add(Label10); widgetPanel.add(Label11); widgetPanel.add(Label12);
        widgetPanel.add(Label13);widgetPanel.add(Label14); widgetPanel.add(Label15); widgetPanel.add(Label16); widgetPanel.add(Label17); widgetPanel.add(Label18); widgetPanel.add(Label19);
        widgetPanel.add(Label20); widgetPanel.add(Label21); widgetPanel.add(Label22); widgetPanel.add(Label23); widgetPanel.add(Label24); widgetPanel.add(Label25); widgetPanel.add(Label26);
        widgetPanel.add(btnNewButton); widgetPanel.add(btnNewButton2); widgetPanel.add(scroll); widgetPanel.add(scroll3); widgetPanel.add(scroll4); widgetPanel.add(scroll5); widgetPanel.add(scroll6);
        widgetPanel.add(scroll10); widgetPanel.add(box); widgetPanel.add(box2); widgetPanel.setBackground(Color.green);

        exitItem.addActionListener(e -> {if (showConfirmDialog(exitItem, "Вы уверены, что хотите выйти?") == YES_OPTION) System.exit(0);});
        exitItem.addActionListener(e ->System.exit(0));infoItem.addActionListener(e -> showMessageDialog(infoItem,"Тестовое приложение CheckfraudV2 ver.0.0.1"));
        infoItem.setToolTipText("Это менюшка про инфо!!!");

        //TO DO BUTTON
        btnNewButton.addMouseListener(ButtonMouseListener);
        btnNewButton2.addActionListener(e ->{btnNewButton4.setEnabled(false);});
        btnNewButton2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2 frame2 = new frame2();
                Label999.setText(String.valueOf(0));
                frame2.showDialog();
                frame2.closeFrame2();
            }
        });

        //Tools window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.add(widgetPanel);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
    }
}

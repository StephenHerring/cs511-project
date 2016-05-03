package view;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import implementation.*;
import data.Row;
import java.util.List;
import osql.osql;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Himel
 */
public class Position {
    public  String selectedMethod = "Hierarchical";
    osql osqlInterface = new osql();

    void set_selected_method(String m){
        selectedMethod = m;
    }
    public  String getSelectedMethod(){
        return  selectedMethod;
    }
    public List<Row> sql_query(String qstmt){
        qstmt = osqlInterface.convert(qstmt);
        String method = getSelectedMethod();
        List<Row> results = new ArrayList();
        System.out.println("Selected Method is " + method);
        System.out.println("Query is: " + qstmt);
        if (qstmt.toLowerCase().contains("select")){
            NaiveImplementation selectEngine = new NaiveImplementation();
            results = selectEngine.query(qstmt);
            return  results;
        }
        switch (method){
            case "Naive":{
                System.out.println("Naive is executing");

                NaiveImplementation naiveImplementation = new NaiveImplementation();
                if (qstmt.contains("insert")) {
                    Row element = new Row(84738746, "Ana", 2.3f, 984873);
                    naiveImplementation.insert(element);
                }
                else if (qstmt.contains("delete")){
                    Row element = new Row(84738746, "Ana", 2.3f, 984873);
                    naiveImplementation.delete(element);
                }
                System.out.println(results.size());
                System.out.println(results.get(0));

                break;
            }
            case "Hierarchical":{
                System.out.println("Hierarchical is executing");
                break;
            }
            case "counted BTree":{
                System.out.println("counted Btree is executing");
                break;
            }
            default:{
                break;
            }
        }
        return  results;
    }
    public Position() {
        JFrame window = new JFrame("OSQL");
        window.setLayout(new BorderLayout());

        JPanel firstPanel = new JPanel();
        Border firstPanelPadding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        firstPanel.setBorder(firstPanelPadding);
        firstPanel.setLayout(new BorderLayout());

        JLabel input = new JLabel("<html>Write your <font color=\"green\">OSQL</font> query<br><br></html>");
        input.setFont(new Font("Serif", Font.PLAIN, 32));

        final JTextArea textArea = new JTextArea(5, 15);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        textArea.setText("SELECT\nFROM\nWHERE");
        textArea.setFont(new Font("Serif", Font.BOLD, 26));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JButton button = new JButton("Execute");
        button.setPreferredSize(new Dimension(40, 40));

        button.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                String gotQuery = textArea.getText().toLowerCase();
                List<Row> results = sql_query(gotQuery);
                if (!gotQuery.contains("select"))
                {
                return;
                }
                JFrame tableFrame = new JFrame("OSQL Result");

                Vector<String> tableColumns = new Vector<>();
                tableColumns.addElement("<html><b>Trank</b></html>");
                tableColumns.addElement("<html><b>Name</b></html>");
                tableColumns.addElement("<html><b>GPA</b></html>");
                tableColumns.addElement("<html><b>Student ID</b></html>");

                Vector<Vector> tableData = new Vector<>();
                JTable table = new JTable(tableData, tableColumns);
                Vector<String> rowTable;

                for(int i=0; i<results.size(); i++)
                {
                    rowTable = new Vector<>();
                    rowTable.addElement(String.valueOf(results.get(i).getTRank()));
                    rowTable.addElement(String.valueOf(results.get(i).getName()));
                    rowTable.addElement(String.valueOf(results.get(i).getGPA()));
                    rowTable.addElement(String.valueOf(results.get(i).getStundent_id()));
                    tableData.addElement(rowTable);
                }
                JScrollPane scrollPane = new JScrollPane(table);
                tableFrame.setContentPane(scrollPane);
                tableFrame.pack();
                tableFrame.setLocationRelativeTo(null);
                tableFrame.setVisible(true);
                System.out.println("Done");
            }
        });
        String[] methodString= { "Naive", "Counted BTree", "Hierarchical" };
        JComboBox algoMethod = new JComboBox(methodString);
        algoMethod.setSelectedIndex(2);

        algoMethod.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                String selectedMethod = (String) combo.getSelectedItem();
                set_selected_method(selectedMethod);
            }
        });

        firstPanel.add(input, BorderLayout.NORTH);
        firstPanel.add(textArea, BorderLayout.CENTER);

        firstPanel.add(algoMethod, BorderLayout.NORTH);
        firstPanel.add(button, BorderLayout.SOUTH);


        window.add(firstPanel, BorderLayout.NORTH);

        window.setSize(500,400);
        window.setLocation(100,100);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /*public static void main(String[] args) {
        Position pos = new Position();
    }
    */
}

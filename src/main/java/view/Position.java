package view;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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

    Position() {

        JFrame window = new JFrame("OSQL");
        window.setLayout(new BorderLayout());

        JPanel firstPanel = new JPanel();
        Border firstPanelPadding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        firstPanel.setBorder(firstPanelPadding);
        firstPanel.setLayout(new BorderLayout());

        JLabel input = new JLabel("<html>Write your <font color=\"green\">OSQL</font> query<br><br></html>");
        input.setFont(new Font("Serif", Font.PLAIN, 32));

        JTextArea textArea = new JTextArea(5, 15);
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
                JFrame tableFrame = new JFrame("OSQL Result");

                Vector<String> tableColumns = new Vector<>();
                tableColumns.addElement("<html><b>Trank</b></html>");
                tableColumns.addElement("<html><b>Name</b></html>");;
                Vector<Vector> tableData = new Vector<>();
                JTable table = new JTable(tableData, tableColumns);
                Vector<String> rowTable;

                for(int i=0; i<5; i++)
                {
                    rowTable = new Vector<>();
                    rowTable.addElement(String.valueOf(i));
                    rowTable.addElement(String.valueOf(i+1));
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

        firstPanel.add(input, BorderLayout.NORTH);
        firstPanel.add(textArea, BorderLayout.CENTER);
        firstPanel.add(button, BorderLayout.SOUTH);



        window.add(firstPanel, BorderLayout.NORTH);

        window.setSize(500,400);
        window.setLocation(100,100);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        Position pos = new Position();
    }

}

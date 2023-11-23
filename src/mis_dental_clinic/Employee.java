/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis_dental_clinic;

import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class Employee extends JFrame implements ActionListener {
  
  
  private JButton ptInfoButton, doctorTableButton, logOutButton, appointmentButton;
  private JLabel label, label2, label1;
  private JTable table;
  
  
 
  public Employee(String fname, String email) {
    setFont(new Font("Courier New", Font.BOLD, 16));
    setTitle("MIS Dental Clinic");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1500, 700);
    setLayout(null);
    getContentPane();
     
    // Initialize and add the username label and field
    label1 = new JLabel("Welcome, ");
    label1.setFont(new Font("Courier New", Font.BOLD, 20));
    label1.setBounds(10, 10, 190, 30);
    add(label1); 
    // Initialize and add the username label and field
    label = new JLabel(fname);
    label.setFont(new Font("Courier New", Font.BOLD, 20));
    label.setBounds(220, 10, 200, 30);
    add(label);
    
    label2 = new JLabel(email);
    label2.setFont(new Font("Courier New", Font.BOLD, 20));
    label2.setBounds(500, 10, 300, 30);
    add(label2);
    
    table = new JTable();
    table.setName("MIS_Dental_DB");
    table.setFont(new Font("Courier New", Font.BOLD, 16));
    table.setBounds(50, 40, 1200, 500);
    add(table);

    doctorTableButton = new JButton("Doctor List");
    doctorTableButton.setBounds(250, 600, 200, 30);
    add(doctorTableButton);
    doctorTableButton.addActionListener(this);
    
    appointmentButton = new JButton("Enter Appointment");
    appointmentButton.setBounds(500, 600, 200, 30);
    add(appointmentButton);
    appointmentButton.addActionListener(this);
    
    ptInfoButton = new JButton("Enter Patient\n Info");
    ptInfoButton.setBounds(750, 600, 200, 30);
    add(ptInfoButton);
    ptInfoButton.addActionListener(this);

    
    // Initialize and add the login button
    logOutButton = new JButton("Log Out");
    logOutButton.setBounds(1000, 600, 200, 30);
    add(logOutButton);
    logOutButton.addActionListener(this);
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      
      
      if (e.getSource() == ptInfoButton) {
      Patient pt = new Patient(label.getText(), label2.getText());
      pt.setVisible(true);
      setVisible(false);
      }
    
      if (e.getSource() == appointmentButton) {
      Appointment apt = new Appointment(label.getText(), label2.getText());
      apt.setVisible(true);
      setVisible(false);
      }
    if (e.getSource() == doctorTableButton) {
        try {
        // Connect to the database and create a statement
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
        Statement stmt = conn.createStatement();

        // Execute the SELECT query and process the results
        ResultSet rs = stmt.executeQuery("SELECT doctors.*, users.first_name, users.last_name\n" +
"FROM dental_clinic.doctors doctors \n" +
"JOIN dental_clinic.users users ON doctors.user_id = users.user_id;");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        //columnNames = {"wer", "Eqq", "sss", "asss"};
        // Get the data and store it in a 2D array
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            data.add(row);
        }

        // Create the table model and set it on the table
        TableModel model = new DefaultTableModel(data.toArray(new Object[data.size()][]), columnNames);
        table.setModel(model);
        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error executing the query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }  
    if (e.getSource() == logOutButton) {
        Activity act = new Activity();
        act.logIn(label2.getText(), "log out");
      LoginPage loginPage = new LoginPage();
      loginPage.setVisible(true);
      setVisible(false);
    }
  }

  
    
}

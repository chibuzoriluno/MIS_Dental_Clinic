/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis_dental_clinic;

import java.awt.Font;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class Doctor extends JFrame implements ActionListener {
  
  
  private JButton logOutButton, docTableButton, patientTableButton, treatmentButton, viewTreatmentButton, viewAppointmentButton;
  private JLabel label, label2, label1;
  private JTable table;
  
  
  
 
  public Doctor(String fname, String email) {
    setFont(new Font("Courier New", Font.BOLD, 16));
    setTitle("MIS Dental Clinic");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1500, 700);
    setLayout(null);
    getContentPane();
    
    
    label1 = new JLabel("Welcome, Dr. ");
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
    table.setBounds(50, 50, 1200, 500);
    add(table);
    
    

    docTableButton = new JButton("View Doctors");
    docTableButton.setBounds(10, 600, 150, 30);
    add(docTableButton);
    docTableButton.addActionListener(this);
    
    patientTableButton = new JButton("View Patients");
    patientTableButton.setBounds(200, 600, 150, 30);
    add(patientTableButton);
    patientTableButton.addActionListener(this);
    
    viewAppointmentButton = new JButton("View Appointment");
    viewAppointmentButton.setBounds(390, 600, 150, 30);
    add(viewAppointmentButton);
    viewAppointmentButton.addActionListener(this);
    
    viewTreatmentButton = new JButton("View Treatment");
    viewTreatmentButton.setBounds(580, 600, 150, 30);
    add(viewTreatmentButton);
    viewTreatmentButton.addActionListener(this);
    
    // Initialize and add the login button
    logOutButton = new JButton("Log Out");
    logOutButton.setBounds(1000, 600, 150, 30);
    add(logOutButton);
    logOutButton.addActionListener(this);
    
    treatmentButton = new JButton("Enter Treatment");
    treatmentButton.setBounds(830, 600, 150, 30);
    add(treatmentButton);
    treatmentButton.addActionListener(this);
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    
    if (e.getSource() == treatmentButton) {
        String a = label.getText();
        String b = label2.getText();
        label.setText("");
        label2.setText("");
        Treatment trmt = new Treatment(a, b);
        
        trmt.setVisible(true);
        setVisible(false);
        
    }
     if (e.getSource()== viewTreatmentButton) {
        
        try {
        // Connect to the database and create a statement
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
        Statement stmt = conn.createStatement();

        // Execute the SELECT query and process the results
        ResultSet rs = stmt.executeQuery("SELECT patient.pt_id, patient.first_name, patient.last_name, trmt.diagnosis, trmt.trmt_procedure, trmt.medication \n" +
"FROM dental_clinic.treatment trmt \n" +
"JOIN dental_clinic.patient patient ON trmt.patient_id = patient.patient_id WHERE trmt.doctor_email = '"+label2.getText()+"' ORDER BY trmt.trmt_time DESC;");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        
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
   
    
    if (e.getSource()== viewAppointmentButton) {
        
        try {
        // Connect to the database and create a statement
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
        Statement stmt = conn.createStatement();

        // Execute the SELECT query and process the results
        ResultSet rs = stmt.executeQuery("SELECT apt.apt_id, apt.this_day, apt.this_time, patient.first_name, patient.last_name\n" +
"FROM dental_clinic.appointment apt \n" +
"JOIN dental_clinic.patient patient ON apt.patient_id = patient.patient_id WHERE apt.doctor_email = '"+label2.getText()+"' ORDER BY apt.this_day DESC;");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        
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
    if (e.getSource() == docTableButton) {
    
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
    
    if (e.getSource() == patientTableButton) {
    
    try {
        table.clearSelection();
        // Connect to the database and create a statement
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
        Statement stmt = conn.createStatement();

        // Execute the SELECT query and process the results
        ResultSet rs = stmt.executeQuery("SELECT * FROM dental_clinic.patient;");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }

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
    if (e.getSource()== viewTreatmentButton) {
        
        try {
        // Connect to the database and create a statement
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
        Statement stmt = conn.createStatement();

        // Execute the SELECT query and process the results
        ResultSet rs = stmt.executeQuery("SELECT trmt.patient_id, patient.first_name, patient.last_name, trmt.trmt_id, trmt.diagnosis, trmt.trmt_procedure, trmt.trmt_time \n" +
"FROM dental_clinic.treatment trmt \n" +
"JOIN dental_clinic.patient patient ON trmt.patient_id = patient.patient_id WHERE trmt.doctor_email = '"+label2.getText()+"' ORDER BY trmt.trmt_time DESC;");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        
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
  }

  
    
}

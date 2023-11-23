/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis_dental_clinic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class Nurse extends JFrame implements ActionListener {
  
  
  private JButton logOutButton, viewVisitButton, enterVisitButton, viewPatientButton;
  private JLabel label, label2, label1;
  private JTable table;
  
  
 
  public Nurse(String fname, String email) throws ClassNotFoundException, SQLException {
    setFont(new Font("Courier New", Font.BOLD, 16));
    setTitle("MIS Dental Clinic");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1500, 700);
    setLayout(null);
    getContentPane();
     
    // Initialize and add the username label and field
    label1 = new JLabel("Welcome, Ns. ");
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
    
    //Label_id lbd = new Label_id();
    //lbd.id(email);
    /*label3 = new JLabel(lbd.id(email));
    label3.setFont(new Font("Courier New", Font.BOLD, 20));
    label3.setBounds(10, 600, 200, 30);
    add(label3);*/
    
    
    
    
    table = new JTable();
    table.setName("MIS_Dental_DB");
    table.setFont(new Font("Courier New", Font.BOLD, 16));
    table.setBounds(50, 40, 1200, 500);
    add(table);

    viewPatientButton = new JButton("View Patient");
    viewPatientButton.setBounds(10, 600, 200, 30);
    add(viewPatientButton);
    viewPatientButton.addActionListener(this);
    
    viewVisitButton = new JButton("my Visit");
    viewVisitButton.setBounds(250, 600, 200, 30);
    add(viewVisitButton);
    viewVisitButton.addActionListener(this);
    
    enterVisitButton = new JButton("Enter Visit");
    enterVisitButton.setBounds(490, 600, 200, 30);
    add(enterVisitButton);
    enterVisitButton.addActionListener(this);
    
    // Initialize and add the login button
    logOutButton = new JButton("Log Out");
    logOutButton.setBounds(750, 600, 200, 30);
    add(logOutButton);
    logOutButton.addActionListener(this);
    
  }
  
  

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == logOutButton) {
        Activity act = new Activity();
        act.logIn(label2.getText(), "log out");
      LoginPage loginPage = new LoginPage();
      loginPage.setVisible(true);
      setVisible(false);
    }
    
    if (e.getSource()== viewPatientButton) {
        try {
        // Connect to the database and create a statement
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
        Statement stmt = conn.createStatement();

        // Execute the SELECT query and process the results
        ResultSet rs = stmt.executeQuery("SELECT pt.patient_id, pt.first_name, pt.last_name, pt.address, pt.phone, trmt.diagnosis, trmt.medication  FROM dental_clinic.patient pt JOIN dental_clinic.treatment trmt ON pt.patient_id = trmt.patient_id ORDER BY trmt.trmt_time DESC;");
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
       
        
        
        // Get the data and store it in a 2D a
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error executing the query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
        
    }
    
    if (e.getSource()== viewVisitButton) {
        
        try {
        // Connect to the database and create a statement
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
        Statement stmt = conn.createStatement();

        // Execute the SELECT query and process the results
        ResultSet rs = stmt.executeQuery("SELECT * FROM dental_clinic.nrs_visits_patient WHERE nurse_email = '"+label2.getText()+"' ORDER BY visited_at DESC;");
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
    
    if (e.getSource() == enterVisitButton) {
        
        Nurse_Visit nrs_visit = new Nurse_Visit(label.getText(), label2.getText());
        nrs_visit.setVisible(true);
        setVisible(false);
    }
   
  }

  
    
}


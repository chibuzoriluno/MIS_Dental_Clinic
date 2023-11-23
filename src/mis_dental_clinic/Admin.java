/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis_dental_clinic;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Admin extends JFrame implements ActionListener {
  
  
  private JButton logOutButton, queryButton, insertButton;
  private JLabel label, label2, label1, tablesLabel, tablesLabel1, tablesLabel2, tablesLabel3, tablesLabel4, tablesLabel5, tablesLabel6, tablesLabel7, tablesLabel8, tablesLabel9, tablesLabel10, tablesLabel11, stmtLabel;
  private JTable table;
  private JTextArea stmtArea;
  
 
  public Admin(String fname, String email) {
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
    table.setBounds(200, 40, 1100, 300);
    add(table);
    
    tablesLabel = new JLabel("DB: dental_clinic");
    tablesLabel.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel.setBounds(10, 40, 200, 30);
    add(tablesLabel);
    
    tablesLabel1 = new JLabel("tables = " );
    tablesLabel1.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel1.setBounds(10, 72, 200, 30);
    add(tablesLabel1);
    
    tablesLabel2 = new JLabel("users " );
    tablesLabel2.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel2.setBounds(10, 104, 200, 30);
    add(tablesLabel2);
    
    tablesLabel3 = new JLabel("docotrs " );
    tablesLabel3.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel3.setBounds(10, 136, 200, 30);
    add(tablesLabel3);
    
    tablesLabel4 = new JLabel("nurses " );
    tablesLabel4.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel4.setBounds(10, 168, 200, 30);
    add(tablesLabel4);
    
    tablesLabel5 = new JLabel("employee " );
    tablesLabel5.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel5.setBounds(10, 200, 200, 30);
    add(tablesLabel5);
    
    tablesLabel6 = new JLabel("admin " );
    tablesLabel6.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel6.setBounds(10, 232, 200, 30);
    add(tablesLabel6);
    
    tablesLabel7 = new JLabel("patient " );
    tablesLabel7.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel7.setBounds(10, 264, 200, 30);
    add(tablesLabel7);
    
    tablesLabel8 = new JLabel("appointment " );
    tablesLabel8.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel8.setBounds(10, 296, 200, 30);
    add(tablesLabel8);
    
    tablesLabel9 = new JLabel("treatment " );
    tablesLabel9.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel9.setBounds(10, 328, 200, 30);
    add(tablesLabel9);
    
    tablesLabel10 = new JLabel("nrs_visits_patient " );
    tablesLabel10.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel10.setBounds(10, 360, 200, 30);
    add(tablesLabel10);
    
    tablesLabel11 = new JLabel("activity_report " );
    tablesLabel11.setFont(new Font("Courier New", Font.BOLD, 16));
    tablesLabel11.setBounds(10, 392, 200, 30);
    add(tablesLabel11);

    stmtLabel = new JLabel("SQL Statement: ");
    stmtLabel.setFont(new Font("Courier New", Font.BOLD, 16));
    stmtLabel.setBounds(10, 560, 200, 30);
    add(stmtLabel);

    stmtArea = new JTextArea();
    stmtArea.setFont(new Font("Courier New", Font.BOLD, 16));
    stmtArea.setBounds(300, 390, 900, 200);
    add(stmtArea);
    
    // Initialize and add the login button
    logOutButton = new JButton("Log Out");
    logOutButton.setBounds(1000, 600, 200, 30);
    add(logOutButton);
    logOutButton.addActionListener(this);
    
    queryButton = new JButton("Query");
    queryButton.setBounds(200, 600, 200, 30);
    add(queryButton);
    queryButton.addActionListener(this);
    
    insertButton = new JButton("Insert/Update");
    insertButton.setBounds(450, 600, 250, 30);
    add(insertButton);
    insertButton.addActionListener(this);
    
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
    
    if (e.getSource() == queryButton) {
        
        table.clearSelection();
        String query = stmtArea.getText();

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");

            // Execute the query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get the result metadata
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            
             DefaultTableModel model = new DefaultTableModel();

            // Create the table columns
            for (int i = 1; i <= numColumns; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            // Populate the table with the query result
            while (rs.next()) {
                Object[] row = new Object[numColumns];
                for (int i = 1; i <= numColumns; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model for the result table
            table.setModel(model);

            // Close the connection
                // Close the connection
            con.close();
            
        } catch (ClassNotFoundException | SQLException ee) {
            ee.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wrong Query! Did You Want to Insert??? Check Table(s) name(s)");
        }
    }
    
    if (e.getSource() == insertButton) {
        
         String query = stmtArea.getText();

        try {
            // Connect to the database
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");

            // Execute the insert statement
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(query);

            // Show a message dialog indicating success
            JOptionPane.showMessageDialog(null, "Insert/Update Complete");

            // Close the connection
            con.close();
            stmtArea.setText("");
             } catch (HeadlessException | SQLException eee) {
            eee.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wrong Insert/Update Statement! Did you mean to Query??? Check Your Table Names");
        }
    }
  }

  
    
}


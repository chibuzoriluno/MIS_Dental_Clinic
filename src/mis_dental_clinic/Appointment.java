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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class Appointment extends JFrame implements ActionListener{
    private JLabel label, label1, label2, employeeEmailLabel, doctorEmailLabel, patientIdLabel, thisDayLabel, thisTimeLabel, formatDayLabel, formatTimeLabel;
    private JTextField employeeEmailField, doctorEmailField, patientIdField, thisDayField, thisTimeField;
    private JButton submitButton, backButton;
    
    public Appointment(String fname, String email) {
        
        setFont(new Font("Courier New", Font.BOLD, 16));
        setTitle("Appointment Entry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1000, 700);
        setLayout(null);
        getContentPane();
        
    
    // Initialize and add the username label and field
    label = new JLabel(fname);
    label.setFont(new Font("Courier New", Font.BOLD, 20));
    label.setBounds(220, 10, 200, 30);
    add(label);
    
    label2 = new JLabel(email);
    label2.setFont(new Font("Courier New", Font.BOLD, 20));
    label2.setBounds(500, 10, 300, 30);
    add(label2);
        
    employeeEmailLabel = new JLabel("Employee Email:");
    employeeEmailLabel.setBounds(50, 50, 200, 30);
    employeeEmailLabel.setFont(new Font("Courier New", Font.BOLD, 20));
    add(employeeEmailLabel);
    
    employeeEmailField = new JTextField(20);
    employeeEmailField.setBounds(300, 50, 300, 30);
    employeeEmailField.setFont(new Font("Courier New", Font.BOLD, 20));
    add(employeeEmailField);
    employeeEmailField.setText(email);
    employeeEmailField.setEditable(false);
    
    doctorEmailLabel = new JLabel("Doctor Email:");
    doctorEmailLabel.setBounds(50, 100, 200, 25);
    doctorEmailLabel.setFont(new Font("Courier New", Font.BOLD, 20));
    add(doctorEmailLabel);
    
    doctorEmailField = new JTextField(20);
    doctorEmailField.setBounds(300, 100, 300, 30);
    doctorEmailField.setFont(new Font("Courier New", Font.BOLD, 20));
    add(doctorEmailField);
    
    patientIdLabel = new JLabel("Patient ID:");
    patientIdLabel.setBounds(50, 150, 200, 25);
    patientIdLabel.setFont(new Font("Courier New", Font.BOLD, 20));
    add(patientIdLabel);
    
    patientIdField = new JTextField(5);
    patientIdField.setBounds(300, 150, 50, 30);
    patientIdField.setFont(new Font("Courier New", Font.BOLD, 20));
    add(patientIdField);
    
    thisDayLabel = new JLabel( "This Day:");
    thisDayLabel.setBounds(50, 200, 200, 25);
    thisDayLabel.setFont(new Font("Courier New", Font.BOLD, 20));
    add(thisDayLabel);
    
    formatDayLabel = new JLabel("YYYY-MM-DD");
    formatDayLabel.setBounds(300, 250, 200, 25);
    formatDayLabel.setFont(new Font("Courier New", Font.BOLD, 20));
    add(formatDayLabel);
    
    thisDayField = new JTextField(20);
    thisDayField.setBounds(300, 200, 200, 30);
    thisDayField.setFont(new Font("Courier New", Font.BOLD, 20));
    add(thisDayField);
    
    thisTimeLabel = new JLabel("This Time:");
    thisTimeLabel.setBounds(50, 300, 200, 25);
    thisTimeLabel.setFont(new Font("Courier New", Font.BOLD, 20));
    add(thisTimeLabel);
    
    formatTimeLabel = new JLabel("HH:MM:SS ");
    formatTimeLabel.setBounds(300, 350, 200, 25);
    formatTimeLabel.setFont(new Font("Courier New", Font.BOLD, 20));
    add(formatTimeLabel);
    
    thisTimeField = new JTextField(20);
    thisTimeField.setBounds(300, 300, 200, 30);
    thisTimeField.setFont(new Font("Courier New", Font.BOLD, 20));
    add(thisTimeField);
    
    submitButton = new JButton("Insert");
    submitButton.setBounds(300, 400, 200, 30);
    submitButton.setFont(new Font("Courier New", Font.BOLD, 20));
    submitButton.addActionListener(this);
    add(submitButton);
    
    backButton = new JButton("Back");
    backButton.setBounds(400, 600, 200, 30);
    add(backButton);
    backButton.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
      
      if (e.getSource() == backButton) {
            Employee emp = new Employee(label.getText(), label2.getText());
            emp.setVisible(true);
            setVisible(false);
        }
      
  if (e.getSource() == submitButton) {
    String employeeEmail = employeeEmailField.getText();
    String doctorEmail = doctorEmailField.getText();
    int patientId = Integer.parseInt(patientIdField.getText());
    String thisDay = thisDayField.getText();
    String thisTime = thisTimeField.getText();

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
       String sql = "INSERT INTO appointment (employee_email, doctor_email, patient_id, this_day, this_time) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setString(1, employeeEmail);
      statement.setString(2, doctorEmail);
      statement.setInt(3, patientId);
      statement.setString(4, thisDay);
      statement.setString(5, thisTime);
      int rowsInserted = statement.executeUpdate();
      
      if (rowsInserted > 0) {
    JOptionPane.showMessageDialog(rootPane, "Insert Sucessful");
    
    doctorEmailField.setText("");
    patientIdField.setText("");
    thisDayField.setText("");
    thisTimeField.setText("");
    
    
}else{JOptionPane.showMessageDialog(rootPane, "Some Fields Are Empty!");}
// Close the connection
statement.close();
conn.close();
      
    } catch (Exception ex) {
      ex.printStackTrace();

    }
  }
  }  
}

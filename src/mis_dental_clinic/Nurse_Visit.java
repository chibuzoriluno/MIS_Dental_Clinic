package mis_dental_clinic;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class Nurse_Visit extends JFrame implements ActionListener{
    
    private JLabel label, label2, nurseEmailJLabel, ptIdLabel;
    private JTextField nurseEmailField, ptIdField;
    private JButton submitButton, backButton;
    
    public Nurse_Visit(String fname, String email){
        
        setFont(new Font("Courier New", Font.BOLD, 16));
        setTitle("Patient Entry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLayout(null);
        getContentPane();
        
        label = new JLabel(fname);
        label.setFont(new Font("Courier New", Font.BOLD, 20));
        label.setBounds(10, 10, 200, 30);
        add(label);
    
        label2 = new JLabel(email);
        label2.setFont(new Font("Courier New", Font.BOLD, 20));
        label2.setBounds(250, 10, 300, 30);
        add(label2);
        
        nurseEmailJLabel = new JLabel("Nurse Email: ");
        nurseEmailJLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        nurseEmailJLabel.setBounds(10, 60, 200, 30);
        add(nurseEmailJLabel);
        
        nurseEmailField = new JTextField();
        nurseEmailField.setFont(new Font("Courier New", Font.BOLD, 20));
        nurseEmailField.setBounds(250, 60, 300, 30);
        add(nurseEmailField);
        nurseEmailField.setText(email);
        nurseEmailField.setEditable(false);
        
        ptIdLabel = new JLabel("Patient ID: ");
        ptIdLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        ptIdLabel.setBounds(10, 110, 200, 30);
        add(ptIdLabel);
        
        ptIdField = new JTextField();
        ptIdField.setFont(new Font("Courier New", Font.BOLD, 20));
        ptIdField.setBounds(250, 110, 100, 30);
        add(ptIdField);
        
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Courier New", Font.BOLD, 20));
        submitButton.setBounds(300, 110, 200, 30);
        add(submitButton);
        submitButton.addActionListener(this);
        
        backButton = new JButton("Back");
        backButton.setBounds(350, 200, 200, 30);
        backButton.setFont(new Font("Courier New", Font.BOLD, 20));
        add(backButton);
        backButton.addActionListener(this);
        
    }
    
     @Override
      public void actionPerformed(ActionEvent e) {
          
          if (e.getSource() == submitButton) {
        // Get the input from the text fields
        String nurseEmail = nurseEmailField.getText();
        int patientId = Integer.parseInt(ptIdField.getText());
        

        // Insert the row into the table
        try {
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
          String sql = "INSERT INTO dental_clinic.nrs_visits_patient (nurse_email, patient_id) VALUES (?, ?)";
          PreparedStatement statement = conn.prepareStatement(sql);
          statement.setString(1, nurseEmail);
          statement.setInt(2, patientId);
          
          statement.executeUpdate();
          conn.close();
          JOptionPane.showMessageDialog(null, "Visit Entry Successful");
        } catch (SQLException ex) {
          ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Patient ID You Entered Does Not Exist");
        }
          }
          
          if (e.getSource() == backButton) {
              
              try {
                  Nurse nurses = new Nurse(label.getText(), label2.getText());
                  nurses.setVisible(true);
                  setVisible(false);
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(Nurse_Visit.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(Nurse_Visit.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          
      }
          
}

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class Treatment extends JFrame implements ActionListener{
    
    private JLabel label, label1, label2, doctorEmailLabel, patientIdLabel, diagnosisLabel, trmtProcedureLabel, medicationLabel;
    private JTextField doctorEmailField, patientIdField;
    private JTextArea diagnosisArea, trmtProcedureArea, medicationArea;
    private JButton submitButton, backButton;

    public Treatment(String fname, String email) {
        setFont(new Font("Courier New", Font.BOLD, 16));
        setTitle("Treatment Entry");
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

        // Initialize and add the input components
        doctorEmailLabel = new JLabel("Doctor Email:");
        doctorEmailLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        doctorEmailLabel.setBounds(10, 50, 150, 30);
        add(doctorEmailLabel);

        doctorEmailField = new JTextField();
        doctorEmailField.setFont(new Font("Courier New", Font.BOLD, 16));
        doctorEmailField.setBounds(160, 50, 300, 30);
        add(doctorEmailField);
        doctorEmailField.setText(email);
        doctorEmailField.setEditable(false);

        patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setFont(new Font("Courier New", Font.BOLD, 16));
                patientIdLabel.setBounds(10, 100, 150, 30);
        add(patientIdLabel);

        patientIdField = new JTextField();
        patientIdField.setFont(new Font("Courier New", Font.BOLD, 16));
        patientIdField.setBounds(160, 100, 300, 30);
        add(patientIdField);

        diagnosisLabel = new JLabel("Diagnosis:");
        diagnosisLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        diagnosisLabel.setBounds(10, 140, 150, 30);
        add(diagnosisLabel);

        diagnosisArea = new JTextArea();
        diagnosisArea.setFont(new Font("Courier New", Font.BOLD, 16));
        diagnosisArea.setBounds(160, 140, 400, 100);
        add(diagnosisArea);

        trmtProcedureLabel = new JLabel("Procedure:");
        trmtProcedureLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        trmtProcedureLabel.setBounds(10, 250, 150, 30);
        add(trmtProcedureLabel);

        trmtProcedureArea = new JTextArea();
        trmtProcedureArea.setFont(new Font("Courier New", Font.BOLD, 16));
        trmtProcedureArea.setBounds(160, 250, 400, 100);
        add(trmtProcedureArea);

        medicationLabel = new JLabel("Medication:");
        medicationLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        medicationLabel.setBounds(10, 360, 150, 30);
        add(medicationLabel);

        medicationArea = new JTextArea();
        medicationArea.setFont(new Font("Courier New", Font.BOLD, 16));
        medicationArea.setBounds(160, 360, 400, 100);
        add(medicationArea);

        // Initialize and add the submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Courier New", Font.BOLD, 16));
        submitButton.setBounds(200, 470, 100, 30);
        add(submitButton);
        submitButton.addActionListener(this);
        
        backButton = new JButton("Back");
        backButton.setBounds(400, 600, 200, 30);
        add(backButton);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == backButton) {
            String a = label.getText();
            String b = label2.getText();
            label.setText("");
            label2.setText("");
            Doctor doc = new Doctor(a, b);
            
            doc.setVisible(true);
            setVisible(false);
        }
        
        if (e.getSource() == submitButton) {
            // Get the data from the input components
            String doctorEmail = doctorEmailField.getText();
            int patientId = Integer.parseInt(patientIdField.getText());
            String diagnosis = diagnosisArea.getText();
            String trmtProcedure = trmtProcedureArea.getText();
            String medication = medicationArea.getText();

            // Insert the data into the table using a JDBC connection
            try {
                // Connect to the database and create a statement
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO treatment (doctor_email, patient_id, diagnosis, trmt_procedure, medication) VALUES (?, ?, ?, ?, ?)");

                // Set the values for the prepared statement
                stmt.setString(1, doctorEmail);
                stmt.setInt(2, patientId);
                stmt.setString(3, diagnosis);
                stmt.setString(4, trmtProcedure);
                stmt.setString(5, medication);

                // Execute the statement
                int rowsInserted = stmt.executeUpdate();
      
      if (rowsInserted > 0) {
    JOptionPane.showMessageDialog(rootPane, "Insert Sucessful");
                
            
            patientIdField.setText("");
            diagnosisArea.setText("");
            trmtProcedureArea.setText("");
            medicationArea.setText("");
      }else{JOptionPane.showMessageDialog(rootPane, "Some Fields Are Empty!");}
            } catch (Exception ex) { ex.printStackTrace();
            }
        }

    }
}

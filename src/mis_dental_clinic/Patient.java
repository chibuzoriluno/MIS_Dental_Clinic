package mis_dental_clinic;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
public class Patient extends JFrame implements ActionListener{
    
    
    private JLabel label, label1, label2, patientIDLabel, employeeEmailLabel, firstNameLabel, lastNameLabel, addressLabel, cityLabel, phoneLabel;
private JTextField patientIDField, employeeEmailField, firstNameField, lastNameField, addressField, cityField, phoneField;
private JButton insertButton, backButton;

    
    public Patient(String fname, String email) {
        
        setFont(new Font("Courier New", Font.BOLD, 16));
        setTitle("Patient Entry");
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


        employeeEmailLabel = new JLabel("Employee Email: ");
        employeeEmailLabel.setBounds(50, 90, 200, 30);
        employeeEmailLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(employeeEmailLabel);
        employeeEmailField = new JTextField(email);
         employeeEmailField.setBounds(270, 90, 250, 30);
        employeeEmailField.setFont(new Font("Courier New", Font.BOLD, 20));
        add(employeeEmailField);
        employeeEmailField.setEditable(false);

        firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setBounds(50, 130, 200, 30);
        firstNameLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(firstNameLabel);
        firstNameField = new JTextField(20);
        firstNameField.setBounds(270, 130, 280, 30);
        firstNameField.setFont(new Font("Courier New", Font.BOLD, 20));
        add(firstNameField);

        lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(50, 170, 200, 30);
        lastNameLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(lastNameLabel);
        lastNameField = new JTextField(20);
        lastNameField.setBounds(270, 170, 280, 30);
        lastNameField.setFont(new Font("Courier New", Font.BOLD, 20));
        add(lastNameField);

        addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(50,210, 200, 30);
        addressLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(addressLabel);
        addressField = new JTextField(30);
        addressField.setBounds(270, 210, 280, 30);
        addressField.setFont(new Font("Courier New", Font.BOLD, 20));
        add(addressField);

        cityLabel = new JLabel("City: ");
        cityLabel.setBounds(50, 250, 200, 30);
        cityLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(cityLabel);
        cityField = new JTextField(20);
        cityField.setBounds(270, 250, 280, 30);
        cityField.setFont(new Font("Courier New", Font.BOLD, 20));
        add(cityField);

        phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBounds(50, 290, 200, 30);
        phoneLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(phoneLabel);
        phoneField = new JTextField(15);
        phoneField.setBounds(270, 290, 280, 30);
        phoneField.setFont(new Font("Courier New", Font.BOLD, 20));
        add(phoneField);

        insertButton = new JButton("Submit");
        insertButton.setBounds(250, 340, 200, 50);
        insertButton.setFont(new Font("Courier New", Font.BOLD, 20));
        add(insertButton);
        insertButton.addActionListener(this);
        
        backButton = new JButton("Back");
        backButton.setBounds(400, 600, 200, 30);
        backButton.setFont(new Font("Courier New", Font.BOLD, 20));
        add(backButton);
        backButton.addActionListener(this);

       
    }

   

    public void actionPerformed(ActionEvent e){
        
        
            if (e.getSource() == backButton) {
            Employee emp = new Employee(label.getText(), label2.getText());
            emp.setVisible(true);
            setVisible(false);
        }    
        
            if (e.getSource() == insertButton){
                String eEF = employeeEmailField.getText();
                String fNF = firstNameField.getText();
                String lNF = lastNameField.getText();
                String aF = addressField.getText();
                String cF = cityField.getText();
                String pF = phoneField.getText();
                
           try{
// Connect to a database
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dental_clinic", "root", "root");

// Create a prepared statement
String insertSQL = "INSERT INTO patient (employee_email, first_name, last_name, address, city, phone) VALUES (?, ?, ?, ?, ?, ?)";
PreparedStatement statement = connection.prepareStatement(insertSQL);

// Set the values for the placeholders
statement.setString(1, eEF);
statement.setString(2, fNF);
statement.setString(3, lNF);
statement.setString(4, aF);
statement.setString(5, cF);
statement.setString(6, pF);

// Execute the insert statement
int rowsInserted = statement.executeUpdate();
if (rowsInserted > 0) {
    JOptionPane.showMessageDialog(rootPane, "Insert Sucessful");
    
    firstNameField.setText("");
    lastNameField.setText("");
    addressField.setText("");
    cityField.setText("");
    phoneField.setText("");
    
}else{JOptionPane.showMessageDialog(rootPane, "Some Fields Are Empty!");}
// Close the connection
statement.close();
connection.close();

            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

    
    

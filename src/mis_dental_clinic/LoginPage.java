/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mis_dental_clinic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class LoginPage extends JFrame implements ActionListener{
    
    
  private JPanel panel;
  private JLabel logoLabel;
  private JLabel usernameLabel, sectionLabel;
  private JTextField usernameField;
  private JLabel passwordLabel;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JComboBox<String> sectionBox;
  

  
  public LoginPage() {
    // Set the title and layout of the frame
   
    setFont(new Font("Courier New", Font.BOLD, 16));
    setTitle("MIS Dental Clinic");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(50, 50, 800, 500);
    setBackground(Color.blue);
    setForeground(Color.red);
    setLayout(null);
    getContentPane();
    
    /*panel.setBounds(50, 50, 400, 200);
    getContentPane().add(panel);
    panel.setLayout(null);*/

    // Initialize and add the logo label
    logoLabel = new JLabel(new ImageIcon("MISDentalClinicLogo.png"));
    logoLabel.setBounds(50, 50, 100, 50);
    add(logoLabel);

    // Initialize and add the username label and field
    usernameLabel = new JLabel("Username: ");
    usernameField = new JTextField(20);
    usernameLabel.setBounds(155, 130, 80, 30);
    usernameField.setBounds(230, 130, 200, 30);
    add(usernameLabel);
    add(usernameField);

    // Initialize and add the password label and field
    passwordLabel = new JLabel("Password: ");
    passwordField = new JPasswordField(20);
    passwordLabel.setBounds(155, 180, 80, 30);
    passwordField.setBounds(230, 180, 200, 30);
    add(passwordLabel);
    add(passwordField);
    
    sectionLabel = new JLabel("Section: ");
    sectionLabel.setBounds(155, 230, 80, 30);
    add(sectionLabel);
    
    String[] options = { "select", "doctor", "nurse", "employee", "admin" };
    sectionBox = new JComboBox<>(options);
    sectionBox.setBounds(230, 230, 200, 30);
      add(sectionBox);

    // Initialize and add the login button
    loginButton = new JButton("Log In");
    loginButton.setBounds(230, 280, 200, 30);
    add(loginButton);
    loginButton.addActionListener(this);

    }

  
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton) {
      String usr = usernameField.getText();
      String pwd = passwordField.getText();
      String sect = sectionBox.getSelectedItem().toString();
      if(usr.equals("")|| pwd.equals("")||sect.equals("select")){
          JOptionPane.showMessageDialog(rootPane, "Some Fields Are Empty", "Error", 1);
      }else{
          try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
              
              PreparedStatement pst;
              
              ResultSet rs;
              
              pst = con.prepareStatement("select * from dental_clinic.users where email=? and passwd=?");
              
              pst.setString(1, usr);
              pst.setString(2, pwd);
              rs = pst.executeQuery();
              
              if(rs.next()){
                  String s1 =rs.getString("section");
                  String s2 = rs.getString("first_name");
                  
                  if(sect.equalsIgnoreCase("doctor") && s1.equalsIgnoreCase("doctor")){
                      Activity act = new Activity();
                      act.logIn(usr, "log in");
                      Doctor doc = new Doctor(s2, usr);
                      doc.setVisible(true);
                      setVisible(false);
                  }
                  if(sect.equalsIgnoreCase("nurse") && s1.equalsIgnoreCase("nurse")){
                      Activity act = new Activity();
                      act.logIn(usr, "log in");
                      Nurse nrs = new Nurse(s2, usr);
                      nrs.setVisible(true);
                      setVisible(false);
                  }
                  if(sect.equalsIgnoreCase("employee") && s1.equalsIgnoreCase("employee")){
                      Activity act = new Activity();
                      act.logIn(usr, "log in");
                      Employee emp = new Employee(s2, usr);
                      emp.setVisible(true);
                      setVisible(false);
                  }
                  if(sect.equalsIgnoreCase("admin") && s1.equalsIgnoreCase("admin")){
                      Activity act = new Activity();
                      act.logIn(usr, "log in");
                      Admin adm = new Admin(s2, usr);
                      adm.setVisible(true);
                      setVisible(false);
                  } 
                  else{
                      JOptionPane.showMessageDialog(rootPane, "Check Section Selection", "Login Error", 1);
                  
              }
                  }else{
                      JOptionPane.showMessageDialog(rootPane, "Username or Password Error", "Login Error", 1);
                  
              }
          } catch (Exception ee) {
          }
      }
    }
  }
  
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
      
    }

    
}

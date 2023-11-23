/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis_dental_clinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Activity {
    
    public void logIn(String email, String activity) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            PreparedStatement pst1 = conn.prepareStatement("INSERT INTO dental_clinic.activity_report (email, activity) VALUES (?, ?)");
            pst1.setString(1, email);
            pst1.setString(2, activity);
            int rs1 = pst1.executeUpdate();
            System.out.println(rs1 + " rows affected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author israe
 */
public class ConectorBD {

    public void test() {
        String url = "jdbc:mysql://localhost:3306/cafe";
        String username = "root";
        String password = "pA970_1lINZ6wLk3OOr`8psZ~";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultset = statement.executeQuery("SELECT * FROM bebidas;");
            System.out.println("id --- nombre --- tipo --- precio");
            while (resultset.next()) {
                System.out.println(resultset.getInt(1) + ", " + resultset.getString(2) + ", " + resultset.getString(3) + ", " + resultset.getString(4));
            }

            connection.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

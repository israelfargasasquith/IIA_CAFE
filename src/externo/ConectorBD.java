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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author israe
 */
public class ConectorBD {

    private final String url = "jdbc:mysql://localhost:3306/cafe";
    private final String username = "root";
    private final String password = "pA970_1lINZ6wLk3OOr`8psZ~";
    private PuertoSolicitud puertoSolicitud;

    public void setPuertoSolicitud(PuertoSolicitud puertoSolicitud) {
        this.puertoSolicitud = puertoSolicitud;
    }

    public void consulta(Document query, int idDocument, int idSegment, int nSegments) {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        Document document = dBuilder.newDocument();

        Element rootElement = document.createElement("SQL_Response");
        document.appendChild(rootElement);

        Element priceElement = document.createElement("price");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String que = query.getElementsByTagName("SQL").item(0).getTextContent();

            ResultSet resultset = statement.executeQuery(que);

//            System.out.println("id --- nombre --- tipo --- precio"); debug
//            while (resultset.next()) {
//                System.out.println(resultset.getInt(1) + ", " + resultset.getString(2) + ", " + resultset.getString(3) + ", " + resultset.getString(4));
//            }
//            while (resultset.next()) {
//                System.out.println(resultset.getInt(1));
//            }
            while (resultset.next()) {
                priceElement.setTextContent(String.valueOf(resultset.getInt(1)));
            }
            rootElement.appendChild(priceElement);
            connection.close();
            puertoSolicitud.escribeSolicitud(document, idDocument, idSegment, nSegments);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komponenty;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

/**
 *
 * @author linuxlite
 */
public class StudentBean implements Serializable {

    private String nazwisko;
    private String pesel;
    private double srednia;
    private String email;

    private Connection conn;

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public double getSrednia() {
        return srednia;
    }

    public void setSrednia(double srednia) {
        this.srednia = srednia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentBean() {
    }

    public void open() throws SQLException, ClassNotFoundException {
        if (this.conn != null) {
            return;
        }
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/studenci", "test", "test");
    }
    
    public Result getAll() throws SQLException, ClassNotFoundException {
        try {
            open();
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM studenci ");
            return ResultSupport.toResult(results);
        } finally {
            close();
        }
    }

    private void close() throws SQLException {
        if (conn == null) {
            return;
        }
        conn.close();
        conn = null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komponenty;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private String srednia;
    private String email;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

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

    public String getSrednia() {
        return srednia;
    }

    public void setSrednia(String srednia) {
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
    
    public Result getBest() throws SQLException, ClassNotFoundException {
        try {
            open();
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery(
                    "SELECT * FROM studenci WHERE cast(srednia as decimal(9,2)) > 4.5"
            );
            return ResultSupport.toResult(results);
        } finally {
            close();
        }
    }
    
    public void add() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        int localResult;
        this.setResult("");
        try {
            open();
            ps = conn.prepareStatement("INSERT INTO STUDENCI VALUES (?, ?, ?, ?)");
            ps.setString(1, getPesel());
            ps.setString(2, getNazwisko());
            ps.setString(3, getEmail());
            ps.setString(4, getSrednia());
        } finally {
            localResult = 0;
        }
        
        try {
            localResult = ps.executeUpdate();
            this.setResult("Student dodany do bazy!");
        } catch (SQLException e) {
            this.setResult("Nie dodano studenta, wystapil blad!");
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

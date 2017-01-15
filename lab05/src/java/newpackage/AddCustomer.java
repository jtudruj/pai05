/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author linuxlite
 */
public class AddCustomer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCustomer</title>");            
            out.println("</head>");
            out.println("<body>");
            
            try { 
                Class.forName("org.apache.derby.jdbc.ClientDriver"); 
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
                    
                String a = request.getParameter("customer_id");
                String a0 = request.getParameter("discount_code");
                String a1 = request.getParameter("zip");
                String a2 = request.getParameter("name");
                String a3 = request.getParameter("addressline1");
                String a4 = request.getParameter("addressline2");
                String a5 = request.getParameter("city");
                String a6 = request.getParameter("state");
                String a7 = request.getParameter("phone");
                String a8 = request.getParameter("fax");
                String a9 = request.getParameter("email");
                    
                String query = "INSERT INTO APP.CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, NAME, ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, PHONE, FAX, EMAIL) " + 
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, a);
                pstmt.setString(2, a0);
                pstmt.setString(3, a1);
                pstmt.setString(4, a2);
                pstmt.setString(5, a3);
                pstmt.setString(6, a4);
                pstmt.setString(7, a5);
                pstmt.setString(8, a6);
                pstmt.setString(9, a7);
                pstmt.setString(10, a8);
                pstmt.setString(11, a9);
                pstmt.executeUpdate();
                out.println("<h1>Dodano rekord!</h1>");
                out.println("<a href=\"http://localhost:8080/lab03/\">Wroc do listy</a>");
            } catch (SQLException e) {
               out.println("<h1>Connection error</h1>");
               out.println(e);
            }             
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

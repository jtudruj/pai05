/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import newpackage.CustomerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(urlPatterns = {"/Customer"})
public class Customer extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Customer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Customer at " + request.getContextPath() + "</h1>");
            
        try { 
            Class.forName("org.apache.derby.jdbc.ClientDriver"); 
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM APP.CUSTOMER"); 
//
            List<CustomerBean> list = new ArrayList<CustomerBean>(); 
            while (rs.next()) {   
                int count = rs.getRow();
                CustomerBean customer = new CustomerBean();  
                customer.setCustomer_id(rs.getInt("CUSTOMER_ID"));
                customer.setDoscount_code(rs.getString("DISCOUNT_CODE"));
                customer.setZip(rs.getString("ZIP"));
                customer.setName(rs.getString("NAME"));
                customer.setAddressline1(rs.getString("ADDRESSLINE1"));
                customer.setAddressline2(rs.getString("ADDRESSLINE2"));
                customer.setCity(rs.getString("CITY"));
                customer.setState(rs.getString("STATE"));
                customer.setPhone(rs.getString("PHONE"));
                customer.setFax(rs.getString("FAX"));
                customer.setEmail(rs.getString("EMAIL"));
                customer.setCredit_limit(rs.getString("CREDIT_LIMIT"));
                list.add(customer); 
            } 
            request.getSession().setAttribute("list", list);
            response.sendRedirect("lista.jsp");
            
            
  
//            out.println("<h2>Pobrany z bazy " + rs.count + " rekordów</h2>");
            
            //numer wiersza   //pobierz dane korzystajac z numeru kolumny w bazie   out.println(rs.getInt(1));    out.println(rs.getString(2));   //lub korzystajac z nazwy kolumny   out.println(rs.getString("COLUMN-NAME")); } 
        } catch (SQLException e) {
           out.println("<h1>Connection error</h1>");
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
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
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

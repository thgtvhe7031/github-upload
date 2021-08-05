/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author PC
 */
public class ControllerCustomer extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnect dbconn = new DBConnect();
        DAOCustomer dao = new DAOCustomer(dbconn);
        
        try (PrintWriter out = response.getWriter()) {
            
            String service = request.getParameter("service");
            if(service==null) {
                service = "displayAll";
            }
            
            if(service.equalsIgnoreCase("displayAll")) {
                
                String sql = "select * from Customer";
                ResultSet rs = dbconn.getData(sql);
                //if(rs==null) {System.out.println("rs null");} else {System.out.println("rs not null");};
                
                ArrayList<Customer> arr = dao.getAllCustomer();
                //if(arr==null) {System.out.println("arr null");} else {System.out.println("arr not null");}; 
                
                String titleTable = "List of customer";
                
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                
                RequestDispatcher dis = request.getRequestDispatcher("/customer.jsp");
                
                dis.forward(request,response);
            }
            
            if(service.equalsIgnoreCase("addCustomer")) {
                dispath(request, response, "/addCustomer.jsp");
            }
            
            if(service.equalsIgnoreCase("insertdb")) {
                String name = request.getParameter("cname");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String username = request.getParameter("username");
                    String password = request.getParameter("pass");

                    //String arr[] = request.getParameterValues("paraArray")
                    //validate: empty, length ..
                    // userName is unique
                    String sql = "select * from Customer where username='" + username + "'";
                    ResultSet rs = dbconn.getData(sql);
                    try {
                        if (rs.next()) {
                            out.print("username is existed, pls use other");
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerCustomer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Customer cus = new Customer(name, phone, address, username, password);

                    int n = dao.addCustomer(cus);
                    
                    response.sendRedirect("ControllerCustomer");
            }
            
            if(service.equals("update")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                
                String sql = "select * from Customer where cid="+cid;
                ResultSet rs= dbconn.getData(sql);
                
                if(rs.next()) {
                    Customer cus = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                    request.setAttribute("cus", cus);
                    dispath(request, response, "/updateCustomer.jsp");
                }
            }
            
            if(service.equalsIgnoreCase("updated")) {
                String cid = request.getParameter("cid");
                
                String cname = request.getParameter("cname");
                String phone = request.getParameter("cphone");
                String address = request.getParameter("caddress");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                int status = Integer.parseInt(request.getParameter("status"));

                Customer cus = new Customer(cname, phone, address, username, password, status);

                dao.updateCustomer(cid, cus);
                
                response.sendRedirect("ControllerCustomer");
            }
            
            if(service.equalsIgnoreCase("delete")) {
                
                String cid = request.getParameter("cid");
                dao.deleteCustomer(cid);
                response.sendRedirect("ControllerCustomer");
                
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerCustomer</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerCustomer at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void dispath(HttpServletRequest request, HttpServletResponse response, String url) {
        RequestDispatcher dis= request.getRequestDispatcher(url);
        try {
            dis.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

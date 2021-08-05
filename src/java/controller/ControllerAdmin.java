/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOAdmin;
import model.DBConnect;

/**
 *
 * @author PC
 */
public class ControllerAdmin extends HttpServlet {

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
        DAOAdmin dao = new DAOAdmin(dbconn);
        
        try (PrintWriter out = response.getWriter()) {
            
            String service = request.getParameter("service");
            if(service==null) {
                service = "displayAll";
            }
            
            if(service.equalsIgnoreCase("displayAll")) {
                
                String sql = "select * from admin";              
                ResultSet rs = dbconn.getData(sql);
                
                ArrayList<Admin> arr = dao.getAllAdmin();
                
                String title = "List of Admin";
                
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", title);
                
                RequestDispatcher dis = request.getRequestDispatcher("/admin.jsp");
                
                dis.forward(request, response);
            }
            
            if(service.equalsIgnoreCase("addAdmin")) {
                dispath(request, response, "/addAdmin.jsp");
            }
            
            if(service.equalsIgnoreCase("insertdb")) {
                String name = request.getParameter("username");
                String pass = request.getParameter("pass");

                Admin ad = new Admin(name, pass);
                int n = dao.addNewAdmin(ad);
                
                response.sendRedirect("ControllerAdmin");
            }
            
            if(service.equalsIgnoreCase("update")) {
                String adminid = request.getParameter("adminid");
                
                String sql = "select * from admin where adminID="+adminid;
                ResultSet rs = dbconn.getData(sql);
                
                if(rs.next()) {
                    Admin ad = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3));
                    request.setAttribute("ad", ad);
                    dispath(request, response, "updateAdmin.jsp");
                }
            }
            
            if(service.equalsIgnoreCase("updated")) {
                String adminid = request.getParameter("adminid");
                
                String oldPassword = request.getParameter("oldPassword");
                
                // get the admin from database
                String sql = "select * from admin where adminid="+adminid;
                
                ResultSet rs = dbconn.getData(sql);
                
                // if user input true old password
                if(rs.next()) {
                    if(rs.getString("password").equals(oldPassword)) {
                        // update new password                   
                        Admin ad = new Admin(request.getParameter("username"), request.getParameter("newPassword"));
                        dao.updateAdmin(adminid, ad);
                
                        response.sendRedirect("ControllerAdmin");
                    } else { //  return to admin list                   
                        response.sendRedirect("ControllerAdmin?service=displayAll");               
                    }
                }
                
            }
            
            if(service.equalsIgnoreCase("delete")) {
                String adminid = request.getParameter("adminid");
                
                dao.deleteAdmin(adminid);
                
                response.sendRedirect("ControllerAdmin");
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerAdmin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerAdmin at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
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

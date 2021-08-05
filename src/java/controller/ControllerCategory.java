/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
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
import model.DAOCategory;
import model.DBConnect;

/**
 *
 * @author PC
 */
public class ControllerCategory extends HttpServlet {

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
        DBConnect dBConnect = new DBConnect();
        DAOCategory dao = new DAOCategory(dBConnect);
        
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if(service==null) {
                service = "displayAll";
            }     
            
            if(service.equalsIgnoreCase("displayAll")) {
                // Model: entity, model     
                // Controller: get action from view --> model --> select view            
                // View: JSP
                // COntroller call Model
                String sql = "select * from Category";
                ResultSet rs = dBConnect.getData(sql);
                
                ArrayList<Category> arr = dao.getAllCategory();
                // controller add other item
                String titleTable = "List of category";
                // set information for view :
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                // call view
                RequestDispatcher dis= request.getRequestDispatcher("/category.jsp");
                
                dis.forward(request, response);
            }
            
            if(service.equalsIgnoreCase("addCategory")) {
                dispath(request, response, "/addCategory.jsp");
            }
            
            if(service.equalsIgnoreCase("insertdb")) {
                String cate = request.getParameter("cate");
                //System.out.println(cate);
                
                Category cat = new Category(cate, 1);

                int n = dao.addCategory(cat);
                
                response.sendRedirect("ControllerCategory");
            }
            
            if(service.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                String sql = "select * from Category where cateid="+id;
                ResultSet rs = dBConnect.getData(sql);
            
                if(rs.next()) {
                    Category cate = new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
                    request.setAttribute("cate", cate);
                    dispath(request, response, "/updateCategory.jsp");
                }
            }
            
            if(service.equals("updated")) {
                
                String cateid = request.getParameter("cateID");
                String catename = request.getParameter("cateName");
                int status = Integer.parseInt(request.getParameter("status"));
                
                Category cate = new Category(catename, status);

                dao.updateCategory(cateid, cate);
                          
                response.sendRedirect("ControllerCategory");
                
            }
            
            if(service.equals("delete")) {
                
                String id = request.getParameter("id");
                dao.deleteCategory(id);
                
                response.sendRedirect("ControllerCategory");
                
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerCategory</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerCategory at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
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

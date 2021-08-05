/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
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
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author PC
 */
public class ControllerProduct extends HttpServlet {

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
        DAOProduct dao = new DAOProduct(dBConnect);
        
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
                String sql = "select p.pid,p.pname,p.quantity,p.price,p.image,p.description,p.status,p.cateID,c.cateName from Product p , Category c where p.cateID=c.cateID;";
                ResultSet rs = dBConnect.getData(sql);
                //if(rs==null) {System.out.println("rs null");} else {System.out.println("rs not null");};
                
                ArrayList<Product> arr = dao.getAllProduct();
                //if(arr==null) {System.out.println("arr null");} else {System.out.println("arr not null");}; 
               
                // controller add other item
                String titleTable = "List of Product";
                // set information for view :
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                // call view
                RequestDispatcher dis= request.getRequestDispatcher("/product.jsp");
                
                dis.forward(request, response);
            }
            
            if(service.equalsIgnoreCase("addProduct")) {
                
                String sql2 = "select * from Category";
                ResultSet cateList = dBConnect.getData(sql2);
                request.setAttribute("cateList", cateList);
                
                dispath(request, response, "/addProduct.jsp");
            }
            
            if(service.equalsIgnoreCase("insertdb")) {
                String pid = request.getParameter("pid");
                    String pname = request.getParameter("pname");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    String image = request.getParameter("image");
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));
                    int cateID = Integer.parseInt(request.getParameter("cate"));

                    Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                    int n = dao.addProduct(pro);
                    
                    response.sendRedirect("ControllerProduct");
            }
            
            if(service.equals("update")) {
                int id = Integer.parseInt(request.getParameter("pid"));
                
                String sql = "select * from Product where pid="+id;
                ResultSet rs = dBConnect.getData(sql);
                
                String sql2 = "select * from Category";
                ResultSet cateList = dBConnect.getData(sql2);
                
                if(rs.next()) {
                    Product pro = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                    request.setAttribute("pro", pro);
                    request.setAttribute("cateList", cateList);
                    dispath(request, response, "/updateProduct.jsp");
                }
            }
            
            if(service.equals("updated")) {
                
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String image = request.getParameter("image");
                String description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateID = Integer.parseInt(request.getParameter("cateID"));
                
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                
                dao.updateProduct(pid, pro);
                          
                response.sendRedirect("ControllerProduct");
                
            }
            
            if(service.equals("delete")) {
                
                String pid = request.getParameter("pid");
                dao.deleteProduct(pid);
                
                response.sendRedirect("ControllerProduct");
                
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerProduct</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerProduct at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dispath(HttpServletRequest request, HttpServletResponse response, String url) {
        RequestDispatcher dis= request.getRequestDispatcher(url);
        try {
            dis.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
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

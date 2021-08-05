/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author New PC
 */
public class DAOProduct {
     Connection conn = null;
     DBConnect dbconn;
     
    public DAOProduct(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product";
        ResultSet rs = dbconn.getData(sql);
        
        try {
            while(rs.next()) {
                Product pro = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    public int addProduct(Product pro) {
        int n = 0;
        
        String sql = "insert into Product(pid,pname,quantity,price,image,description,status,cateID)\n"
                + "values(?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, pro.getPid());
            ps.setString(2, pro.getPname());
            ps.setInt(3, pro.getQuantity());
            ps.setDouble(4, pro.getPrice());
            ps.setString(5, pro.getImage());
            ps.setString(6, pro.getDescription());
            ps.setInt(7, pro.getStatus());
            ps.setInt(8, pro.getCateID());
            
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateProduct(String pid, Product pro) {
        int n = 0;
        
        String sql = "update Product "
                + "set pname='"+pro.getPname()+"'"
                + ",quantity='"+pro.getQuantity()+"'"
                + ",price='"+pro.getPrice()+"'"
                + ",image='"+pro.getImage()+"'"
                + ",description='"+pro.getDescription()+"'"
                + ",status='"+pro.getStatus()+"'"
                + ",cateID='"+pro.getCateID()+"'"
                + "where pid='"+pid+"'";
        
        System.out.println(sql);
        
         try {
             Statement state = conn.createStatement();
             n = state.executeUpdate(sql);
             
         } catch (SQLException ex) {
             Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        return n;
    }
    
    public int changePrice(String pid, double price) {
        int n =0;
        
        String presql = "update Product set price=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(presql);
            pre.setDouble(1, price);
            pre.setString(2, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int changeQuantity(String pid, int quantity) {
        int n =0;
        
        String presql = "update Product set quantity=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(presql);
            pre.setInt(1, quantity);
            pre.setString(2, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int changeDescription(String pid,String description) {
        int n =0;
        
        String presql = "update Product set description=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(presql);
            pre.setString(1, description);
            pre.setString(2, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void searchProductByName(String pname) {
        
        String sql = "select * from Product where pname='"+pname+"'";
        
         try {        
             ResultSet rs = dbconn.getData(sql);
             
             while (rs.next()) {              
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String description = rs.getString(6);
                int cateID = rs.getInt(8);
                
                System.out.println(new Product(pname,quantity,price,description,cateID));
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
         }    
        
    }
    
    
    
    public void showProductByPriceAscending() {
        String sql = "select * from Product order by price asc";
        
         try {        
             ResultSet rs = dbconn.getData(sql);
             
             while (rs.next()) {  
                String pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String description = rs.getString(6);
                int cateID = rs.getInt(8);
                
                System.out.println(new Product(pname,quantity,price,description,cateID));
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public int deleteProduct(String pid){
        int n=0;
        // Product -- 1 --n-->Bill
        // check foreign key
        String sql="select * from BillDetail where pid='"+pid+"'";
        //getdata
        ResultSet rs=dbconn.getData(sql);
        try {
            if(rs.next()){
                // productid is exited --> change status
                String sql1 = "update Product set status=0 where pid='"+pid+"'";
                Statement state1 = conn.createStatement();
                state1.executeUpdate(sql1);
                
            }else{
               String sql2 = "delete from Product where pid='"+pid+"'";
               Statement state2 = conn.createStatement();
               n = state2.executeUpdate(sql2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOProduct dao = new DAOProduct(dbconn);
     
        //dao.searchProductByName("Table");
        dao.showProductByPriceAscending();
    }
}

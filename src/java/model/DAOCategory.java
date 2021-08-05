/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Category;
import entity.Customer;
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
public class DAOCategory {
    Connection conn = null;
    DBConnect dbconn;
    
    
    public DAOCategory(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    
    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> arr = new ArrayList<Category>();
        String sql = "select * from Category";
        ResultSet rs = dbconn.getData(sql);
        
        try {
            while(rs.next()) {
                Category cate = new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    public int addCategory(Category cat) {
        int n = 0;
        
//        String sql = "insert into Category(cateName,status)\n"
//                + "values('"+cat.getCateName()+"','"+cat.getStatus()+"')";
//        
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        

        String sql = "insert into Category(cateName, status)"
                + "values(?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, cat.getCateName());
            ps.setInt(2, cat.getStatus());
            
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int changeCategoryName(String oldCateName,String newCateName) {
        int n =0;
        
        String cateID = null;
        
        String checksql = "select * from Category where cateName='"+oldCateName+"'";
        
        ResultSet rs = dbconn.getData(checksql);
        
        try {
            if(!rs.next()) {
                System.out.println("no admin found");
                return 0;
            } else {
                cateID = rs.getString(1);
            }
            
        }catch(Exception ex) {
            System.out.println(ex);
        }
        
        String sql = "update Admin set cateName='"+newCateName+"' where cateID='"+cateID+"'";
        
        try{
            Statement sm = conn.createStatement();
            n = sm.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void showAllCategoryByName() {
        
        String sql = "select * from Category order by cateName asc";
        
        try {
            ResultSet rs = dbconn.getData(sql);
            
            while(rs.next()) {
                System.out.println(new Category(rs.getInt(1),rs.getString(2),1));
            }
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int deleteCategory(String cateid) {
        int n = 0;
        
        // CHECK FOREIGN KEY FROM PRODUCT TABLE
        String sql = "select * from Product where cateID='"+cateid+"'";
        
        ResultSet rs = dbconn.getData(sql);
        
        try { 
            if(rs.next()) { // PRODUCT TABLE HAVE ROW HAVE CATEID
                
                // SET CATEGORY STATUS TO 0 (UNENABLE)
                
            String sql1 = "update Category set status = 0 where cateID='"+cateid+"'";
            
            Statement state = conn.createStatement();
            state.executeUpdate(sql1);
            
                
            } else {  // DELETE CATEGEORY
                
            String sql2 = "delete from Category where cateID='"+cateid+"'";
            
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql2);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateCategory(String cateid, Category cate) {
        int n =0;
        
        String sql = "update Category set cateName='"+cate.getCateName()+"', status='"+cate.getStatus()+"' where cateID='"+cateid+"'";
        
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOCategory dao = new DAOCategory(dbconn);
        dao.showAllCategoryByName();
    }
    
}

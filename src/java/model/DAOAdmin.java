/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
import entity.Bill;
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
public class DAOAdmin {
    Connection conn = null;
    DBConnect dbconn;
    
    public DAOAdmin(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    
    public ArrayList<Admin> getAllAdmin() {
        ArrayList<Admin> arr = new ArrayList<Admin>();
        
        String sql = "select * from admin";
        ResultSet rs = dbconn.getData(sql);
        
        try {
            while(rs.next()) {
               Admin ad = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3));
               arr.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    public int addNewAdmin(Admin ad) {
        int n =0;
//        String sql = "insert into Admin(username,password)\n"
//                + "values ('"+ad.getUsername()+"','"+ad.getPassword()+"')";
        String sql = "insert into Admin(username,password)"
                + "values (?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, ad.getUsername());
            ps.setString(2, ad.getPassword());
            
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int changePassword(String username,String oldpass, String newpass) {
        int n =0;
        String adminID = null;
        
        String checksql = "select * from Admin where username='"+username+"' and password='"+oldpass+"'";
        
        ResultSet rs = dbconn.getData(checksql);
        
        try {
            if(!rs.next()) {
                System.out.println("no admin found");
                return 0;
            } else {
                adminID = rs.getString(1);
            }
            
        }catch(Exception ex) {
            System.out.println(ex);
        }
        
        // ok found admin
        
        String sql = "update Admin set password='"+newpass+"' where adminID='"+adminID+"'";
        
        try{
            Statement sm = conn.createStatement();
            n = sm.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    } 
    
    public void showAllUsername() {
        String sql = "select usesname from Admin";
        
        try{
             ResultSet rs = dbconn.getData(sql);
             
             while(rs.next()) {
                 String username= rs.getString(1);
                 
                 System.out.println(username);
             }
             
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int updateAdmin(String adminID, Admin ad) {
        int n = 0;
        
        String sql = "update admin set username='"+ad.getUsername()+"'"
                    +",password='"+ad.getPassword()+"'"
                    +"where adminID='"+adminID+"'";
        
        try {
            Statement sm = conn.createStatement();
            n= sm.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int deleteAdmin(String adminid) {
        int n = 0;
        
        
        
        String sql = "delete from admin where adminID='"+adminid+"'";
        
        try {
            Statement sm = conn.createStatement();
            n = sm.executeUpdate(sql);
                    
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOAdmin dao = new DAOAdmin(dbconn);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DAOCustomer {
    
    Connection conn = null;
    DBConnect dbconn = null;
    
    public DAOCustomer(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    
    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> arr = new ArrayList<Customer>();
        String sql = "select * from Customer";
        
        ResultSet rs = dbconn.getData(sql);
        
        try {
            while(rs.next()) {
                Customer cus = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                arr.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    //insert, update, delete: number of records
    public int addCustomer(Customer cus) {
        int n = 0;
//        String sql = "insert into Customer(cname, cphone, cAddress,username,password)\n" 
//                + "values ('"+cus.getCname()+"','"+cus.getCphone()+"','"+cus.getcAddress()+"','"+cus.getUsername()+"','"+cus.getPassword()+"');";
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
          String preSql = "insert into "
                  + "Customer(cname,cphone,cAddress,username,password)"
                  + "values(?,?,?,?,?)";
          
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            n = pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
        return n;
    }
    
    public int updateCustomer(String cid, Customer cus) {
        int n = 0;
        
        String sql = "update Customer set cname=?,cphone=?,cAddress=?"
                + ",username=?,password=?, status=? where cid=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            pre.setInt(7, Integer.parseInt(cid.trim()));
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int changeStatus (int cid, int status) {
        int n= 0;
        String preSql="update Customer set status=? where cid=?";
        
        try {
            PreparedStatement pre=conn.prepareStatement(preSql);
            pre.setInt(1, status);
            pre.setInt(2, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int changePassword(int cid,String username,
            String oldpass,String newPass){
        int n=0;
        // check pass and repass -- javascript
        // check account (username, oldpass)
        String checksql = "select * from Customer where username='"+
                username+" and password='"+oldpass+"'";
        ResultSet rs = dbconn.getData(checksql);
        try {
           if(!rs.next()) {
                 System.out.println("account dont exist");
                 return n;
           }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        // check OK
        String sql="update Customer set password=? where cid=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, newPass);
            ps.setInt(2, cid);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAllCustomer() {
        String sql = "select * from Customer";
        
        try {
            //TYPE_FORWARD_ONLY: pointer top --> down
            //TYPE_SCROLL_SENSITIVE: top <--> down;thread safe
           
            //CONCUR_READ_ONLY: not modify resultset
            
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            
            // ResultSet rs = dbconn.getData(sql);
            
            while(rs.next()) {
                int cid = rs.getInt("cid"); // rs.getInt(1)
                
                String cname = rs.getString(2);
                
                String cphone = rs.getString(3);
                
                String cAddress = rs.getString(4);
                
                String username = rs.getString(5);
                
                String password = rs.getString(6);
                
                int status = rs.getInt(7);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                
                System.out.println(cus);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int deleteCustomer(String cid) {
        int n = 0;
        
        String sql = "delete from Customer where cid='"+cid+"'";
        
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
        DAOCustomer dao = new DAOCustomer(dbconn);
//        int n = dao.addCustomer(new Customer("Binh","095858","Ha Noi","Binh123","abc12332"));
//        if(n>0)
//            System.out.println("Inserted");

        dao.displayAllCustomer();
    }
    
    
}

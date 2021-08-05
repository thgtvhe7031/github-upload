/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
import entity.BillDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author New PC
 */
public class DAOBillDetail {
    Connection conn;
    DBConnect dbconn;
    
    
    public DAOBillDetail(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    
    public int addBillDetail(BillDetail bd) {
        int n =0;
        
//        String sql = "insert into BillDetail(pid, oID, quantity, money, total)\n"
//                + "values('"+bd.getPid()+"','"+bd.getoID()+"','"+bd.getQuantity()+"','"+bd.getMoney()+"','"+bd.getTotal()+"')";
        
//        try {
//            Statement st = conn.createStatement();
//            n = st.executeUpdate(sql);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        String sql = "insert into BillDetail(pid, oID, quantity, money, total)\n"
                + "values(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, bd.getPid());
            ps.setString(2, bd.getoID());
            ps.setInt(3, bd.getQuantity());
            ps.setDouble(4, bd.getMoney());
            ps.setDouble(5, bd.getTotal());
            
            n = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public ResultSet getBillDetailOfBill(String oid) {
        ResultSet rs = null;
        String sql = "select p.pname,p.description,c.cateName,bd.quantity,p.price,bd.total" 
            +"\nfrom BillDetail bd inner join Product p"
            +"\non p.pid= bd.pid"
            +"\ninner join Category c"
            +"\non p.cateID = c.cateID"
            +"\nwhere bd.oID = " +oid ;
        
        try {
            rs =  dbconn.getData(sql);
            
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return rs;
    }
    
    public void showBillDetailOfBill(String oid) {
        String sql = "select * from BillDetail where oID='"+oid+"'";
        
        try{
             ResultSet rs = dbconn.getData(sql);
             
             while(rs.next()) {
                 String pID = rs.getString(1);
                 int quantity = rs.getInt(3);
                 double price = rs.getDouble(4);
                 
                 System.out.println(new BillDetail(pID,oid,quantity,price));
             }
             
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int deleteBillDetail(String oid,String pid) {
        int n = 0;
        
        String sql = "delete from BillDetail where oid='"+oid+"' and pid='"+pid+"'";
        
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
        DAOBillDetail dao = new DAOBillDetail(dbconn);
        
    }
}

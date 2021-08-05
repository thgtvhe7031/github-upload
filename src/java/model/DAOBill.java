/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
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
public class DAOBill {
    Connection conn = null;
    DBConnect dbconn;
    
    public DAOBill(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    
    public int addBill(Bill bill) {
        int n = 0;
        
//        String sql = "insert into Bill(oID, cname, cphone, cAddress, total, status, cid)\n"
//               + "values ('"+bill.getCid()+"','"+bill.getCname()+"','"+bill.getCphone()+"','"+bill.getcAddress()+"','"+bill.getTotal()+"','"+bill.getStatus()+"','"+bill.getCid()+"')";
        
//        try {
//            Statement st = conn.createStatement();
//            n = st.executeUpdate(sql);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        String sql = "insert into Bill(oID, cname, cphone, cAddress, total, status, cid)"
               + "values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bill.getoID());
            ps.setString(2, bill.getCname());
            ps.setString(3, bill.getCphone());
            ps.setString(4, bill.getcAddress());
            ps.setDouble(5, bill.getTotal());
            ps.setInt(6, bill.getStatus());
            ps.setInt(7, bill.getCid());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void showBillByDateDescending() {
        String sql = "select * from Bill order by dateCreate desc";
        
        try{
             ResultSet rs = dbconn.getData(sql);
             
             while(rs.next()) {
                 String oID = rs.getString(1);
                 String date = rs.getString(2);
                 String name = rs.getString(3);
                 double total = rs.getDouble(6);
                 int cid = rs.getInt(8);
                 
                 System.out.println(new Bill(oID,date,name,total,cid));
             }
             
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int deleteBill(String oid) {
        int n = 0;
        
        String sql = "delete from Bill where oid='"+oid+"'";
        
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
        DAOBill dao = new DAOBill(dbconn);
        dao.showBillByDateDescending();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginApp;

import dbutil.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAHUL
 */
public class loadModal {
    
   private Connection conn=null;
   private PreparedStatement prs=null;
   private ResultSet res=null;
   private String sql="";
   
   public loadModal(){
   
       try {
           conn= dbConnection.getConnection();
       } catch (SQLException ex) {
          ex.printStackTrace();
       }
   }
   
   public boolean isConnected(){
   return conn!=null;
   }
   
   public boolean isLogin(String uname, String upass, String opt) throws SQLException{
       
       try {
           sql="select * from logininfo where username=? and password=? and division=?";
           
           prs=conn.prepareStatement(sql);
           prs.setString(1, uname);
           prs.setString(2, upass);
           prs.setString(3, opt);
            
           res=prs.executeQuery();
           
           if(res.next())return true;
           
           return false;
           
       } catch (SQLException ex) {
         return false;
       }
       finally{
          res.close();
           prs.close();
          
        
       }
       
   }
    
}

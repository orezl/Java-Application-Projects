/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retreive;

import dbutil.dbConnection;
import dbutil.studentmarksdata;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginApp.loginController;

/**
 *
 * @author RAHUL
 */
public class retreiveController implements Initializable{
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @FXML
    private Button back,retreive,rsearch;
    @FXML
    private Label querystatus,rname,rpass,rques;
    @FXML
    private TextField rans,rid;
    
    
    private String sql,ans,pass;
    
    @FXML
    public void rsearchfunc(ActionEvent e) throws SQLException{
        clearfun();
        ResultSet rs=null;
        if(rid.getText().equalsIgnoreCase("ADMIN"))querystatus.setText("Invalid Details!!");
        else{
        try {
            sql="Select *  from logininfo where username='"+rid.getText()+"'";
            Connection conn =  dbConnection.getConnection();
             rs=conn.createStatement().executeQuery(sql);
            if (rs.next()){
                
                System.out.println("else");
            pass=rs.getString(2);
                rname.setText(rs.getString(4));
                rques.setText(rs.getString(5));
                ans=rs.getString(6);
                
              }
            else{
                System.out.println("if");
                 querystatus.setText("Invalid Details!!");
         
        }}catch (SQLException ex) {
           // System.out.println("fail");
            querystatus.setText("Invalid Details!!");
        }
            
        } 
    }
    
    @FXML
    public void retreivefunc(ActionEvent e){
    
        if(rans.getText().equalsIgnoreCase(ans)){rpass.setText(pass);
       querystatus.setText("Password successfully Retrived"); }
        else
            querystatus.setText("Invalid Details!!");
    }
    
    @FXML
    public void goback(ActionEvent e){
      
         Stage stage=(Stage)back.getScene().getWindow();
        
        
                try {
           // Stage stage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/loginApp/login.fxml").openStream());
            loginController logincontrol=(loginController) loader.getController();
            
            Scene scene=new Scene(root);
             stage.setScene(scene);
             stage.setTitle("SMS");
             stage.setResizable(false);
             stage.centerOnScreen();
             stage.show();  
  }catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }

}

    private void clearfun() {
         querystatus.setText("");
         rans.setText("");
         rname.setText("");
         rques.setText("");
         rpass.setText("");
          }
}
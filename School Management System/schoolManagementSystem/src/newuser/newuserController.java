/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newuser;

import dbutil.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginApp.loginController;


/**
 *
 * @author RAHUL
 */
public class newuserController implements Initializable{
    
        
    @FXML
   private Label newstatus;
    @FXML
    private Button newuser,newback,newclc;
    @FXML
    private TextField newid,newname,newans;
    @FXML
    private ComboBox newdiv, newques;
    @FXML
    private PasswordField newpass;
    
    private int check;
    String sql;
    Connection conn;
    ResultSet rs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
          this.newdiv.setItems(FXCollections.observableArrayList(newoption.values()));
          this.newques.getItems().addAll(newquestion.values());
        
    }

    
    @FXML
    public void newuserfunc(ActionEvent e) throws SQLException{
        
        if(newid.getText().equalsIgnoreCase("ADMIN"))newstatus.setText("Invalid user ID");
        else{
        
            if(!newid.getText().isEmpty() &&!newpass.getText().isEmpty() && !newname.getText().isEmpty() && !newans.getText().isEmpty() && !newdiv.getValue().toString().isEmpty()&& !newques.getValue().toString().isEmpty()){
            
                check=checkuser(newid.getText());
                if(check==0){
                    try {
                        /////////////////////////////////////////////////////////////
                        String addsql="insert into newuser (username,password,division,name,question, answer,status) values (?,?,?,?,?,?,?)";
                        
                        Connection addconn = dbConnection.getConnection();
                        PreparedStatement pr=addconn.prepareStatement(addsql);
                        
                        pr.setString(1, newid.getText());
                        pr.setString(2,newpass.getText());
                        pr.setString(3, newdiv.getValue().toString());
                        pr.setString(4, newname.getText());
                        pr.setString(5,  newques.getValue().toString());
                        pr.setString(6, newans.getText());
                         pr.setString(7, "NO");
                        
                        pr.execute();
                        addconn.close();
                        
                         newstatus.setText("User created!!");
                        //////////////////////////////////////////////////////////////
                    } catch (SQLException ex) {
                        Logger.getLogger(newuserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(check==1)
                    newstatus.setText("User already exist!!");
                else
                     newstatus.setText("Account is not yet Activated!!");
            }
            else
                newstatus.setText("Fill all the fields!!");
        }
    }
    
    @FXML
    public void newbackfunc(ActionEvent e){
      
         Stage stage=(Stage)newback.getScene().getWindow();
        
        
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

    @FXML
    public void newclcfunc(ActionEvent e){
    
        this.newid.setText("");
        this.newpass.setText("");
        this.newname.setText("");
        this.newans.setText("");
        this.newdiv.valueProperty().set(null);
        this.newques.valueProperty().set(null);
        this.newstatus.setText("");
         
    }
    
    private int checkuser(String text) throws SQLException {
    
         rs=null;
        
        try {
            sql="Select *  from newuser where username='"+text+"'";
            conn =  dbConnection.getConnection();
            rs=conn.createStatement().executeQuery(sql);
            
        }catch (SQLException ex) {
           // System.out.println("fail");
            //querystatus.setText("Invalid Details!!");
        }
            if (!rs.next()){
                System.out.println("0"); 
                close();
                return 0;
                }
            else{
            if(rs.getString(7).equals("NO"))
            {
                System.out.println("2");
                 close();
                 return 2;}}
            System.out.println("1");
             close();
            return 1;
    }

    private void close() throws SQLException {
                 rs.close();
                conn.close();    }
    
    
        
    }
    
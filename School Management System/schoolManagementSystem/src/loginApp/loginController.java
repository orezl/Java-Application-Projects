/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginApp;

import admin.adminController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
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
import newuser.newuserController;
import retreive.retreiveController;
import student.studentController;
import teacher.teacherController;

/**
 *
 * @author RAHUL
 */
public class loginController implements Initializable{
    
    loadModal loginmodal=new loadModal();
    
    @FXML
    Label dbstatus;
            
    @FXML
    Label loginstatus;
    
    @FXML
    ComboBox type;
    
    @FXML
    Button login;
    
    @FXML
    Button fp;
    
    @FXML
    Button  newlogin;
    
    @FXML
    TextField username;
    
    @FXML
    PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  System.out.println(type.getValue());
        if(this.loginmodal.isConnected()){//this.dbstatus.styleProperty("color").setValue(COLOR.GREEN);
        this.dbstatus.setText("Connected");}
        else this.dbstatus.setText("Not Connected");
        
        this.type.setItems(FXCollections.observableArrayList(option.values()));
       
    }
    
    @FXML
    public void loginfun(ActionEvent e) throws IOException{
        // System.out.println(type.getValue());
        if(type.getValue()==null){
            loginstatus.setText("Invalid credentials!!");
            }
        else{
        try {
            if(this.loginmodal.isLogin(username.getText(), password.getText(),type.getValue().toString())){
             putpropertyvalue();             
             switch(type.getValue().toString()){
             
                 case "Admin":adminloginfun();
                                break;
                case "Teacher":teacherloginfun();
                              break;
                case "Student":studentloginfun();
                              break;              
                              
             }
                
            }
            else{   loginstatus.setText("Invalid credentials!!");
                    }
        } catch (SQLException ex) {
            
              // loginstatus.setText("Invalid credentials!! exception");
        }
    
        }
    }
    
    public void teacherloginfun(){
    
        try {
              Stage stage=(Stage)login.getScene().getWindow();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/teacher/teacher.fxml").openStream());
            teacherController teachcontrol=(teacherController) loader.getController();
            
            Scene scene=new Scene(root);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.setTitle("Teacher Dashboard: Welcome "+getpropertyvalue());
             stage.setResizable(false);
             stage.show();

        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void adminloginfun(){
        
        try {
             Stage stage=(Stage)login.getScene().getWindow();
              
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/admin/admin.fxml").openStream());
            adminController admincontrol=(adminController) loader.getController();
            
            Scene scene=new Scene(root);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.setTitle("Admin Dashboard: Welcome "+getpropertyvalue());
             stage.setResizable(false);
            
             stage.show();

        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
    
        public void studentloginfun(){
        
        try {
             Stage stage=(Stage)login.getScene().getWindow();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/student/student.fxml").openStream());
            studentController studcontrol=(studentController) loader.getController();
            
            Scene scene=new Scene(root);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.setTitle("Student Dashboard: Welcome "+getpropertyvalue());
             stage.setResizable(false);
             stage.show();

        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
        
    @FXML
    public void retreive(ActionEvent e){
    
        Stage stage=(Stage)fp.getScene().getWindow();
        
                try {
           // Stage fpstage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/retreive/retreive.fxml").openStream());
            retreiveController retcontrol=(retreiveController) loader.getController();
            
            Scene scene=new Scene(root);
            stage.setScene(scene);
             stage.centerOnScreen();
             stage.setTitle("Retrive Password");
             stage.setResizable(false);
             stage.show();

        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void newloginfunc(ActionEvent e){

           Stage stage=(Stage)newlogin.getScene().getWindow();
        
                try {
           // Stage fpstage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/newuser/newuser.fxml").openStream());
            newuserController newusercontrol=(newuserController) loader.getController();
            
            Scene scene=new Scene(root);
            stage.setScene(scene);
             stage.centerOnScreen();
             stage.setTitle("New User Creation");
             stage.setResizable(false);
             stage.show();

        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    
}
    
    private void putpropertyvalue() throws FileNotFoundException, IOException{
    
        Properties p=new Properties();
        OutputStream os=new FileOutputStream("C:\\Users\\RAHUL\\Documents\\NetBeansProjects\\schoolManagementSystem\\src\\dbutil\\user.properties");
        p.setProperty("user", username.getText());
        p.store(os, null);
    }
   
    private String getpropertyvalue() throws FileNotFoundException, IOException{
    
        Properties p=new Properties();
        InputStream in=new FileInputStream("C:\\Users\\RAHUL\\Documents\\NetBeansProjects\\schoolManagementSystem\\src\\dbutil\\user.properties");
        p.load(in);
        return p.getProperty("user");
        
        }
}

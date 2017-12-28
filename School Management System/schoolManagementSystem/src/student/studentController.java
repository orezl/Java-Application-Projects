/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import dbutil.dbConnection;
import dbutil.studentdata;
import dbutil.studentmarksdata;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 *
 * @author RAHUL
 */
public class studentController implements Initializable{
    

    @FXML
    private TextField studupdname, studupdfname, studupdadd;
    @FXML
    private Button studupdclc, studupd;
    @FXML
    private DatePicker studupddob;
    @FXML
    private TableView<studentmarksdata> markstable;
    @FXML
    private TableColumn<studentmarksdata,String> studmarkidcol,studmarknamecol,studmarkmathscol,studmarksciencecol,studmarkenglishcol,studmarktotalcol,studmarkgradecol,studmarkpercentcol;
    @FXML
    private Text studresid,studresname,studresfname,studresadd,studresdob;
    @FXML
    private Label studresstatus;
    
    private dbConnection dbcon;
    private  ObservableList<studentmarksdata> data;
     private  ObservableList<studentdata> datas;
    private String sql,sid,sname,sfname,sadd,sdob;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        this.dbcon=new dbConnection();
         
        try {
        sid = getpropertyvalue();
         } catch (IOException ex) {
         Logger.getLogger(studentController.class.getName()).log(Level.SEVERE, null, ex);
         }
        studresstatus.setText("");
        fillintro(sid);
        filltable(sid);
        
         }
                    
        private String getpropertyvalue() throws FileNotFoundException, IOException{
    
        Properties p=new Properties();
        InputStream in=new FileInputStream("C:\\Users\\RAHUL\\Documents\\NetBeansProjects\\schoolManagementSystem\\src\\dbutil\\user.properties");
        p.load(in);
        return p.getProperty("user");
        
        }
        
    
    @FXML
    public void studupdfunc(ActionEvent e){
        try {
           // studresstatus.setText("");
           
           if(!studupdname.getText().isEmpty())sname=studupdname.getText();
           if(!studupdfname.getText().isEmpty())sfname=studupdfname.getText();
           if(!studupdadd.getText().isEmpty())sadd=studupdadd.getText();
           if(studupddob.getValue()!=null)sdob=studupddob.getValue().toString();
        
            if(studupdname.getText().isEmpty())sname=studresname.getText();
            if(studupdfname.getText().isEmpty())sfname=studresfname.getText();
            if(studupdadd.getText().isEmpty())sadd=studresadd.getText();
            if(studupddob.getValue()==null)sdob=studresdob.getText();

           String addsql="UPDATE student SET name=?,fathername=?,address=?,dob=? where id=?";
           
           
                   //+ ""+sname+"', fathername='"+sfname+"', address='"+sadd+"', dob='"+sdob+"' where id='"+sid+"'";
            
           Connection addconn = dbConnection.getConnection();
            PreparedStatement pr=addconn.prepareStatement(addsql);
            
            pr.setString(1, sname);
             pr.setString(2, sfname);
              pr.setString(3, sadd);
               pr.setString(4, sdob);
                pr.setString(5, sid);
            
           int tr=pr.executeUpdate();
            if(tr>0){
           studresstatus.setText("Succesfull updated !!");
            }
            else
                studresstatus.setText("Failed !!");
           
                
             addconn.close();
            
            
            fillintro(sid);
            filltable(sid);
   
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    public void studupdclcfunc(ActionEvent e){
    
        this.studupdname.setText("");
        this.studupdfname.setText("");
        this.studupdadd.setText("");
        this.studupddob.setValue(null);
        studresstatus.setText("");
        
        
    }

    private void filltable(String sid) {
    
        try {
        
         sql="select * from marks where id='"+sid+"'";

            Connection conn =  dbConnection.getConnection();
            this.data=FXCollections.observableArrayList();
            
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next()){
             this.data.add(new studentmarksdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.studmarkidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.studmarknamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.studmarkmathscol.setCellValueFactory(new PropertyValueFactory<>("maths"));        
        this.studmarksciencecol.setCellValueFactory(new PropertyValueFactory<>("science"));        
        this.studmarkenglishcol.setCellValueFactory(new PropertyValueFactory<>("english")); 
        this.studmarktotalcol.setCellValueFactory(new PropertyValueFactory<>("teacher")); 
        this.studmarkpercentcol.setCellValueFactory(new PropertyValueFactory<>("percentage")); 
        this.studmarkgradecol.setCellValueFactory(new PropertyValueFactory<>("grade")); 

    
        this.markstable.setItems(null);
        this.markstable.setItems(data);

    }

    private void fillintro(String sid) {
    
        try {
            
             sql="select * from student where id='"+sid+"'";
            
            Connection conn =  dbConnection.getConnection();
            this.datas=FXCollections.observableArrayList();
            
            ResultSet rs=conn.createStatement().executeQuery(sql);
            if(rs.next()){
            //this.datas.add(new studentdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        this.studresid.setText(rs.getString(1));
        this.studresname.setText(rs.getString(2));
        this.studresfname.setText(rs.getString(3));        
        this.studresadd.setText(rs.getString(4)); 
        this.studresdob.setText(rs.getString(5)); 
            
            }
            conn.close();
            rs.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
      
       // this.studresid.setText(new PropertyValueFactory<>("teacher"));
    
    }
            
            
    
}

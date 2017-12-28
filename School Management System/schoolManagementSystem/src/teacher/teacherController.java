/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import dbutil.typeoption;
import dbutil.dbConnection;
import dbutil.setvalues;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import loginApp.option;

/**
 *
 * @author RAHUL
 */
public class teacherController implements Initializable{
    
        @FXML
    private Tab studdetailpane;
    @FXML
    private TextField addstudid, addstudname, addstudfname, addstudadd;
    @FXML
    private Button addstudclear, allstuddetail, addstudbut;
    @FXML
    private DatePicker addstuddob;
    @FXML
    private TableView<studentdata> studdetailtable;
    @FXML
    private TableColumn<studentdata,String> idcol,namecol,fnamecol,addcol,dobcol;
    @FXML
    private Label addstatus;
    

    @FXML
    private TextField addstudmarkid, addstudmarkname, addstudmarkmaths, addstudmarkscience,addstudmarkenglish;
    @FXML
    private Button allstudmarkdetail, addstudmark, addstudmarkidclear;
    @FXML
    private TableView<studentmarksdata> markstable;
    @FXML
    private TableColumn<studentmarksdata,String> studmarkidcol,studmarknamecol,studmarkmathscol,studmarksciencecol,studmarkenglishcol,studmarktotalcol, studmarkpercentcol, studmarkgradecol;
    @FXML
    private Label addstudmarkidstatus;
    
    
    @FXML
    private TextField studsrchid, studsrchname;
    @FXML
    private TextField studresid, studresname,studresfname,studresadd,studresdob;
    @FXML
    private ComboBox studsrchtype;
    @FXML
    private Button studsrchclear, studsrch, allstudsrchmarks;
     @FXML
    private Label studsrchatatus;
     @FXML
     private VBox teachersearch;
    
    private dbConnection dbcon;
    private  ObservableList<studentdata> data;
    private  ObservableList<studentmarksdata> datam;
    private  ObservableList<studentmarksdata> datamv;
    private String sql;
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        this.dbcon=new dbConnection();
        this.studsrchtype.setItems(FXCollections.observableArrayList(typeoption.values()));
        
    }
    
    @FXML
    public void allstuddetailfunc(ActionEvent e)throws SQLException, IOException{
    
        try {
            
             sql="select * from student where teacher='"+getpropertyvalue()+"'";
            
            Connection conn =  dbConnection.getConnection();
            this.data=FXCollections.observableArrayList();
            
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next()){
            this.data.add(new studentdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.fnamecol.setCellValueFactory(new PropertyValueFactory<>("fathername"));        
        this.addcol.setCellValueFactory(new PropertyValueFactory<>("address"));        
        this.dobcol.setCellValueFactory(new PropertyValueFactory<>("dob")); 

    
        this.studdetailtable.setItems(null);
        this.studdetailtable.setItems(data);
    
    }
    
    @FXML
    public void addstudbutfunc(ActionEvent e) throws IOException{
        
        if(addstudid.getText().isEmpty() || addstudname.getText().isEmpty() || addstudfname.getText().isEmpty() || addstudadd.getText().isEmpty() || addstuddob.getEditor().getText().isEmpty() ){
        
            addstatus.setText("Some input is missing, Please Check !!");
        }
    
        else{
        try {
            addstatus.setText("");
            
            String addsql="insert into student(id,name,fathername,address,dob,teacher) values (?,?,?,?,?,?)";
            
            Connection addconn = dbConnection.getConnection();
            PreparedStatement pr=addconn.prepareStatement(addsql);
            
            pr.setString(1, addstudid.getText());
            pr.setString(2,addstudname.getText());
            pr.setString(3, addstudfname.getText());
            pr.setString(4, addstudadd.getText());
            pr.setString(5, addstuddob.getEditor().getText());
            pr.setString(6, getpropertyvalue());
            
            pr.execute();
            addconn.close();
            
            addstatus.setText("New Student is added Succesfully !!");
            this.allstuddetailfunc(e);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    }
    
    @FXML
    public void addstudclearfunc(ActionEvent e){
    
        this.addstudid.setText("");
        this.addstudname.setText("");
        this.addstudfname.setText("");
        this.addstudadd.setText("");
        this.addstuddob.setValue(null);
        this.addstatus.setText("");
        
    }
    
    @FXML
    public void allstudmarkdetailfunc(ActionEvent e)throws SQLException, IOException{
    
        try {
            
             sql="select * from marks where teacher='"+getpropertyvalue()+"'";
            
            Connection conn =  dbConnection.getConnection();
            this.datam=FXCollections.observableArrayList();
            
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next()){
            this.datam.add(new studentmarksdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.studmarkidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.studmarknamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.studmarkmathscol.setCellValueFactory(new PropertyValueFactory<>("maths"));        
        this.studmarksciencecol.setCellValueFactory(new PropertyValueFactory<>("science"));        
        this.studmarkenglishcol.setCellValueFactory(new PropertyValueFactory<>("english")); 
        this.studmarktotalcol.setCellValueFactory(new PropertyValueFactory<>("total")); 
        this.studmarkpercentcol.setCellValueFactory(new PropertyValueFactory<>("percentage")); 
        this.studmarkgradecol.setCellValueFactory(new PropertyValueFactory<>("grade")); 

    
        this.markstable.setItems(null);
        this.markstable.setItems(datam);
    
    }
    
        @FXML
    public void addstudmarkfunc(ActionEvent e) throws IOException{
    
        
        if(addstudmarkid.getText().isEmpty() || addstudmarkname.getText().isEmpty() || addstudmarkmaths.getText().isEmpty() || addstudmarkscience.getText().isEmpty() || addstudmarkenglish.getText().isEmpty() ){
        
            addstudmarkidstatus.setText("Some input is missing, Please Check !!");
        }
    
        else{
            Boolean chk=checkstud(addstudmarkid.getText(),addstudmarkname.getText());
            
            if(chk){
        try {
            addstudmarkidstatus.setText("");
            

            String addsql="insert into marks (id,name,teacher,maths,science,english,total,percentage,grade) values (?,?,?,?,?,?,?,?,?)";
            
            Connection addconn = dbConnection.getConnection();
            PreparedStatement pr=addconn.prepareStatement(addsql);
            
            pr.setString(1, addstudmarkid.getText());
            pr.setString(2,addstudmarkname.getText());
            pr.setString(3, getpropertyvalue());
            pr.setString(4, addstudmarkmaths.getText());
            pr.setString(5,addstudmarkscience.getText());
            pr.setString(6,addstudmarkenglish.getText());
            pr.setString(7,setvalues.settotal(addstudmarkmaths.getText(), addstudmarkscience.getText(), addstudmarkenglish.getText()));
            pr.setString(8,setvalues.setpercentage());
            pr.setString(9,setvalues.grade);
            
            pr.execute();
            addconn.close();
            
            addstudmarkidstatus.setText("Marks added Succesfully !!");
            this.allstudmarkdetailfunc(e);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
            else
                addstudmarkidstatus.setText("Student Do not Exist!!");
    }
    }
    
    @FXML
    public void addstudmarkidclearfunc(ActionEvent e){
    
        this.addstudmarkid.setText("");
        this.addstudmarkname.setText("");
        this.addstudmarkmaths.setText("");
        this.addstudmarkscience.setText("");
        this.addstudmarkenglish.setText("");
        this.addstudmarkidstatus.setText("");
    }
    
    @FXML
    public void allstudsrchmarksfunc(ActionEvent e) throws SQLException, IOException{
        this.allstudmarkdetailfunc(e);
    }
    
     @FXML
    public void studsrchfunc(ActionEvent e) throws IOException{
        if(studsrchtype.getValue()==null){ studsrchatatus.setText("Search type is missing, Please Check !!");}
        else{
        if(studsrchid.getText().isEmpty() && studsrchtype.getValue().toString().equals("PI")){
        
            studsrchatatus.setText("ID is missing, Please Check !!");
        }
    
        else{
        
            studsrchatatus.setText("");
            
            switch(studsrchtype.getValue().toString()){
             
                 case "PI":viewPI();
                                break;
                case "Marks":viewMarks(e);
                              break;
             }
        
    }
    }
    }
    
    
    @FXML
    public void studsrchclearfunc(ActionEvent e){
    
        teachersearch.setDisable(true);
        this.studsrchid.setText("");
        this.studsrchname.setText("");
        this.studresid.setText("");
        this.studresname.setText("");
        this.studresfname.setText("");
        this.studresadd.setText("");
        this.studresdob.setText("");
        this.studsrchtype.valueProperty().set(null);
    }
    
    private void viewPI() throws IOException{
            try {
                String addsql;
                if(!studsrchid.getText().isEmpty() && !studsrchname.getText().isEmpty())
                    addsql="select * from student where id='"+studsrchid.getText()+"' and name='"+studsrchname.getText()+"' and teacher='"+getpropertyvalue()+"'" ;
                else
                    addsql="select * from student where id='"+studsrchid.getText()+"' and teacher='"+getpropertyvalue()+"'" ;
                
                Connection addconn = dbConnection.getConnection();
                ResultSet rs=addconn.createStatement().executeQuery(addsql);
                
                if(!rs.next())studsrchatatus.setText("Incorrect details entered!!");
                else{
                    studsrchatatus.setText("Data Found!!");
                    teachersearch.setDisable(false);
                    studresid.setText(rs.getString(1));
                    studresname.setText(rs.getString(2));
                    studresfname.setText(rs.getString(3));
                    studresadd.setText(rs.getString(4));
                    studresdob.setText(rs.getString(5));
                }
                rs.close();
                addconn.close();
               
            } catch (SQLException ex) {
                Logger.getLogger(teacherController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

            private void viewMarks(ActionEvent e) throws IOException{
            try {
                String addsql;
                if(!studsrchid.getText().isEmpty() && !studsrchname.getText().isEmpty())
                    addsql="select * from marks where id='"+studsrchid.getText()+"' and name='"+studsrchname.getText()+"' and teacher='"+getpropertyvalue()+"'";
                else if(studsrchid.getText().isEmpty())
                    addsql="select * from marks where name='"+studsrchname.getText()+"' and teacher='"+getpropertyvalue()+"'" ;
                else
                    addsql="select * from marks where id='"+studsrchid.getText()+"' and teacher='"+getpropertyvalue()+"'" ;
                
                Connection addconn = dbConnection.getConnection();
                this.datamv=FXCollections.observableArrayList();
            
            ResultSet rs=addconn.createStatement().executeQuery(addsql);
            while(rs.next()){
            this.datamv.add(new studentmarksdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.studmarkidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.studmarknamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.studmarkmathscol.setCellValueFactory(new PropertyValueFactory<>("maths"));        
        this.studmarksciencecol.setCellValueFactory(new PropertyValueFactory<>("science"));        
        this.studmarkenglishcol.setCellValueFactory(new PropertyValueFactory<>("english")); 
        this.studmarktotalcol.setCellValueFactory(new PropertyValueFactory<>("total")); 
        this.studmarkpercentcol.setCellValueFactory(new PropertyValueFactory<>("percentage")); 
        this.studmarkgradecol.setCellValueFactory(new PropertyValueFactory<>("grade")); 

    
        this.markstable.setItems(null);
        this.markstable.setItems(datamv);
    }
    
    
    
        private String getpropertyvalue() throws FileNotFoundException, IOException{
    
        Properties p=new Properties();
        InputStream in=new FileInputStream("C:\\Users\\RAHUL\\Documents\\NetBeansProjects\\schoolManagementSystem\\src\\dbutil\\user.properties");
        p.load(in);
        return p.getProperty("user");
        
        }

    private Boolean checkstud(String id, String name) throws IOException { 
         Boolean bol=false;
                    try {
                       
                String addsql;
                 addsql="select * from student where id='"+id+"' and name='"+name+"' and teacher='"+getpropertyvalue()+"'" ;
                
                Connection addconn = dbConnection.getConnection();
                ResultSet rs=addconn.createStatement().executeQuery(addsql);
               bol=rs.next();
                rs.close();
                addconn.close();
               
            } catch (SQLException ex) {
                Logger.getLogger(teacherController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return bol;
    }
    
}

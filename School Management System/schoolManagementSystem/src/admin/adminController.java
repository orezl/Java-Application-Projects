
package admin;

import dbutil.dbConnection;
import dbutil.studentdata;
import dbutil.studentmarksdata;
import dbutil.teacherdata;
import dbutil.typeoption;
import dbutil.userdata;
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
import newuser.newuserController;
import teacher.teacherController;

public class adminController implements Initializable{
    
    //studentdata studdata= new studentdata();
    
    @FXML
    private Tab studdetailpane;
    @FXML
    private TextField addstudteacher, addstudid, addstudname, addstudfname, addstudadd;
    @FXML
    private Button addstudclear, allstuddetail, addstudbut;
    @FXML
    private DatePicker addstuddob;
    @FXML
    private TableView<studentdata> studdetailtable;
    @FXML
    private TableColumn<studentdata,String> idcol,namecol,fnamecol,addcol,dobcol,tnamecol;
    @FXML
    private Label addstatus;
    
    @FXML
    private Tab teachdetail;
    @FXML
    private TextField addteachexp, addteachid, addteachname, addteachfname, addteachadd;
    @FXML
    private Button addteachclear, allteacher, addteacher;
    @FXML
    private DatePicker addteachdob;
    @FXML
    private TableView<teacherdata> teachdetailtable;
    @FXML
    private TableColumn<teacherdata,String> teachidcol,teachnamecol,teachfnamecol,teachaddcol,teachdobcol,teachexpcol;
    @FXML
    private Label addteachstatus;
    
    @FXML
    private TextField studsrchid, studsrchname,studsrchtname;
    @FXML
    private TextField studresid, studresname,studresfname,studresadd,studresdob,studresdobtname;
    @FXML
    private ComboBox studsrchtype;
    @FXML
    private Button studsrchclear, studsrch, allstudsrchmarks;
     @FXML
    private Label studsrchatatus;
     @FXML
     private VBox teachersearch;
    @FXML
    private TableView<studentmarksdata> markstable;
    @FXML
    private TableColumn<studentmarksdata,String> studmarkidcol,studmarknamecol,studmarkmathscol,studmarksciencecol,studmarkenglishcol,studmarktnamescol, studmarkpercentcol, studmarkgradecol;
    
    @FXML
    private TextField userid, userstatus;
    @FXML
    private TextField userresid, userresname,userresstatus,userresdiv;
    @FXML
    private Button userclear, usersrch, allusers,userupdatestatus;
     @FXML
    private Label usersrchatatus;
     @FXML
     private VBox usersrchres;
    @FXML
    private TableView<userdata> userstable;
    @FXML
    private TableColumn<userdata,String> useridcol,usernamecol,userdivcol,userquescol,userstatuscol;
 
    
    
    private dbConnection dbcon;
    private  ObservableList<studentdata> data;
    private String sql;
    private  ObservableList<teacherdata> datat;
    private  ObservableList<studentmarksdata> datav;
    private  ObservableList<userdata> datau;
    private userdata datauser;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        this.dbcon=new dbConnection();
         this.studsrchtype.setItems(FXCollections.observableArrayList(typeoption.values()));
        
    }
    
    @FXML
    public void allstuddetailfunc(ActionEvent e)throws SQLException{
    
        try {
            
             sql="select * from student";
            
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
        this.tnamecol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
    
        this.studdetailtable.setItems(null);
        this.studdetailtable.setItems(data);
    
    }
    
    @FXML
    public void addstudbutfunc(ActionEvent e){
        
        if(addstudid.getText().isEmpty() || addstudname.getText().isEmpty() || addstudfname.getText().isEmpty() || addstudadd.getText().isEmpty()|| addstudteacher.getText().isEmpty() || addstuddob.getEditor().getText().isEmpty() ){
        
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
            pr.setString(6, addstudteacher.getText());
            
            pr.execute();
            addconn.close();
            
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
        this.addstudteacher.setText("");
    }
    
    @FXML
    public void allteacherfunc(ActionEvent e)throws SQLException{
    
        try {
             sql="select * from teacher";
            
            Connection conn =  dbConnection.getConnection();
            this.datat=FXCollections.observableArrayList();
            
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next()){
            this.datat.add(new teacherdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.teachidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.teachnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.teachfnamecol.setCellValueFactory(new PropertyValueFactory<>("fathername"));        
        this.teachaddcol.setCellValueFactory(new PropertyValueFactory<>("address"));        
        this.teachdobcol.setCellValueFactory(new PropertyValueFactory<>("dob")); 
        this.teachexpcol.setCellValueFactory(new PropertyValueFactory<>("experience"));
    
        this.teachdetailtable.setItems(null);
        this.teachdetailtable.setItems(datat);
    
    }
    
    @FXML
    public void addteacherfunc(ActionEvent e){
        
        if(addteachid.getText().isEmpty() || addteachname.getText().isEmpty() || addteachfname.getText().isEmpty() || addteachadd.getText().isEmpty()|| addteachexp.getText().isEmpty() || addteachdob.getEditor().getText().isEmpty() ){
        
            addteachstatus.setText("Some input is missing, Please Check !!");
        }
    
        else{
        try {
            addteachstatus.setText("");
            
            String addsql="insert into teacher(id,name,experience,fathername,address,dob) values (?,?,?,?,?,?)";
            
            Connection addconn = dbConnection.getConnection();
            PreparedStatement pr=addconn.prepareStatement(addsql);
            
            pr.setString(1, addteachid.getText());
            pr.setString(2,addteachname.getText());
            pr.setString(4, addteachfname.getText());
            pr.setString(5, addteachadd.getText());
            pr.setString(6, addteachdob.getEditor().getText());
            pr.setString(3, addteachexp.getText());
            
            pr.execute();
            addconn.close();
            
            this.allteacherfunc(e);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    }
    
    @FXML
    public void addteachclearfunc(ActionEvent e){
    
        this.addteachid.setText("");
        this.addteachname.setText("");
        this.addteachfname.setText("");
        this.addteachadd.setText("");
        this.addteachdob.setValue(null);
        this.addteachexp.setText("");
        
    }
    
    

        @FXML
    public void allstudsrchmarksfunc(ActionEvent e)throws SQLException{
    
        try {
            
             sql="select * from marks";
            
            Connection conn =  dbConnection.getConnection();
            this.datav=FXCollections.observableArrayList();
            
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next()){
            this.datav.add(new studentmarksdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.studmarkidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.studmarknamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.studmarkmathscol.setCellValueFactory(new PropertyValueFactory<>("maths"));        
        this.studmarksciencecol.setCellValueFactory(new PropertyValueFactory<>("science"));        
        this.studmarkenglishcol.setCellValueFactory(new PropertyValueFactory<>("english")); 
        this.studmarktnamescol.setCellValueFactory(new PropertyValueFactory<>("teacher")); 
        this.studmarkpercentcol.setCellValueFactory(new PropertyValueFactory<>("percentage")); 
        this.studmarkgradecol.setCellValueFactory(new PropertyValueFactory<>("grade")); 

    
        this.markstable.setItems(null);
        this.markstable.setItems(datav);
    
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
         this.studsrchtname.setText("");
        this.studresdobtname.setText("");
    }
    
    private void viewPI() throws IOException{
            try {
                String addsql="select * from student where id='Z1'";
                if( !studsrchname.getText().isEmpty() && !studsrchtname.getText().isEmpty())
                    addsql="select * from student where id='"+studsrchid.getText()+"' and name='"+studsrchname.getText()+"' and teacher='"+studsrchtname.getText()+"'" ;
                else if(!studsrchtname.getText().isEmpty())
                    addsql="select * from student where id='"+studsrchid.getText()+"' and teacher='"+studsrchtname.getText()+"'" ;
                else if(!studsrchname.getText().isEmpty())
                    addsql="select * from student where id='"+studsrchid.getText()+"' and name='"+studsrchname.getText()+"'" ;
                else
                    addsql="select * from student where id='"+studsrchid.getText()+"'" ;
                
                    
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
                    studresdobtname.setText(rs.getString(6));
                }
                rs.close();
                addconn.close();
               
            } catch (SQLException ex) {
                Logger.getLogger(teacherController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

            private void viewMarks(ActionEvent e) throws IOException{
            try {
               String addsql="select * from student where id='Z1'";
                
                
                if( !studsrchname.getText().isEmpty() && !studsrchtname.getText().isEmpty() && !studsrchid.getText().isEmpty())
                    addsql="select * from marks where id='"+studsrchid.getText()+"' and name='"+studsrchname.getText()+"' and teacher='"+studsrchtname.getText()+"'" ;
                else if( !studsrchname.getText().isEmpty() && !studsrchtname.getText().isEmpty() && studsrchid.getText().isEmpty())
                    addsql="select * from marks where name='"+studsrchname.getText()+"' and teacher='"+studsrchtname.getText()+"'" ;
                else if( studsrchname.getText().isEmpty() && !studsrchtname.getText().isEmpty() && !studsrchid.getText().isEmpty())
                    addsql="select * from marks where id='"+studsrchid.getText()+"' and teacher='"+studsrchtname.getText()+"'" ;
                else if( !studsrchname.getText().isEmpty() && studsrchtname.getText().isEmpty() && !studsrchid.getText().isEmpty())
                    addsql="select * from marks where id='"+studsrchid.getText()+"' and name='"+studsrchname.getText()+"'" ;
                else if( studsrchname.getText().isEmpty() && studsrchtname.getText().isEmpty() && !studsrchid.getText().isEmpty())
                    addsql="select * from marks where id='"+studsrchid.getText()+"'" ;
                else if( studsrchname.getText().isEmpty() && !studsrchtname.getText().isEmpty() && studsrchid.getText().isEmpty())
                    addsql="select * from marks where teacher='"+studsrchtname.getText()+"'" ;
                else if( !studsrchname.getText().isEmpty() && studsrchtname.getText().isEmpty() && studsrchid.getText().isEmpty())
                    addsql="select * from marks where name='"+studsrchname.getText()+"'" ;
                
                
                
                Connection addconn = dbConnection.getConnection();
                this.datav=FXCollections.observableArrayList();
            
            ResultSet rs=addconn.createStatement().executeQuery(addsql);
            while(rs.next()){
            this.datav.add(new studentmarksdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.studmarkidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.studmarknamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.studmarkmathscol.setCellValueFactory(new PropertyValueFactory<>("maths"));        
        this.studmarksciencecol.setCellValueFactory(new PropertyValueFactory<>("science"));        
        this.studmarkenglishcol.setCellValueFactory(new PropertyValueFactory<>("english")); 
        this.studmarktnamescol.setCellValueFactory(new PropertyValueFactory<>("teacher")); 
        this.studmarkpercentcol.setCellValueFactory(new PropertyValueFactory<>("percentage")); 
        this.studmarkgradecol.setCellValueFactory(new PropertyValueFactory<>("grade")); 

    
        this.markstable.setItems(null);
        this.markstable.setItems(datav);
    }
    
     @FXML
    public void allusersfunc(ActionEvent e)throws SQLException{
    
        try {sql="select * from newuser";
            
            Connection conn =  dbConnection.getConnection();
            this.datau=FXCollections.observableArrayList();
            
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next()){
            this.datau.add(new userdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.useridcol.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.usernamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.userdivcol.setCellValueFactory(new PropertyValueFactory<>("division"));        
        this.userquescol.setCellValueFactory(new PropertyValueFactory<>("question"));        
        this.userstatuscol.setCellValueFactory(new PropertyValueFactory<>("status")); 
        
        this.userstable.setItems(null);
        this.userstable.setItems(datau);
    
    }        
            
    @FXML
    public void userupdatestatusfunc(ActionEvent e) throws SQLException{
    
        if(userstatus.getText().isEmpty()) usersrchatatus.setText("Status Input missing!!");
        
        else if(!userstatus.getText().equalsIgnoreCase("YES"))usersrchatatus.setText("Input can only be YES or NO!!");
        
        else{
        
            userstatus.setText(userstatus.getText().toUpperCase());
            ////////////////////////////////////
             String addsql="UPDATE newuser SET status=? where username=?";
            
           Connection addconn = dbConnection.getConnection();
            PreparedStatement pr=addconn.prepareStatement(addsql);
            
            pr.setString(1, userstatus.getText());
             pr.setString(2, userid.getText());
             
           int tr=pr.executeUpdate();
            if(tr>0){
          
           allusersfunc(e);
           insertintologininfo();
           usersrchatatus.setText("Succesfull updated !!");
            }
            else
                usersrchatatus.setText("Failed !!");
            addconn.close();
            
        } 
            ///////////////////////////////////
        }

    private void insertintologininfo() {
    
    try {
                        /////////////////////////////////////////////////////////////
                        String addsql="insert into logininfo (username,password,division,name,question, answer) values (?,?,?,?,?,?)";
                        
                        Connection addconn = dbConnection.getConnection();
                        PreparedStatement pr=addconn.prepareStatement(addsql);
                        
                        pr.setString(1, datauser.getUsername());
                        pr.setString(2,datauser.getPassword());
                        pr.setString(3, datauser.getDivision());
                        pr.setString(4, datauser.getName());
                        pr.setString(5,datauser.getQuestion());
                        pr.setString(6, datauser.getAnswer());

                        pr.execute();
                        addconn.close();
                    
                        //////////////////////////////////////////////////////////////
                    } catch (SQLException ex) {
                        Logger.getLogger(newuserController.class.getName()).log(Level.SEVERE, null, ex);
                    }    
        
    }
        
    
        @FXML    
        public void usersrchfunc(ActionEvent e) throws IOException{
        {
        if(userid.getText().isEmpty()){
        
            usersrchatatus.setText("ID is missing");
        }
          
        else{
        
            usersrchatatus.setText("");
            //////////////////////////////////
                try {
                String addsql="select * from newuser where username='"+userid.getText()+"'";
                  
                Connection addconn = dbConnection.getConnection();
                ResultSet rs=addconn.createStatement().executeQuery(addsql);
                
                if(!rs.next())usersrchatatus.setText("ID do not exist!!");
                else{
                    usersrchatatus.setText("Data Found!!");
                      this.userstatus.setEditable(true);
                    usersrchres.setDisable(false);
                    
                    datauser=new userdata(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                    
                    userresid.setText(datauser.getUsername());
                    userresdiv.setText(datauser.getDivision());
                    userresname.setText(datauser.getName());
                    userresstatus.setText(datauser.getStatus());
                    
                 
                    
                    
                }
                rs.close();
                addconn.close();
               
            } catch (SQLException ex) {
                Logger.getLogger(teacherController.class.getName()).log(Level.SEVERE, null, ex);
            }
            /////////////////////////////////
               
    }
    }
    }        
    
            
    @FXML
    public void userclearfunc(ActionEvent e){
    
        usersrchres.setDisable(true);
        this.userid.setText("");
        this.userstatus.setText("");
        this.userstatus.setEditable(false);
        this.userresid.setText("");
        this.userresname.setText("");
        this.userresstatus.setText("");
        this.usersrchatatus.setText("");
        this.userresdiv.setText("");
        
    }    
            
    
        private String getpropertyvalue() throws FileNotFoundException, IOException{
    
        Properties p=new Properties();
        InputStream in=new FileInputStream("C:\\Users\\RAHUL\\Documents\\NetBeansProjects\\schoolManagementSystem\\src\\dbutil\\user.properties");
        p.load(in);
        return p.getProperty("user");
        
        }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author RAHUL
 */
public class studentdata {
    
      private StringProperty id;
    private StringProperty name;
    private StringProperty fathername;
    private StringProperty address;        
    private StringProperty dob;
    private StringProperty teacher;

    public studentdata(String id, String name, String fathername, String address, String dob, String teacher) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.fathername = new SimpleStringProperty(fathername);
        this.address = new SimpleStringProperty(address);
        this.dob = new SimpleStringProperty(dob);
        this.teacher = new SimpleStringProperty(teacher);
    }

    public studentdata() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 

    /**
     * @return the id
     */
    public String getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(StringProperty id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(StringProperty name) {
        this.name = name;
    }

    /**
     * @return the fathername
     */
    public String getFathername() {
        return fathername.get();
    }

    /**
     * @param fathername the fathername to set
     */
    public void setFathername(StringProperty fathername) {
        this.fathername = fathername;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * @param address the address to set
     */
    public void setAddress(StringProperty address) {
        this.address = address;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob.get();
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(StringProperty dob) {
        this.dob = dob;
    }

    /**
     * @return the teacher
     */
    public String getTeacher() {
        return teacher.get();
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(StringProperty teacher) {
        this.teacher = teacher;
    }
    
}

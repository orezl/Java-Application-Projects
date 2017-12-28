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
public class studentmarksdata {
    
    private StringProperty id;
    private StringProperty name;
    private StringProperty teacher;
    private StringProperty maths;        
    private StringProperty science;
    private StringProperty english;
    private StringProperty total;
    private StringProperty percentage;
    private StringProperty grade;

    public studentmarksdata(String id, String name, String teacher, String maths, String science, String english, String total, String percentage, String grade) {
        this.id =  new SimpleStringProperty(id);
        this.name =  new SimpleStringProperty(name);
        this.teacher =  new SimpleStringProperty(teacher);
        this.maths =  new SimpleStringProperty(maths);
        this.science =  new SimpleStringProperty(science);
        this.english =  new SimpleStringProperty(english);
        this.total =  new SimpleStringProperty(total);
        this.percentage =  new SimpleStringProperty(percentage);
        this.grade =  new SimpleStringProperty(grade);
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

    /**
     * @return the maths
     */
    public String getMaths() {
        return maths.get();
    }

    /**
     * @param maths the maths to set
     */
    public void setMaths(StringProperty maths) {
        this.maths = maths;
    }

    /**
     * @return the science
     */
    public String getScience() {
        return science.get();
    }

    /**
     * @param science the science to set
     */
    public void setScience(StringProperty science) {
        this.science = science;
    }

    /**
     * @return the english
     */
    public String getEnglish() {
        return english.get();
    }

    /**
     * @param english the english to set
     */
    public void setEnglish(StringProperty english) {
        this.english = english;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total.get();
    }

    /**
     * @param total the total to set
     */
    public void setTotal(StringProperty total) {
        this.total = total;
    }

    /**
     * @return the percentage
     */
    public String getPercentage() {
        return percentage.get();
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(StringProperty percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade.get();
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(StringProperty grade) {
        this.grade = grade;
    }
    
    
    
    
}

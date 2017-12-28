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
public class userdata {
    
    private StringProperty username;
    private StringProperty name;
    private StringProperty password;
    private StringProperty division;        
    private StringProperty question;
    private StringProperty answer;
    private StringProperty status;

    public userdata(String username,String password,String division, String name,   String question, String answer, String status) {
        this.username = new SimpleStringProperty(username);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.division = new SimpleStringProperty(division);
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
        this.status = new SimpleStringProperty(status);
    }
    
  

    /**
     * @return the username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(StringProperty username) {
        this.username = username;
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
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(StringProperty password) {
        this.password = password;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division.get();
    }

    /**
     * @param division the division to set
     */
    public void setDivision(StringProperty division) {
        this.division = division;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question.get();
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(StringProperty question) {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer.get();
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(StringProperty answer) {
        this.answer = answer;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status.get();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StringProperty status) {
        this.status = status;
    }
}

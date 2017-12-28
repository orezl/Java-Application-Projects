
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newuser;


/**
 *
 * @author RAHUL
 */
public enum newoption {
 Teacher, Student;
    
    private newoption(){};
    
    public String value(){
    return name();
    }
    
    public static newoption fromvalue(String v){
    return valueOf(v);
    }
    
}

    


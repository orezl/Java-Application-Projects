/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutil;

import static loginApp.option.valueOf;

/**
 *
 * @author RAHUL
 */
 
    public enum typeoption {
    PI, Marks;
    
    private typeoption(){};
    
    public String value(){
    return name();
    }
    
    public static typeoption fromvalue(String v){
    return valueOf(v);
    }
    
}

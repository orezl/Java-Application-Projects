/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutil;

/**
 *
 * @author RAHUL
 */
public class setvalues {
    
    public static Integer total;
    public static String grade;
    public static double percentage;
   
    
    public static String settotal(String m, String s,String e ){
    int ma=Integer.parseInt(m),sc=Integer.parseInt(s),en=Integer.parseInt(e);
    total=(ma+sc+en);
    percentage=(total*100)/300;
    
    if(total>250)grade="S";
    else if(total>200 && total<=250)grade="A";
    else if(total>150 && total<=200)grade="C";    
    else if(total>100 && total<=150)grade="D";
    else grade="F";
    
    return total.toString();
    
    }
    
    public static String setpercentage(){
    
        Double d=percentage;
        return d.toString();
    }
    

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
}

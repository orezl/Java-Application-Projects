/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAHUL
 */
public class setup implements Runnable{
    private String title;
    private int size;
    private Thread thread;
    private BufferStrategy buffer;
    private Graphics2D g;
    
    public setup(String title,int size){
    
        this.size=size;
        this.title=title;
    }
    
    public void init(){ // this function is to put all the initialisation variables and function
      
        new display(title,size); // to call display function 
    }
    
    public synchronized void start(){ // firstly start() is exected, then the thread created inside it calls the run()
                                        // then run() calls all the functions called inside it
                                        // when we close the frame, stop() is called
    
        thread = new Thread(this);
        thread.start();
    }
    public void draw(){
    
        buffer=display.canvas.getBufferStrategy();
        if(buffer==null){
        display.canvas.createBufferStrategy(3);
        return;}
        
        g=(Graphics2D)buffer.getDrawGraphics();
        g.clearRect(0, 0, size, size);
        
        //draw
        g.setColor(Color.black);
        g.fillOval(10, 10, size-20, size-20);
        
        g.setColor(Color.white);
        g.fillOval(20, 20, size-40, size-40);
        
        int x,y,r; double angle,a,b,time;
        r=(size/2)-20-20;//  radius of innercircle -20;
        for(int i=1;i<=12;i++){
        
            angle=2*Math.PI*(i/12.0);
            x=(int)((size/2) + Math.sin(angle)*r);
            y=(int)((size/2) - Math.cos(angle)*r);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.BOLD,20));
            g.drawString(Integer.toString(i), x, y);
        }   
        
            for(int i=1;i<=60;i++){
                r=(size/2)-20;
            angle=2*Math.PI*(i/60.0);
            x=(int)((size/2) + Math.sin(angle)*r);
            y=(int)((size/2) - Math.cos(angle)*r);
            if(i%5==0)r=r=(size/2)-70;
            else r=(size/2)-40;
            int x1=(int)((size/2) + Math.sin(angle)*r);
            int y1=(int)((size/2) - Math.cos(angle)*r);
            g.setColor(Color.gray);
            g.drawLine(x, y, x1, y1);
           
        
        }   
             Calendar today = new GregorianCalendar();
            int hour = today.get(Calendar.HOUR_OF_DAY);
    // System.out.println ("hour = " + hour);
              int minute = today.get(Calendar.MINUTE);
     //System.out.println ("minute = " + minute);
             int sec = today.get(Calendar.SECOND);
     //System.out.println ("second = " + sec);
        
     
           // hour=2;
            //minute=2;
            //sec=22;
                // hour hand
            //time=(System.nanoTime()/(1000000000.0*60.0*60.0*12.0))*2*Math.PI;
            time=((hour+(minute/60.0))/(12.0))*2*Math.PI;
            r=(size/2)-20-20-150;
            x=(int)((size/2) + Math.sin(time)*r);
            y=(int)((size/2) - Math.cos(time)*r);
            g.setColor(Color.red);
            g.setStroke(new BasicStroke(10));
            g.drawLine((size/2), (size/2), x, y);
             g.setStroke(new BasicStroke(0));
        
        // minute hand
           // time=(System.nanoTime()/(1000000000*60.0*60))*2*Math.PI;
           time=((minute+(sec/60.0))/(60.0))*2*Math.PI;
            r=(size/2)-20-20-70;
            x=(int)((size/2) + Math.sin(time)*r);
            y=(int)((size/2) - Math.cos(time)*r);
            g.setColor(Color.blue);
            g.setStroke(new BasicStroke(6));
            g.drawLine((size/2), (size/2), x, y);
             g.setStroke(new BasicStroke(0));
        
            // second hand
            //time=(System.nanoTime()/(1000000000.0*60.0))*2*Math.PI;
            time=(sec/(60.0))*2*Math.PI;
            r=(size/2)-20-20-30;
            x=(int)((size/2) + Math.sin(time)*r);
            y=(int)((size/2) - Math.cos(time)*r);
            g.setColor(Color.black);
            g.drawLine((size/2), (size/2), x, y);
        
        g.setColor(Color.red);
        g.fillOval((size/2)-10, (size/2)-10, 20, 20);
        
        //end
        buffer.show();
        g.dispose();
    }
    
    public synchronized void stop(){
    
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(setup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void run(){
    
        init();
        while(true){
            draw();
        }
    }
    
}

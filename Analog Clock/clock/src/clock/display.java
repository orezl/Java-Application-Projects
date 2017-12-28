/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author RAHUL
 */
public class display {

    private String title;
    private int size;
    JFrame frame;
    public static Canvas canvas;
    public display(String title, int size){
    
        this.size=size;
        this.title=title;
        drawdisplay();
        }
    
   public void drawdisplay(){
    
       frame=new JFrame();
        frame.setTitle(title);
        frame.setSize(size,size);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(size,size));
        canvas.setBackground(Color.pink);
        
        
        frame.add(canvas);
        frame.pack();
        
    }

    
}

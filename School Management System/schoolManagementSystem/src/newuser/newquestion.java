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
public enum newquestion {
   
            A("What is ur favourite food?"),
            B("What is ur favourite animal?"),
            C("What is ur favourite color?"),
            D("What is name of your first school?");

    private String game;

    newquestion(String game) {
        this.game = game;
    }

    public String newquestion() { return game; }

    @Override public String toString() { return game; }
    
}

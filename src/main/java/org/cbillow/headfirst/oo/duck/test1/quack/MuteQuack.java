package org.cbillow.headfirst.oo.duck.test1.quack;

/**
 * Created by Cbillow on 15/12/13.
 */
public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< Slience >>");
    }
}

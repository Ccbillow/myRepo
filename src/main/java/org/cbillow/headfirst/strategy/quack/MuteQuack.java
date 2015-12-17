package org.cbillow.headfirst.strategy.quack;

/**
 * Created by Cbillow on 15/12/13.
 */
public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< Slience >>");
    }
}

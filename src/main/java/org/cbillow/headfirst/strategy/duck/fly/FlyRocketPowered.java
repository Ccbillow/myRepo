package org.cbillow.headfirst.strategy.duck.fly;

/**
 * Created by Cbillow on 15/12/13.
 */
public class FlyRocketPowered implements FlyBehavior {

    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}

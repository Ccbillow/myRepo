package org.cbillow.headfirst.strategy;

import org.cbillow.headfirst.strategy.fly.FlyWithWings;
import org.cbillow.headfirst.strategy.quack.Quack;

/**
 * Created by Cbillow on 15/12/13.
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void diaplay() {
        System.out.println("I'm a real Mallard duck. ");
    }
}

package org.cbillow.headfirst.strategy;

import org.cbillow.headfirst.strategy.fly.FlyNoWay;
import org.cbillow.headfirst.strategy.quack.Quack;

/**
 * Created by Cbillow on 15/12/13.
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void diaplay() {
        System.out.println("I'm a model duck");
    }
}

package org.cbillow.headfirst.oo.duck.test1;

import org.cbillow.headfirst.oo.duck.test1.fly.FlyNoWay;
import org.cbillow.headfirst.oo.duck.test1.quack.Quack;

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

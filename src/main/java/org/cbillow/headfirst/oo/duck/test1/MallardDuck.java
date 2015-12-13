package org.cbillow.headfirst.oo.duck.test1;

import org.cbillow.headfirst.oo.duck.test1.fly.FlyWithWings;
import org.cbillow.headfirst.oo.duck.test1.quack.Quack;

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

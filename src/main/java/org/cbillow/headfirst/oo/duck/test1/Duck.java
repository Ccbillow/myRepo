package org.cbillow.headfirst.oo.duck.test1;

import org.cbillow.headfirst.oo.duck.test1.fly.FlyBehavior;
import org.cbillow.headfirst.oo.duck.test1.quack.QuackBehavior;

/**
 * Created by Cbillow on 15/12/13.
 */
public abstract class Duck {

    FlyBehavior flyBehavior;

    QuackBehavior quackBehavior;

    public Duck() {
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public abstract void diaplay();

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}

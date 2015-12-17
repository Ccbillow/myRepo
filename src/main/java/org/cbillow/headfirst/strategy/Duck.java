package org.cbillow.headfirst.strategy;

import org.cbillow.headfirst.strategy.fly.FlyBehavior;
import org.cbillow.headfirst.strategy.quack.QuackBehavior;

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

    /**
     * 子类不同类型鸭子实现各自的特点
     */
    public abstract void diaplay();

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}

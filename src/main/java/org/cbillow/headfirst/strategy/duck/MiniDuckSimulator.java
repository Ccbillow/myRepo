package org.cbillow.headfirst.strategy.duck;

import org.cbillow.headfirst.strategy.duck.fly.FlyRocketPowered;
import org.junit.Test;

/**
 * Created by Cbillow on 15/12/13.
 */
public class MiniDuckSimulator {

    @Test
    public void testMallardDuck() {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
    }

    @Test
    public void testModelDuck() {
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}

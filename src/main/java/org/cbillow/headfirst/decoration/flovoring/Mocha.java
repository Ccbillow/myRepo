package org.cbillow.headfirst.decoration.flovoring;

import org.cbillow.headfirst.decoration.Beverage;
import org.cbillow.headfirst.decoration.CondimentDecorator;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:05
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}

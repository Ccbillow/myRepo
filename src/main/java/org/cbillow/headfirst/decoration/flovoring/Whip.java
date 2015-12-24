package org.cbillow.headfirst.decoration.flovoring;

import org.cbillow.headfirst.decoration.Beverage;
import org.cbillow.headfirst.decoration.CondimentDecorator;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:10
 */
public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}

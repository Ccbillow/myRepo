package org.cbillow.headfirst.decoration.flovoring;

import org.cbillow.headfirst.decoration.Beverage;
import org.cbillow.headfirst.decoration.CondimentDecorator;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:10
 */
public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return .15 + beverage.cost();
    }
}

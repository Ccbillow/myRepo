package org.cbillow.headfirst.decoration.coffee;

import org.cbillow.headfirst.decoration.Beverage;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:03
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

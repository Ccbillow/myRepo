package org.cbillow.headfirst.decoration.coffee;

import org.cbillow.headfirst.decoration.Beverage;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:04
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}

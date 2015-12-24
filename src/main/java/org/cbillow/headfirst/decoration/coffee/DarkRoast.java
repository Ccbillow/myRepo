package org.cbillow.headfirst.decoration.coffee;

import org.cbillow.headfirst.decoration.Beverage;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:08
 */
public class DarkRoast extends Beverage{

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return .99;
    }
}

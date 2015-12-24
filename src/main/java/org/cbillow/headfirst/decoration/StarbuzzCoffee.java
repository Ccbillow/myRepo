package org.cbillow.headfirst.decoration;

import org.cbillow.headfirst.decoration.coffee.DarkRoast;
import org.cbillow.headfirst.decoration.coffee.Espresso;
import org.cbillow.headfirst.decoration.coffee.HouseBlend;
import org.cbillow.headfirst.decoration.flovoring.Mocha;
import org.cbillow.headfirst.decoration.flovoring.Soy;
import org.cbillow.headfirst.decoration.flovoring.Whip;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:11
 */
public class StarbuzzCoffee {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage1 = new DarkRoast();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);
        System.out.println(beverage1.getDescription() + " $" + beverage1.cost());

        Beverage beverage2 = new HouseBlend();
        beverage2 = new Soy(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
    }
}

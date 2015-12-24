package org.cbillow.headfirst.decoration;

/**
 * Created by Cbillow on 15/12/23.
 */
public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

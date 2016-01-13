package org.cbillow.headfirst.command;

/**
 * @author Created by Cbillow
 * @date 16/1/13
 * @time 15:07
 */
public class Demand implements Task {

    private Programmer programmer;

    public Demand(Programmer programmer) {
        this.programmer = programmer;
    }

    public void handle() {
        programmer.handleDemand();
    }

    @Override
    public String toString() {
        return "Demand{" +
                "programmer=" + programmer.getName() +
                '}';
    }
}

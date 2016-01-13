package org.cbillow.headfirst.command;

/**
 * @author Created by Cbillow
 * @date 16/1/13
 * @time 15:07
 */
public class Bug implements Task {

    private Programmer programmer;

    public Bug(Programmer programmer) {
        this.programmer = programmer;
    }

    public void handle() {
        programmer.handleBug();
    }

    @Override
    public String toString() {
        return "Bug{" +
                "programmer=" + programmer.getName() +
                '}';
    }
}

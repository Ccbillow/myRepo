package org.cbillow.headfirst.command;

/**
 * @author Created by Cbillow
 * @date 16/1/13
 * @time 15:07
 */
public class Problem implements Task {

    private Programmer programmer;

    public Problem(Programmer programmer) {
        this.programmer = programmer;
    }

    public void handle() {
        programmer.handleProblem();
    }

    @Override
    public String toString() {
        return "Problem{" +
                "programmer=" + programmer.getName() +
                '}';
    }
}

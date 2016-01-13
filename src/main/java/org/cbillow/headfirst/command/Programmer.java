package org.cbillow.headfirst.command;

/**
 * @author Created by Cbillow
 * @date 16/1/13
 * @time 15:08
 */
public class Programmer {

    private String name;

    public Programmer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void handleDemand() {
        System.out.println( name + "处理新需求");
    }

    public void handleBug() {
        System.out.println( name + "处理新bug");
    }

    public void handleProblem() {
        System.out.println( name + "处理线上问题");
    }
}

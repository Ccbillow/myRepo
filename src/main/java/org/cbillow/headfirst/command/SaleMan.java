package org.cbillow.headfirst.command;

/**
 * @author Created by Cbillow
 * @date 16/1/13
 * @time 15:22
 */
public class SaleMan {

    private String name;

    private ProductManager productManager;

    public SaleMan(String name, ProductManager productManager) {
        super();
        this.name = name;
        this.productManager = productManager;
    }

    public String getName() {
        return name;
    }

    public void putDemand(){
        System.out.println( "业务员" + name + "提出新需求");
        productManager.receive(new Demand(productManager.chooseProgrammer()));
    }

    public void putBug(){
        System.out.println( "业务员" + name + "提出bug");
        productManager.receive(new Bug(productManager.chooseProgrammer()));
    }

    public void putProblem(){
        System.out.println( "业务员" + name + "提出线上问题");
        productManager.receive(new Problem(productManager.chooseProgrammer()));
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
}

package org.cbillow.headfirst.command;

/**
 * @author Created by Cbillow
 * @date 16/1/13
 * @time 15:23
 */
public class Work {

    public static void main(String[] args) {
        Programmer xiaozuo = new Programmer("小左");
        ProductManager productManager = new ProductManager(xiaozuo);

        SaleMan saleManA = new SaleMan("A", productManager);
        SaleMan saleManB = new SaleMan("B", productManager);
        SaleMan saleManC = new SaleMan("C", productManager);
        SaleMan saleManD = new SaleMan("D", productManager);

        saleManA.putDemand();
        saleManB.putDemand();
        saleManB.putBug();
        saleManC.putDemand();
        saleManC.putProblem();
        saleManD.putDemand();

        System.out.println("第一天产品经理分配任务");
        productManager.assign();
        productManager.printTaskList();

        System.out.println("第二天产品经理分配任务");
        productManager.assign();
        productManager.printTaskList();
    }
}

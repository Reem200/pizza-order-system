package Model;
/*
  Author: Reem Mohamed
  Id: al0281
  Study program: DT
*/

public abstract class Order implements IOrder{
    protected double price;
    protected String orderName;

    @Override
    public double getPrice(){
        return price;
    }

    @Override
    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String getOrderName(){
        return orderName;
    }

    @Override
    public void setOrderName(String orderName){
        this.orderName = orderName;
    }
}

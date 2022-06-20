package Model;
/*
  Author: Reem Mohamed
  Study program: DT
*/
import java.util.ArrayList;

public class Cart{

    private ArrayList<Order> orderList;
    private ArrayList<Integer> orderAmountList;

    private double price;
    private int orderId;

    public Cart(){
        orderList = new ArrayList<>();
        orderAmountList = new ArrayList<>();
    }

    public void addNewProduct(Order order, int amount){
        int index = -1; //ogiltig location på listan
        boolean isNewProduct = true;

        //kntoll om ordern redan finns; öka kvantitet
        for (int i = 0; i < orderList.size(); i++){
            if(orderList.get(i).getOrderName().equalsIgnoreCase(order.getOrderName())) {
                index = i;
                isNewProduct = false;
                break;
            }
        }

        //kntoll om ordern är ny; lägg till en ny
        if(isNewProduct){
            orderList.add(order);
            orderAmountList.add(amount);
        }

        //ordern beställdes innan; öka registererat amount
        else{
            int currentAmount = orderAmountList.get(index) + amount; //lägger till amount
            orderAmountList.set(index, currentAmount); //updaterar amount
        }
    }

    //innan checkout, omvandlas listan --> läsbar list
    public String[] getOrderList(){
        String[] OrderListString = new String[orderList.size()]; //samma

        for (int i = 0; i < orderList.size(); i++){
            OrderListString[i] = orderList.get(i).toString() + " Quantity: " + orderList.get(i);
        }
        return OrderListString;
    }

    public double calculateTotalPrice(){
        price = 0;  //default
        for (int i = 0; i < orderList.size(); i++){
            price = price + (orderList.get(i).price * orderAmountList.get(i)); //total
        }
        return price;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int id){
        this.orderId = id;
    }

    //skriver ut fakturan
    public String getReceipt()
    {
        String receiptStr = "";
        receiptStr += "Order number: " + orderId + "\n";

        for (int i = 0; i < orderList.size(); i++){
            receiptStr += orderList.get(i).toString() + "Quantity: " + orderAmountList.get(i) + "\n";
        }

        receiptStr += "Total price: " + calculateTotalPrice() + "\n";

        return receiptStr;
    }

    @Override
    public String toString(){
        return "Order_" + orderId;
    }
}

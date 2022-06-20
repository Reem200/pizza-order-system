package Model;
/*
  Author: Reem Mohamed
  Study program: DT
*/

public class Pizza extends Order{

    private Toppings[] toppingsList;

    private int maxToppings = 5;
    private int toppingsAmount;

    public Pizza(String orderName, double price) {
        toppingsList = new Toppings[maxToppings];

        this.orderName = orderName;
        this.price = price;
    }

    public void addToppings(Toppings topping)
    {
        if (toppingsAmount < toppingsList.length){
            toppingsList[toppingsAmount] = topping;
            toppingsAmount++;
        }
        else{
            System.out.println("You cannot add more than 3 toppings!");
        }
    }

    @Override
    public String toString()
    {
        String toppingString = "";
        for (int i = 0; i < toppingsAmount; i++)
        {
            toppingString += toppingsList[i].toString() + ", ";
        }
        return String.format("%s %.2f kr %s" , orderName, price, toppingString);

    }
}

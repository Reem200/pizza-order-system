package Model;
/*
  Author: Reem Mohamed
  Id: al0281
  Study program: DT
*/

import java.util.ArrayList;

public class Pizzeria {
    private ArrayList<Pizza> pizzaList;
    private ArrayList<Cart> cartList;

    private int totalOrders = 0;

    public Pizzeria() {
        preparePizza();
    }

    private void preparePizza(){
        pizzaList = new ArrayList<>();
        cartList = new ArrayList<>();
        Pizza firstPizzaType = new Pizza("Margherita Pizza", 70);
        firstPizzaType.addToppings(Toppings.Cheese);
        firstPizzaType.addToppings(Toppings.Sauce);
        pizzaList.add(firstPizzaType);

        Pizza secondPizzaType = new Pizza("Kebab Pizza", 80);
        secondPizzaType.addToppings(Toppings.Kebab);
        secondPizzaType.addToppings(Toppings.Olives);
        secondPizzaType.addToppings(Toppings.Sauce);
        pizzaList.add(secondPizzaType);

        Pizza thirdPizzaType = new Pizza("Tomaso Pizza", 65);
        thirdPizzaType.addToppings(Toppings.TomatoSauce);
        thirdPizzaType.addToppings(Toppings.Olives);
        secondPizzaType.addToppings(Toppings.Sauce);
        pizzaList.add(thirdPizzaType);

        Pizza fourthPizzaType = new Pizza("Hawaii Pizza", 70);
        fourthPizzaType.addToppings(Toppings.Pineapple);
        fourthPizzaType.addToppings(Toppings.Shrimps);
        pizzaList.add(fourthPizzaType);

        Pizza fifthPizzaType = new Pizza("Vegetariana Pizza", 80);
        fifthPizzaType.addToppings(Toppings.Onion);
        fifthPizzaType.addToppings(Toppings.Mushrooms);
        fifthPizzaType.addToppings(Toppings.Olives);
        pizzaList.add(fifthPizzaType);

    }

    public ArrayList<Pizza> getPizzeriaList(){
        return pizzaList;
    }

    public ArrayList<Cart> getCartList(){
        return cartList;
    }

    public void addOrder(Cart order){
        order.setOrderId(totalOrders);
        totalOrders++;
        cartList.add(order);
    }

    public int ordersAmount(){
        return cartList.size();
    }

    public Pizza getAvailablePizzaList(int i)
    {
        return pizzaList.get(i);
    }

    public Cart getOrderInCart(int i)
    {
        return cartList.get(i);
    }
}

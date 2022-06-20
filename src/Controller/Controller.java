package Controller;
/*
  Author: Reem Mohamed
  Id: al0281
  Study program: DT
*/
import Model.Cart;
import Model.Pizza;
import Model.Pizzeria;
import View.View;

import java.util.ArrayList;

public class Controller {

    private Pizzeria pizzeria;

    private Cart cart;

    private View view;



    public Controller() {
        pizzeria = new Pizzeria();
        view = new View(this);

        cart = new Cart();

        updateLists();
    }

    public void updateLists() {
        view.updatePizzaList(getPizzaList());
        view.updateOrderList(getOrderProductsInCart());
        view.updateTotal(cart.calculateTotalPrice());

        if(pizzeria.getCartList().size() > 0){
            view.updateCheckoutList(getCartList());
        }
    }

    public void addProduct(int i, int quantity) {
        Pizza pizza = pizzeria.getAvailablePizzaList(i);
        cart.addNewProduct(pizza, quantity);

        updateLists();
    }


    public boolean order() {
        boolean okOrder = false;

        if(cart.getOrderList().length > 0){ // 1 eller mer orders finns
            pizzeria.addOrder(cart);
            cart = new Cart(); //tillgänglig att ha en ny order
            okOrder = true;
        }

        updateLists();

        return okOrder;
    }

    public String[] getPizzaList(){
        ArrayList<Pizza> pizzaList = pizzeria.getPizzeriaList();

        String[] pizzaArrayStr = new String[pizzaList.size()];

        for (int i = 0; i < pizzaArrayStr.length; i++){
            pizzaArrayStr[i] = pizzaList.get(i).toString();
        }

        return pizzaArrayStr;
    }

    public String[] getOrderProductsInCart(){
        return cart.getOrderList();
    }


    public String[] getCartList()
    {
        ArrayList<Cart> cartList = pizzeria.getCartList();

        String[] orderArrayStr = new String[cartList.size()];

        for (int i = 0; i < orderArrayStr.length; i++){
            orderArrayStr[i] = cartList.get(i).toString();
        }

        return orderArrayStr;
    }


    public String orderDetails(int i) {
        if(pizzeria.getCartList().size() > 0){
            Cart receipt = pizzeria.getOrderInCart(i);
            return receipt.getReceipt();
        }
        else{
            return null;
        }
    }

}

package View;
/*
  Author: Reem Mohamed
  Study program: DT
*/

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    private JPanel mainPanel, buttonPanel = new JPanel();
    private JPanel middlePanel, pizzaPanel, orderPanel,checkoutPanel = new JPanel();

    private JButton addBtn, orderBtn, viewOrderBtn;

    private JList<String> pizzaList, orderList, checkoutList;

    JScrollPane scroll;
    JLabel costlabel;


    public View(Controller controller) {
        this.controller = controller;

        openMainPanel();

        openMiddlePanel();

        openPizzaPanel();

        openOrderPanel();

        openCheckoutPanel();

        openButtonPanel();


        addPanels();
        setTitle("Pizzerie");
        setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }


    public void openMainPanel(){
        mainPanel = new JPanel();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        mainPanel.setLayout(new BorderLayout());

    }
    public void openMiddlePanel(){
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(2, 3));
    }

    public void openPizzaPanel(){
        pizzaPanel = new JPanel();
        pizzaPanel.setBorder(BorderFactory.createTitledBorder("Pizza List"));
        pizzaPanel.setLayout(new BorderLayout());

        pizzaList = new JList<>();
        scroll = new JScrollPane(pizzaList);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pizzaPanel.add(scroll);


    }

    public void openOrderPanel() {
        orderPanel = new JPanel();

        orderPanel.setBorder(BorderFactory.createTitledBorder("Order"));
        orderPanel.setLayout(new BorderLayout());

        orderList = new JList<>();
        scroll = new JScrollPane(orderList);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        costlabel = new JLabel("Total cost: 0,00kr");
        orderPanel.add(scroll);

    }

    public void openCheckoutPanel(){
        checkoutPanel = new JPanel();
        checkoutPanel.setBorder(BorderFactory.createTitledBorder("Order History"));
        checkoutPanel.setLayout(new GridLayout(1,1));

        checkoutList = new JList<>();
        scroll = new JScrollPane(checkoutList);
        scroll.setHorizontalScrollBarPolicy((JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        checkoutPanel.add(scroll);

    }


    public void openButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        addBtn.addActionListener(this);

        orderBtn = new JButton("Order");
        orderBtn.addActionListener(this);

        viewOrderBtn = new JButton("View details");
        viewOrderBtn.addActionListener(this);

        buttonPanel.add(addBtn);
        buttonPanel.add(orderBtn);
        buttonPanel.add(viewOrderBtn);

    }

    public void addPanels(){
        mainPanel.add(checkoutPanel, BorderLayout.LINE_END);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);

        middlePanel.add(pizzaPanel);
        middlePanel.add(orderPanel);

        orderPanel.add(costlabel, BorderLayout.PAGE_END);
    }


    public void updatePizzaList(String[] list) {
        pizzaList.setListData(list);
    }

    public void updateOrderList(String[] list) {
        orderList.setListData(list);
    }

    public void updateCheckoutList(String[] list)
    {
        checkoutList.setListData(list);
    }

    public void updateTotal(double price)
    {
        costlabel.setText(String.format("Total: %.2fkr", price));
    }

    private void addPizza()
    {
        int i = pizzaList.getSelectedIndex();

        if(i > -1) {
            String input = JOptionPane.showInputDialog(this, "How many pizzas? ",
                    "Amount of pizza", JOptionPane.INFORMATION_MESSAGE);

            int amount = 0;
            try {
                amount = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Error! Invalid quantity", "ERROR INPUT", JOptionPane.ERROR_MESSAGE);
                amount = 0;
            }

            if(amount > 0)
                controller.addProduct(i, amount);
        }
    }

    private void orderPizza(){
        boolean okOrder = controller.order();

        if(!okOrder) {
            JOptionPane.showMessageDialog(this,
                    "Add at least 1 order to continue!", "EMPTY ORDER", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void orderDetails(){
        int i = checkoutList.getSelectedIndex();
        if(i > -1)
        {
            String orderDetailsStr = controller.orderDetails(i);

            if(orderDetailsStr != null)
            {
                JOptionPane.showMessageDialog(this,
                        orderDetailsStr, "Order details", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn){
            addPizza();
        }

        else if (e.getSource() == orderBtn){
            orderPizza();
        }

        else if (e.getSource() == viewOrderBtn) {
            orderDetails();
        }
    }
}

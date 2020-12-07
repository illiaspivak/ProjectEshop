package sk.kosickaakademia.illiaspivak.projekteshop.cart;

import sk.kosickaakademia.illiaspivak.projekteshop.Item;
import sk.kosickaakademia.illiaspivak.projekteshop.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> list;

    public Cart() {
        list = new ArrayList<>();
    }
    public void addItem(Item newItem){
        list.add(newItem);
    }
    public int getCountOfItemsInCart(){
        return list.size();
    }
    public double getTotalPrice(){
        double total = 0;
        for(Item temp : list){
            total = total + temp.getItemPrice();
        }
        return Util.formatPrice(total);
    }
    public void printCart(){
        System.out.println("List of items in your cart:");
        for(Item temp : list){
            System.out.println(" -  "+temp.toString());
        }
    }
}

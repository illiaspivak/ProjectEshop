package sk.kosickaakademia.illiaspivak.projekteshop.cart;

import sk.kosickaakademia.illiaspivak.projekteshop.Item;
import sk.kosickaakademia.illiaspivak.projekteshop.countable.CountItem;
import sk.kosickaakademia.illiaspivak.projekteshop.servise.ServiceInterface;
import sk.kosickaakademia.illiaspivak.projekteshop.uncountable.WeightItem;
import sk.kosickaakademia.illiaspivak.projekteshop.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> list;

    public Cart() {
        list = new ArrayList<>();
    }
    public List<Item> getList() {
        return list;
    }
    public void addItem(Item newItem){
        if(newItem.getPrice()>0) {
            if(newItem instanceof CountItem && ((CountItem) newItem).getCount()>0) {
                list.add(newItem);
            }
            if(newItem instanceof WeightItem &&((WeightItem) newItem).getWeight()>0){
                list.add(newItem);
            }
            if(newItem instanceof ServiceInterface){
                list.add(newItem);
            }
        }
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
    public int getCount() {
        return list.size();
    }

    public void printCart(){
        System.out.println("List of items in your cart:");
        for(Item temp : list){
            System.out.println(" -  "+temp.toString());
        }
    }
}

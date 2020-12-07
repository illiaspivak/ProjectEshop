package sk.kosickaakademia.illiaspivak.projekteshop.uncountable;

import sk.kosickaakademia.illiaspivak.projekteshop.Item;
import sk.kosickaakademia.illiaspivak.projekteshop.util.Util;

public class Apple extends Item implements WeightItem {
    private double weight;

    public Apple(double price, String name, double weight) {
        super(price, name); // posleme kontruktoru Item
        this.weight=weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getItemPrice() {
        return weight*getPrice();
    }

    @Override
    public String toString() {
        return "Appel: "+getName()+" , Price per kg : "+getPrice()+" , "+
                " Weight: "+weight + " , Price: "+ Util.formatPrice(getItemPrice());
    }
}

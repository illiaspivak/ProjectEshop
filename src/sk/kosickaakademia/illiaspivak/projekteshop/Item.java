package sk.kosickaakademia.illiaspivak.projekteshop;

public abstract class Item {
    private double price; // cena za kg alebo kus
    private String name;

    public Item(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract double getItemPrice();
}

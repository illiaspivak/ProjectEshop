package sk.kosickaakademia.illiaspivak.projekteshop.util;

public class Util {
    public static double convertEurToSkk(double value){
        return formatPrice(value* 30.126);
    }

    public static double formatPrice(double price){
        double result = (double) Math.round(price *100)/100;
        return result;
    }
}

package sk.kosickaakademia.illiaspivak.projekteshop.coupon;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private static String fileName = "resourse/coupon.txt";
    public static List<Coupon> getListofCouponsFromFile(){
        List<Coupon> list = new ArrayList<>();
        try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] array = data.split(" ");
                Coupon newCoupon = new Coupon(array[0],Integer.parseInt(array[1]));
                list.add(newCoupon);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public static void updateCoupons(List<Coupon> list){
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            for(Coupon temp:list){
                fw.write(temp.getCode()+" "+temp.getValue()+"\n");
            }
            fw.flush();
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

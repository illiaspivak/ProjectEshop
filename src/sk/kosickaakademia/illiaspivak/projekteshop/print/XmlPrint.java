package sk.kosickaakademia.illiaspivak.projekteshop.print;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sk.kosickaakademia.illiaspivak.projekteshop.Item;
import sk.kosickaakademia.illiaspivak.projekteshop.cart.Cart;
import sk.kosickaakademia.illiaspivak.projekteshop.countable.CountItem;
import sk.kosickaakademia.illiaspivak.projekteshop.servise.ServiceInterface;
import sk.kosickaakademia.illiaspivak.projekteshop.uncountable.WeightItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlPrint {
    public static void buildReceiptDoc(Cart cart) {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatForDateNowtime = new SimpleDateFormat("hh:mm:ss");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            // создаем пустой объект Document, в котором будем создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element bill = doc.createElement("bill");
            // добавляем корневой элемент в объект Document
            doc.appendChild(bill);
            //добавляем дату
            Element data = doc.createElement("date");
            data.appendChild(doc.createTextNode(formatForDateNow.format(date)));
            bill.appendChild(data);
            //добавляем время
            Element vrema = doc.createElement("time");
            vrema.appendChild(doc.createTextNode(formatForDateNowtime.format(date)));
            bill.appendChild(vrema);
            // items
            Element items = doc.createElement("items");
            bill.appendChild(items);
            // набор атрибутов для этого элемента
            items.setAttribute("count",String.valueOf(cart.getCount()));//сколько покупок в корзине
            for (Item type : cart.getList()) { //добавляем все покупки
                if (type instanceof CountItem) {
                    countItem(type, doc, items);
                }
                if (type instanceof WeightItem){
                    weightItem(type, doc, items);
                }
                if (type instanceof ServiceInterface){
                    serviceItem(type, doc, items);
                }
            }

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("resourse/subor.xml"));

            //записываем данные
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void weightItem(Item type, Document document, Element items) {
        //элемент item
        Element item= document.createElement("item");
        items.appendChild(item);
        //атрибуты элемента
        item.setAttribute("type","weight");

        //name
        Element name= document.createElement("name");
        item.appendChild(name); //добавляем название элемента
        item.appendChild(document.createTextNode(type.getName())); //записываем название продукта

        //weight
        Element weight= document.createElement("weight");
        item.appendChild(weight);
        String tmpString=Double.toString(((WeightItem)type).getWeight());
        item.appendChild(document.createTextNode(tmpString));

        //pricePerKg
        Element pricePerKg= document.createElement("pricePerKg");
        item.appendChild(pricePerKg);
        String tmpString1=Double.toString(type.getPrice());
        item.appendChild(document.createTextNode(tmpString1));

        //price
        Element price= document.createElement("price");
        item.appendChild(price);
        String tmpString2=Double.toString(type.getItemPrice());
        item.appendChild(document.createTextNode(tmpString2));
        //price attribute
        price.setAttribute("unit","eur");
    }

    private static void countItem(Item type, Document document, Element items) {
        //item
        Element item= document.createElement("item");
        items.appendChild(item);
        item.setAttribute("type","count");

        //name
        Element name= document.createElement("name");
        item.appendChild(name);
        item.appendChild(document.createTextNode(type.getName()));

        //count
        Element count= document.createElement("count");
        item.appendChild(count);
        String tmpString=Integer.toString(((CountItem) type).getCount());
        item.appendChild(document.createTextNode(tmpString));

        //pricePerUnit
        Element pricePerUnit= document.createElement("pricePerUnit");
        item.appendChild(pricePerUnit);
        String tmpString1=Double.toString(type.getPrice());
        item.appendChild(document.createTextNode(tmpString1));

        //price
        Element price= document.createElement("price");
        item.appendChild(price);
        String tmpString2=Double.toString(type.getItemPrice());
        item.appendChild(document.createTextNode(tmpString2));
        //price attribute
        price.setAttribute("unit","eur");
    }
    private static void serviceItem(Item type, Document document, Element items) {
        //item
        Element item= document.createElement("item");
        items.appendChild(item);
        item.setAttribute("type","service");

        //name
        Element name= document.createElement("name");
        item.appendChild(name);
        item.appendChild(document.createTextNode(type.getName()));

        //price
        Element price= document.createElement("price");
        item.appendChild(price);
        String tmpString2=Double.toString(type.getItemPrice());
        item.appendChild(document.createTextNode(tmpString2));
        price.setAttribute("unit","eur");
    }
}

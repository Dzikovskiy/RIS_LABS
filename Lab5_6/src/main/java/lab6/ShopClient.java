package lab6;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ShopClient {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:7779/ws/shop?wsdl");

        QName qname = new QName("http://lab6/", "ShopImplService");
        Service service = Service.create(url, qname);
        Shop shop = service.getPort(Shop.class);


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            System.out.println("1 - add item");
            System.out.println("2 - search by name");
            System.out.println("3 - search by price");
            line = in.readLine();

            if (line.equals("1")) {
                String name;
                int price;
                System.out.println("Please enter name: ");
                name = in.readLine();
                System.out.println("Please enter price");
                price = Integer.parseInt(in.readLine());
                System.out.println(shop.addItem(name, price));

            } else if (line.equals("2")) {
                String name;
                System.out.println("Enter name:");
                name = in.readLine();
                System.out.println(shop.searchByName(name));

            } else if (line.equals("3")) {
                int price;
                System.out.println("Please enter price");
                price = Integer.parseInt(in.readLine());
                System.out.println(shop.searchByPrice(price));

            }

        }

    }

}
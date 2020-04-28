package lab6;

import javax.jws.WebService;
import java.util.ArrayList;


@WebService(endpointInterface = "lab6.Shop")
public class ShopImpl implements Shop {
    private ArrayList<Item> itemArr = new ArrayList<>();

//все товары и сумму
    // сдоп и без отдельно показать скрины
   @Override
    public String addItem(String name, int price) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        itemArr.add(item);
        return "Item added";
    }

    @Override
    public String searchByName(String name) {
        for (Item item : itemArr) {
            if (item.getName().equals(name)) {
                return "name: " + name + " price: " + item.getPrice();
            }
        }
        return "nothing found";
    }

    @Override
	public String searchByPrice(int price) {
		for (Item item : itemArr) {
			if (item.getPrice()==price) {
				return "name: " + item.getName() + " price: " + price;
			}
		}
		return "nothing found";
	}

    @Override
    public String getAll() {
       String result = "";
        for (Item item : itemArr) {
            result = result + "\n" + item.getName();
        }
        return result;
    }

    @Override
    public int getSum(){
        int sum = 0;
        for (Item item : itemArr) {
            sum+= item.getPrice();
        }
       return sum;
    }



    private class Item {

        private String name;
        private int price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

    }

}

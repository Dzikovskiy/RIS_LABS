package lab6;

import javax.xml.ws.Endpoint;

//Endpoint publisher
public class ShopPublisher {

    public static void main(String[] args) {

    	Endpoint.publish("http://localhost:7779/ws/shop", new ShopImpl());
    }

}
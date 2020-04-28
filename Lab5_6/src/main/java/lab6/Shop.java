package lab6;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface Shop {

	@WebMethod String addItem(String name, int price);
	@WebMethod String searchByName(String name);
	@WebMethod String searchByPrice(int price);
	@WebMethod String getAll();
	@WebMethod int getSum();
 
}
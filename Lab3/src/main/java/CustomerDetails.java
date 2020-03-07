import entites.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CustomerDetails")
public class CustomerDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.joinTransaction();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet CustomerDetails</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Search Customer Information</h1>");
        String customerNr = request.getParameter("customer_nr");

        if ((customerNr != null) && !(customerNr.equals(""))) {
            Client client = entityManager.find(Client.class,Integer.valueOf(customerNr));
            if (client != null) {
               out.println("Customer's info for nr. " + customerNr + ": " + client.getUser_name() + ", "
                        + client.getFirst_address() + ", " + client.getSecond_address());
            } else {
                out.println("Customer not found.");
            }
        }

        out.println("<form>");
        out.println("Customer number: <input type='text' name='customer_nr'/>");
        out.println("<input type=submit value=Select />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();

    }
}

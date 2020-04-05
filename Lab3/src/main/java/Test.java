import entites.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit",
                System.getProperties());
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Client client = new Client();
        client.setUser_name("Jhonny2");
        client.setFirst_address("Mins22k");
        client.setSecond_address("Uruc2ha");

        entityManager.persist(client);
        entityManager.getTransaction().commit();

        entityManager.close();
        System.out.println("Id = "+ client.getIdClient());
//session factory
        //какие состояния может принимать client / persist / detached / transient
    }
}

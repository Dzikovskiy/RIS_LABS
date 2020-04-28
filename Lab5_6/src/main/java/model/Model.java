package model;


import entites.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Model {

    EntityManagerFactory emf;
    EntityManager entityManager;
    List<Client> clients = new ArrayList<>();

    public Model() {
        emf = Persistence.createEntityManagerFactory("PersistenceUnit",
                System.getProperties());
        entityManager = emf.createEntityManager();

    }


    public void addClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<Client> getAllClients() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> rootEntry = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(rootEntry);
        TypedQuery<Client> allQuery = entityManager.createQuery(all);
        clients = (List<Client>) allQuery.getResultList();

        return clients;
    }

    public void deleteClient(Client client) {
        entityManager.getTransaction().begin();
        Client client_find = entityManager.find(Client.class, client.getIdClient());

        if (client_find != null) {
            entityManager.remove(client_find);
        }
        entityManager.getTransaction().commit();

    }

    public void updateClient(Client client) {
        entityManager.getTransaction().begin();
        Client client_find = entityManager.find(Client.class, client.getIdClient());

        if (client_find != null) {
            client_find.setFirst_address(client.getFirst_address());
            client_find.setSecond_address(client.getSecond_address());
            client_find.setUser_name(client.getUser_name());
            client_find.setRoles(client.getRoles());
        }
        entityManager.getTransaction().commit();
    }

    public List<Client> searchClientByAddress(String address) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> rootEntry = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(rootEntry);
        TypedQuery<Client> allQuery = entityManager.createQuery(all);

        List<Client> clients_unsorted = (List<Client>) allQuery.getResultList();

        if (address!=null) {
            clients.clear();
            for (Client client : clients_unsorted) {
                if (address.equals(client.getFirst_address())||address.equals(client.getSecond_address())){
                    clients.add(client);
                }
            }
        }
        return clients;
    }
}

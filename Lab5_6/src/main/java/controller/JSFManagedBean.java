package controller;

import entites.Client;
import entites.Role;
import model.Model;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "JSFManagedBean")
@SessionScoped
public class JSFManagedBean implements Serializable {

    private Model model = new Model();
    private Client client = new Client();
    private List<Client> clients;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addClient(){
        Set<Role> role_set = new HashSet<Role>();
        if(role.equals("USER")){
            role_set.add(Role.USER);
        }else {
            role_set.add(Role.ADMIN);
        }

        client.setRoles(role_set);
        model.addClient(client);
    }
    public Client getClient(){
        return client;
    }

    public String getClientsPage(){
        clients = model.getAllClients();
        return "clients";
    }

    public String searchClient(){
        clients = model.searchClientByAddress(client.getFirst_address());
        return "clients";
    }

    public List<Client> getClients(){
        return clients;
    }

    public void deleteClient(){
        model.deleteClient(client);
    }
    public void updateClient(){
        Set<Role> role_set = new HashSet<Role>();
        if(role.equals("USER")){
            role_set.add(Role.USER);
        }else {
            role_set.add(Role.ADMIN);
        }

        client.setRoles(role_set);
        model.updateClient(client);
    }


}
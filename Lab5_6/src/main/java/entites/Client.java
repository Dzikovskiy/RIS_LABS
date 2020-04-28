package entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClient;



    @Column(nullable = false,length = 50)
    private String first_address;

    @Column(nullable = false,length = 50)
    private String second_address;

    @Column(nullable = false,length = 50)
    private String user_name;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "client_role", joinColumns = @JoinColumn(name = "idClient"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFirst_address() {
        return first_address;
    }

    public void setFirst_address(String first_address) {
        this.first_address = first_address;
    }

    public String getSecond_address() {
        return second_address;
    }

    public void setSecond_address(String second_address) {
        this.second_address = second_address;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}

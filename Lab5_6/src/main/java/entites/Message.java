package entites;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 50)
    private String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}

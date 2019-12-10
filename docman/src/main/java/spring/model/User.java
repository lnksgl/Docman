package spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;
    @Column
    @NotEmpty
    private String username;
    @Column
    @NotEmpty
    private String password;
    @Column
    @NotEmpty
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

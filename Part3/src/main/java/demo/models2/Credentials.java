package demo.models2;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "credentials") // Specify the table name in the database
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId") // Specify the column name in the database
    private int id;
 // Specify the column name in the database
     @NotBlank(message = "Username cannot be blank")
     @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
     @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username can only contain letters, numbers, hyphens, and underscores")
    @Column(name = "name") // Specify the column name in the database
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name = "studentPass") // Specify the column name in the database
    private String password;

    @Column(name = "`rank`") // Specify the column name in the database
    private String rank;

    public Credentials(int id, String username, String password, String rank) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rank = rank;
    }

    public Credentials() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}

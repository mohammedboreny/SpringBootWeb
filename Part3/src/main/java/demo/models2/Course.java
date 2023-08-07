package demo.models2;

import javax.persistence.*;

@Entity
@Table(name = "course") // Specify the table name in the database
public class Course {
    @Id
    @Column(name = "courseID") // Specify the column name in the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "courseName")
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
// Constructors, getters, and setters (You can generate them automatically or define them manually)
}

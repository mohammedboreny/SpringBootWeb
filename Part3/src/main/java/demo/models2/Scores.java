package demo.models2;
import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Scores {
    @EmbeddedId
    private ScoresId id;

    @ManyToOne
    @JoinColumn(name = "studentId", insertable = false, updatable = false)
    private Credentials user;

    @ManyToOne
    @JoinColumn(name = "courseID", insertable = false, updatable = false)
    private Course course;

    private double score;

    public Scores(ScoresId id, Credentials user, Course course, double score) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.score = score;
    }

    public Scores() {

    }

    public ScoresId getId() {
        return id;
    }

    public void setId(ScoresId id) {
        this.id = id;
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

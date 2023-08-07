package demo.models2;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ScoresId implements Serializable {
    @Column(name = "studentId")
    private int studentId;

    @Column(name = "courseID")
    private int courseId;

    public ScoresId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public ScoresId() {

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoresId)) return false;
        ScoresId scoresId = (ScoresId) o;
        return getStudentId() == scoresId.getStudentId() && getCourseId() == scoresId.getCourseId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getCourseId());
    }
}

package demo.db.pojos;

import demo.db.interfaces.CourseDAO;
import demo.models2.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CourseDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Course getCourseById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Course.class, id);
    }

    @Override
    public List<Course> getAllCourses() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("from Course", Course.class).getResultList();
    }

    @Override
    public void addCourse(Course course) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(course);
    }

    @Override
    public void deleteCourse(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Course course = currentSession.get(Course.class, id);
        if (course != null) {
            currentSession.delete(course);
        }
    }

    @Override
    public void updateCourse(int id, Course course) {
        Session session = sessionFactory.getCurrentSession();
        Course existingCourse = session.get(Course.class, id);
        if (existingCourse != null) {
            // Update the fields of the existingCourse with the fields from the course object
            existingCourse.setName(course.getName());
            // Update other fields as needed
            session.update(existingCourse);
        }
    }
}

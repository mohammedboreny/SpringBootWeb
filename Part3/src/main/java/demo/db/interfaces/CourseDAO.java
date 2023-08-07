package demo.db.interfaces;

import demo.models2.Course;

import java.util.List;

public interface CourseDAO {
    Course getCourseById(int id);
    List<Course> getAllCourses();
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(int id);

    void updateCourse(int id, Course course);
}

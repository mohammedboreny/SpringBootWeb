package demo.controllers;

import demo.db.interfaces.CredentialsDAO;
import demo.db.interfaces.ScoresDAO;
import demo.models2.Course;
import demo.models2.Credentials;
import demo.models2.Scores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import demo.db.interfaces.CourseDAO;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private CredentialsDAO credentialsDAO;

    @Autowired
    private ScoresDAO scoresDAO;

    @GetMapping("/credentials")
    public List<Credentials> getAllCredentials() {
        return credentialsDAO.getAllCredentials();
    }

    @GetMapping("/credentials/{id}")
    public Credentials getCredentialsById(@PathVariable int id) {
        return credentialsDAO.getCredentialsById(id);
    }

    @PostMapping("/credentials")
    public Credentials addCredentials(@RequestBody Credentials credentials) {
        return credentialsDAO.addCredentials(credentials);
    }

    @PutMapping("/credentials/{id}")
    public void updateCredentials(@PathVariable int id, @RequestBody Credentials credentials) {
         credentialsDAO.updateCredentials(id, credentials);
    }
    @DeleteMapping("/credentials/{id}")
    public void deleteCredentials(@PathVariable int id) {
        credentialsDAO.deleteCredentials(id);
    }
    // Grade Operations

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseDAO.getCourseById(id);
    }


    @PostMapping("/courses")
    public void addCourse(@RequestBody Course course) {
         courseDAO.addCourse(course);
    }

    @PutMapping("/courses/{id}")
    public void updateCourse(@PathVariable int id, @RequestBody Course course) {
        courseDAO.updateCourse(id, course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseDAO.deleteCourse(id);
    }


    // Scores CRUD Operations

    @GetMapping("/scores")
    public List<Scores> getAllScores() {
        return scoresDAO.getAllScores();
    }



//    make it return object not hashmap
//    @GetMapping("/scores/{studentId}}")
//    public Scores getScoresById(@PathVariable int studentId) {
//        return scoresDAO.getScoresByStudent(studentId);
//    }

    @PostMapping("/scores")
    public void addScores(@RequestBody Scores scores) {
         scoresDAO.saveScores(scores);
    }

    @PutMapping("/scores")
    public void updateScores(@RequestBody Scores scores) {
        scoresDAO.updateGrade(scores);
    }

    @DeleteMapping("/scores/{studentId}")
    public void deleteScores(@PathVariable int studentId) {
        scoresDAO.deleteScores(studentId);
    }

    @DeleteMapping("/scores/{studentId}/{courseId}")
    public void deleteScores(@PathVariable int studentId, @PathVariable int courseId) {
        scoresDAO.deleteScores(studentId,courseId);
    }
}

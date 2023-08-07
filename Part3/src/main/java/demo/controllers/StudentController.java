package demo.controllers;


import demo.db.interfaces.ScoresDAO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {

    private final ScoresDAO scoresDAO;
    @Autowired
    public StudentController(ScoresDAO scoresDAO) {
        this.scoresDAO = scoresDAO;
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<Map<String, Double>> getScoresByStudent(@PathVariable int studentId) {
        Map<String, Double> scoresMap = scoresDAO.getScoresByStudent(studentId);
        if (scoresMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(scoresMap);
        }
    }
    @GetMapping("/score/{courseName}")
    public ResponseEntity<Map<String, Double>> getScoresByStudent(@PathVariable String courseName) {
        Map<String, Double> scoresMap = scoresDAO.getScoresByCourse(courseName);
        if (scoresMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(scoresMap);
        }
    }
}

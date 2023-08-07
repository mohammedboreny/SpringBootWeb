package demo.db.interfaces;

import demo.models2.Scores;

import java.util.List;
import java.util.Map;

public interface ScoresDAO {
    public Map<String, Double> getScoresByStudent(int studentId);
    public Map<String, Double> getScoresByCourse(String courseName);
    public List<Scores> getAllScores();
    public void saveScores(Scores scores);
    void updateGrade(Scores grade);
    public void deleteScores(int id, int courseId);
    public void deleteScores(int id);

}

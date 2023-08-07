package demo.db.pojos;

import demo.db.interfaces.ScoresDAO;
import demo.models2.Course;
import demo.models2.Credentials;
import demo.models2.Scores;
import demo.models2.ScoresId;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScoresDB implements ScoresDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ScoresDB(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override

    public List<Scores> getAllScores() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Scores> query = currentSession.createQuery("from Scores", Scores.class);
        return query.getResultList();
    }
    @Override

    public void saveScores(Scores scores) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(scores);
    }

    @Override
    public void updateGrade(Scores grade) {
        return;
    }

    @Override
    public void deleteScores(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Scores scores = currentSession.get(Scores.class, id);
        if (scores != null) {
            currentSession.delete(scores);
        }
    }
    public void deleteScores(int studentId, int courseId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Scores scores = currentSession.get(Scores.class, new ScoresId(studentId, courseId));
        if (scores != null) {
            currentSession.delete(scores);
        }
    }
    @Override
    public Map<String, Double> getScoresByStudent(int studentId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Scores> scoresRoot = criteriaQuery.from(Scores.class);
        Join<Scores, Course> courseJoin = scoresRoot.join("course");
        Join<Scores, Credentials> credentialsJoin = scoresRoot.join("user"); // Use "user" instead of "credentials"

        criteriaQuery.multiselect(courseJoin.get("name"), scoresRoot.get("score"));
        criteriaQuery.where(criteriaBuilder.equal(credentialsJoin.get("id"), studentId));

        Query<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();

        // Convert the list of results into a Map
        Map<String, Double> scoresMap = new HashMap<>();
        for (Object[] result : results) {
            String courseName = (String) result[0];
            Double score = (Double) result[1];
            scoresMap.put(courseName, score);
        }

        return scoresMap;
    }

    public Map<String, Double> getScoresByCourse(String courseName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
            Root<Scores> scoreRoot = criteriaQuery.from(Scores.class);
            Join<Scores, Course> courseJoin = scoreRoot.join("course");

            criteriaQuery.select(scoreRoot.get("score"))
                    .where(criteriaBuilder.equal(courseJoin.get("name"), courseName));

            List<Double> scores = session.createQuery(criteriaQuery).getResultList();

            // Calculate statistics
            double[] scoresArray = scores.stream().mapToDouble(s -> s).sorted().toArray();
            Map<String, Double> stats = new HashMap<>();
            stats.put("Median", getMedian(scoresArray));
            stats.put("Mean", getMean(scoresArray));
            stats.put("Min", getMin(scoresArray));
            stats.put("Max", getMax(scoresArray));

            return stats;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    public double getMean(double[] array){
        Mean mean = new Mean();
        return mean.evaluate(array);
    }
    public double getMedian(double[] array){
        Median median = new Median();
        return  median.evaluate(array);
    }
    public double getMax(double[] array){
        return array[array.length-1];
    }
    public double getMin(double[] array)
    {
        return array[0];
    }
}

package demo.db.pojos;

import demo.db.interfaces.CredentialsDAO;
import demo.models2.Credentials;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CredentialDB implements CredentialsDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CredentialDB(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Credentials getCredentialsById(int id) {
        return null;
    }

    @Override
    public Credentials getUserByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Credentials) currentSession.createQuery("from Credentials where username = :username")
                .setParameter("username", username)
                .uniqueResult();
    }

    @Override
    public List<Credentials> getAllCredentials() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("from Credentials ", Credentials.class).getResultList();
    }

    @Override
    public Credentials addCredentials(Credentials user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
        return user;
    }

    @Override
    public void updateCredentials(int id, Credentials credentials) {
        Session currentSession = sessionFactory.getCurrentSession();
        Credentials existingCredentials = currentSession.get(Credentials.class, id);
        if (existingCredentials != null) {
            existingCredentials.setUsername(credentials.getUsername());
            existingCredentials.setPassword(credentials.getPassword());
            existingCredentials.setRank(credentials.getRank());
            // Update other attributes as needed
            currentSession.update(existingCredentials);
        }
    }

    @Override
    public void deleteCredentials(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Credentials user = currentSession.get(Credentials.class, id);
        if (user != null) {
            currentSession.delete(user);
        }
    }
}

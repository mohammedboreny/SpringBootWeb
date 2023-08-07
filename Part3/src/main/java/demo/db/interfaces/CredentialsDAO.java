package demo.db.interfaces;

import demo.models2.Credentials;

import java.util.List;

public interface CredentialsDAO {
    Credentials getCredentialsById(int id);
    Credentials getUserByUsername(String username);
    List<Credentials> getAllCredentials();
    Credentials addCredentials(Credentials user);
    public void updateCredentials(int id, Credentials credentials);
    void deleteCredentials(int id);
}
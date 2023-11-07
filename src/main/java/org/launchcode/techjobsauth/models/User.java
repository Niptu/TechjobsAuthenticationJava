package org.launchcode.techjobsauth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Entity

public class User extends AbstractEntity {
    @Id
    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.pwHash = passwordEncoder.encode(pwHash);
    }
    public boolean checkPwHash(String pwHash) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(pwHash, this.pwHash);
    }

    @Repository
    @Transactional
    public interface UserRepository extends CrudRepository<User, Integer> {
        Optional<User> findByUsername(String username);
    }


}

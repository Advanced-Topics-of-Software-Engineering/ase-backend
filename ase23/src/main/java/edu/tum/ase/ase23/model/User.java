package edu.tum.ase.ase23.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    @Indexed(unique = true)
    @NonNull
    private String email;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    @JsonIgnore
    private String password;

    @Indexed(unique = true)
    @NonNull
    private String RFIDToken;

    protected User() {}

    public User(Set<Role> roles, String email, String username, String password, String RFIDToken) {
        this.roles = roles;
        this.email = email;
        this.username = username;
        this.password = password;
        this.RFIDToken = RFIDToken;
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setUserType(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRFIDToken() {
        return password;
    }

    public void setRFIDToken(String RFIDToken) {
        this.RFIDToken = RFIDToken;
    }
}

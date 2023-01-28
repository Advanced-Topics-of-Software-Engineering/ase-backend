package edu.tum.ase.ase23.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NonNull
    private String userType;

    @Indexed(unique = true)
    @NonNull
    private String email;

    @NonNull
    @JsonIgnore
    private String password;

    @Indexed(unique = true)
    @NonNull
    private String RFIDToken;

    protected User() {}

    public User(String userType, String email, String password, String RFIDToken) {
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.RFIDToken = RFIDToken;
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

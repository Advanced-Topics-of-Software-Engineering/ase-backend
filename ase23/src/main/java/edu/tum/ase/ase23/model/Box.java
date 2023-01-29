package edu.tum.ase.ase23.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "boxes")
public class Box {
    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String name;

//    @Indexed(unique = false) // Need to check.
    @NonNull
    private String streetAddress;

    @NonNull
    private Boolean alive;


    protected Box() {}

    public Box(String name, String streetAddress, Boolean alive) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.alive = Boolean.TRUE;
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getAlive() {
        return alive;
    }

    public String getStreetAddress() {return streetAddress; }


    public void setName(String name) {
        this.name = name;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setAlive(Boolean alive) {this.alive = alive; }
}

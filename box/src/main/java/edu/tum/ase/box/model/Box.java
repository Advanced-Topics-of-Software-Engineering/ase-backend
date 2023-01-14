package edu.tum.ase.box.model;

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

    protected Box() {}

    public Box(String name, String streetAddress) {
        this.name = name;
        this.streetAddress = streetAddress;
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}

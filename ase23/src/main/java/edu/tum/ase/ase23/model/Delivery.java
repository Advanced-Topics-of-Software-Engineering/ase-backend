package edu.tum.ase.ase23.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deliveries")
public class Delivery {

    @Id
    private String id;

    @DBRef
    private Box box;

    @Reference
    private User customer;

    @Reference
    private User deliverer;

    @NonNull
    private String status;

    @Indexed(unique = true)
    @NonNull
    private String trackingID;

    protected Delivery() {}

    public Delivery(Box box, User customer, User deliverer, String status, String trackingID) {
        this.box = box;
        this.customer = customer;
        this.deliverer = deliverer;
        this.status = status;
        this.trackingID = trackingID;
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(User deliverer) {
        this.deliverer = deliverer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }

}

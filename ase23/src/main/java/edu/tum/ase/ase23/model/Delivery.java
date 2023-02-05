package edu.tum.ase.ase23.model;

import com.mongodb.lang.NonNull;
import edu.tum.ase.ase23.util.StringEncoder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.NoSuchAlgorithmException;


@Document(collection = "deliveries")
public class Delivery {

    @Id
    private String id;

    @Reference
    private Box box;

    private String customerID;

    private String delivererID;

    @NonNull
    private String status;

    @Indexed(unique = true)
    @NonNull
    private String trackingID;
    private StringEncoder StringEncoder;

    protected Delivery() {}


    public Delivery(Box box, String customerID, String delivererID) throws NoSuchAlgorithmException {
        this.box = box;
        this.customerID = customerID;
        this.delivererID = delivererID;
        this.status = "ORDERED";
        this.trackingID = StringEncoder.encode(customerID, delivererID);

    }


    //getters and setters

    public String getId() {
        return id;
    }

    public Box getBox() {
        return box;
    }

    public String getStatus() { return status; }

    public void setBox(Box box) {
        this.box = box;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDelivererID() {
        return delivererID;
    }

    public void setDelivererID(String delivererID) {
        this.delivererID = delivererID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String customerID, String delivererID) throws NoSuchAlgorithmException {
        this.trackingID = StringEncoder.encode(customerID, delivererID);
    }

}

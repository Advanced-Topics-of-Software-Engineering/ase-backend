package edu.tum.ase.ase23.payload.request;

public class DeliveryCreateRequest {
    String customerID;
    String delivererID;
    String boxID;

    public DeliveryCreateRequest(String customerID, String delivererID, String boxID) {
        this.customerID = customerID;
        this.delivererID = delivererID;
        this.boxID = boxID;
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

    public String getBoxID() {
        return boxID;
    }

    public void setBoxID(String boxID) {
        this.boxID = boxID;
    }
}

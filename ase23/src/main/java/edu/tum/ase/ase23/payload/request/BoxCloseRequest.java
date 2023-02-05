package edu.tum.ase.ase23.payload.request;

import edu.tum.ase.ase23.model.Delivery;

import java.util.List;

public class BoxCloseRequest {
    String boxId;
    String boxStatus;
    String userId;
    String delivererId;

    public BoxCloseRequest(String boxId, String boxStatus, String userId, String delivererId) {
        this.boxId = boxId;
        this.boxStatus = boxStatus;
        this.userId = userId;
        this.delivererId = delivererId;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(String boxStatus) {
        this.boxStatus = boxStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(String delivererId) {
        this.delivererId = delivererId;
    }
}

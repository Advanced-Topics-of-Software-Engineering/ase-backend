package edu.tum.ase.ase23.payload.request;

public class BoxRequest {
    String boxId;

    String RFIDToken;

    public BoxRequest(String boxId, String RFIDToken) {
        this.boxId = boxId;
        this.RFIDToken = RFIDToken;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getRFIDToken() {
        return RFIDToken;
    }

    public void setRFIDToken(String RFIDToken) {
        this.RFIDToken = RFIDToken;
    }
}

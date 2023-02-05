package edu.tum.ase.ase23.payload.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UpdateStatusDto {
    @NotBlank
    private List<String> deliveryIDList;


    public UpdateStatusDto(@NotBlank List<String> deliveryIDList) {
        this.deliveryIDList = deliveryIDList;
    }

    public List<String> getDeliveryIDList() {
        return deliveryIDList;
    }

    public void setDeliveryIDList(List<String> deliveryIDList) {
        this.deliveryIDList = deliveryIDList;
    }
}

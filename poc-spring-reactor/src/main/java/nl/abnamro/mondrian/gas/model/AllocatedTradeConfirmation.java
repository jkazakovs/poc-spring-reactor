package nl.abnamro.mondrian.gas.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AllocatedTradeConfirmation {

    @NotBlank
    @Size(max = 150)
    private String allocatedAccountId;

    public AllocatedTradeConfirmation(String allocatedAccountId) {
        this.allocatedAccountId = allocatedAccountId;
    }

    public String getAllocatedAccountId() {
        return allocatedAccountId;
    }
}

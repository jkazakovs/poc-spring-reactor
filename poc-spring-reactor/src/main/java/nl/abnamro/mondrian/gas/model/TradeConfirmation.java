package nl.abnamro.mondrian.gas.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TradeConfirmation {

    @NotBlank
    @Size(max = 150)
    private String externalAccountId;

    public TradeConfirmation(String externalAccountId) {
        this.externalAccountId = externalAccountId;
    }

    public String getExternalAccountId() {
        return externalAccountId;
    }

    public void setExternalAccountId(String externalAccountId) {
        this.externalAccountId = externalAccountId;
    }
}

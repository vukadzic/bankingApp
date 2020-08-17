package com.logate.banking.dto;

public class BankTransferDTO {

    private Double amouth;
    private String fromAccountNumber;
    private String toAccountNumber;


    public Double getAmouth() {
        return amouth;
    }

    public void setAmouth(Double amouth) {
        this.amouth = amouth;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }
}

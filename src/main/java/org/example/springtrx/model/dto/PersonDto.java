package org.example.springtrx.model.dto;


import lombok.*;



public class PersonDto {
    private String name;
    private String family;
    private String accountNumber;

    public PersonDto() {
    }

    public PersonDto(String name, String family) {
        this.name = name;
        this.family = family;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

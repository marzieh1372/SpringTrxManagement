package org.example.springtrx.model.dto;


import lombok.*;



public class PersonDto {
    private String name;
    private String family;

    public PersonDto() {
    }

    public PersonDto(String name, String family) {
        this.name = name;
        this.family = family;
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
}

package org.example.springtrx.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String family;

    @OneToOne(cascade=CascadeType.ALL)  // CascadeType.ALL means if we save a person, the account will be saved too.
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public Account getAccount() {
        return account;
    }

    public Address getAddress() {
        return address;
    }
}

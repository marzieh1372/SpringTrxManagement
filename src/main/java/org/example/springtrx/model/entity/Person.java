package org.example.springtrx.model.entity;

import jakarta.persistence.*;

/*@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor*/
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String family;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Person() {
    }

    public Person(Integer id, String name, String family, Account account, Address address) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.account = account;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

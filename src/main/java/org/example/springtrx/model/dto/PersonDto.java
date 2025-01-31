package org.example.springtrx.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.springtrx.model.entity.Account;
import org.example.springtrx.model.entity.Address;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String name;
    private String family;
    private Account account;
    private Address address;

}

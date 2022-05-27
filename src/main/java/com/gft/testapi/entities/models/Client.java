package com.gft.testapi.entities.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "member_since")
    private LocalDate memberSince;

    public Client(String name, Integer age, LocalDate memberSince) {
        this.name = name;
        this.age = age;
        this.memberSince = memberSince;
    }

    public Client updateName(String newName) {
        this.name = newName;
        return this;
    }

    public Client updateAge(Integer newAge) {
        this.age = newAge;
        return this;
    }

    public Client updateDate(LocalDate newDate) {
        this.memberSince = newDate;
        return this;
    }
}

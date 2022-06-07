package com.gft.testapi.entities.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    private String name;

    private Integer age;

    private LocalDate memberSince;

    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // private String password;

    public ClientDTO(String name, Integer age, LocalDate memberSince) {
        this.name = name;
        this.age = age;
        this.memberSince = memberSince;
    }
}

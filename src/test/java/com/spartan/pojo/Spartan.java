package com.spartan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(value="id", allowGetters = true)
//we need id for deserialization but we wanna exclude the id for serialization(post req)

public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;
}

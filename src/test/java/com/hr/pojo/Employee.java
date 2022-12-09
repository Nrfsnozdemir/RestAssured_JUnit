package com.hr.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class Employee {

    @JsonProperty("first_name")
    private String f_name;
    @JsonProperty("last_name")
    private String l_name;
    @JsonProperty("job_id")
    private String jodID;
    private int salary;
}

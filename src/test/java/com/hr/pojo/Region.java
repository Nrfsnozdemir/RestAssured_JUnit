package com.hr.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = false)

public class Region {
    @JsonProperty("region_id")
    private int id;
    @JsonProperty("region_name")
    private String name;
    private List<Link> links;
}

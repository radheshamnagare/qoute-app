package com.radhesham.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radhesham.entity.StateEntity;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@Data
@XmlRootElement(name="city-bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityBean {

    @XmlElement(name="id")
    @JsonProperty("id")
    private Integer id;

    @XmlElement(name="name")
    @JsonProperty("name")
    private String name;

    @XmlElement(name="state")
    @JsonProperty("state")
    private StateEntity state;
}

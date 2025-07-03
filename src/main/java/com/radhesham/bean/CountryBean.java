package com.radhesham.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "country-bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryBean {

    @XmlElement(name = "id")
    @JsonProperty("id")
    int id;

    @XmlElement(name = "name")
    @JsonProperty("name")
    String name;

    @XmlElement(name = "iso-code")
    @JsonProperty("iso-code")
    String isoCode;

    @XmlElement(name = "short-code")
    @JsonProperty("short-code")
    String shortCode;
}



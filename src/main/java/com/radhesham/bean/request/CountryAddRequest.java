package com.radhesham.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "country-add-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryAddRequest {

    @XmlElement(name="id")
    @JsonProperty("id")
    private Integer id;

    @XmlElement(name="country-name")
    @JsonProperty("country-name")
    private String name;

    @XmlElement(name="iso-code")
    @JsonProperty("iso-code")
    private String isoCode;

    @XmlElement(name="short-code")
    @JsonProperty("short-code")
    private String shortCode;
}

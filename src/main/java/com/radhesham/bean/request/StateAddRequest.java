package com.radhesham.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "state-add-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateAddRequest {

    @XmlElement(name="id")
    @JsonProperty("id")
    private Integer id;

    @XmlElement(name="state-name")
    @JsonProperty("state-name")
    private String name;

    @XmlElement(name="short-code")
    @JsonProperty("short-code")
    private String shortCode;

    @XmlElement(name="country-id")
    @JsonProperty("country-id")
    private Integer countryId;
}

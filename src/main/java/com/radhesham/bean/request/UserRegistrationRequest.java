package com.radhesham.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "user-registration-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationRequest {

    @XmlElement(name = "name")
    @JsonProperty("name")
    String name;

    @XmlElement(name = "email")
    @JsonProperty("email")
    String email;

    @XmlElement(name = "phone")
    @JsonProperty("phone")
    String phone;

    @XmlElement(name = "country")
    @JsonProperty("country")
    Integer country;

    @XmlElement(name = "state")
    @JsonProperty("state")
    Integer state;

    @XmlElement(name = "city")
    @JsonProperty("city")
    Integer city;
}

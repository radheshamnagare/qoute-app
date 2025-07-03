package com.radhesham.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radhesham.bean.SystemError;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement(name = "user-login-response")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginResponse {

    @XmlElement(name = "error")
    @JsonProperty("error")
    SystemError error;

    @XmlElement(name = "id")
    @JsonProperty("id")
    int id;

    @XmlElement(name = "name")
    @JsonProperty("name")
    String name;

    @XmlElement(name = "email")
    @JsonProperty("email")
    String email;

    @XmlElement(name = "phone")
    @JsonProperty("phone")
    String phone;

    @XmlElement(name = "first-login")
    @JsonProperty("first-login")
    String isFirstLogin;

    @XmlElement(name = "country")
    @JsonProperty("country")
    String country;

    @XmlElement(name = "state")
    @JsonProperty("state")
    String state;

    @XmlElement(name = "city")
    @JsonProperty("city")
    String city;
}

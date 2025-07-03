package com.radhesham.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "update-password-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePasswordRequest {

    @XmlElement(name = "id")
    @JsonProperty("id")
    int id;

    @XmlElement(name = "current-password")
    @JsonProperty("current-password")
    String currentPassword;

    @XmlElement(name = "new-password")
    @JsonProperty("new-password")
    String newPassword;

    @XmlElement(name = "confirm-password")
    @JsonProperty("confirm-password")
    String confirmPassword;
}

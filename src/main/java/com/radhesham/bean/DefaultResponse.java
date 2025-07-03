package com.radhesham.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement(name = "default-response")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultResponse {

    @XmlElement(name = "error-code")
    @JsonProperty("error-code")
    String errorCode;

    @XmlElement(name = "error-status")
    @JsonProperty("error-status")
    String errorStatus;

    @XmlElement(name = "error-description")
    @JsonProperty("error-description")
    String errorDescription;
}


package com.radhesham.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radhesham.common.ConstantsPool;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemError {

    @XmlElement(name = "error-code")
    @JsonProperty("error-code")
    String errorCode = ConstantsPool.STATUS_UNKNOWN;

    @XmlElement(name = "error-status")
    @JsonProperty("error-status")
    String errorStatus = ConstantsPool.STATUS_UNKNOWN;

    @XmlElement(name = "error-description")
    @JsonProperty("error-description")
    String errorDescription = ConstantsPool.STATUS_UNKNOWN;
}

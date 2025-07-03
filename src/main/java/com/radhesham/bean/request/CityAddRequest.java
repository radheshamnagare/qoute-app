package com.radhesham.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "city-add-request")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityAddRequest {

    @XmlElement(name = "id")
    @JsonProperty("id")
    private Integer id;

    @XmlElement(name = "city-name")
    @JsonProperty("city-name")
    private String name;

    @XmlElement(name = "state-id")
    @JsonProperty("state-id")
    private Integer stateId;
}

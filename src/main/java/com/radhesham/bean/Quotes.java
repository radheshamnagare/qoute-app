package com.radhesham.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "quote")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quotes {

    @XmlElement(name = "id")
    @JsonProperty("id")
    String id;

    @XmlElement(name = "quote")
    @JsonProperty("quote")
    String quote;

    @XmlElement(name = "author")
    @JsonProperty("author")
    String author;
}

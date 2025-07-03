package com.radhesham.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="m_state")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "state_name", unique = true)
    private String name;

    @Column(name="state_code",unique = true)
    private String shortCode;

    @CreationTimestamp
    @Column(name="insert_time",updatable = false)
    private Date insertTime;

    @UpdateTimestamp
    @Column(name="update_time",insertable = false)
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity country;
}

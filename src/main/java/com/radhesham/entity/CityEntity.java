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
@Table(name="m_city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city_name", unique = true)
    private String name;

    @CreationTimestamp
    @Column(name="insert_time",updatable = false)
    private Date insertTime;

    @UpdateTimestamp
    @Column(name="update_time",insertable = false)
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name="state_id")
    private StateEntity state;
}

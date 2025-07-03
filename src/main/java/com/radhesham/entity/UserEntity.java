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
@Table(name = "m_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "contact", unique = true)
    String phone;

    @Column(name = "password")
    String password;

    @Column(name="is_first_login",columnDefinition = "VARCHAR(5) DEFAULT 'YES'")
    String isFirstLogin;

    @CreationTimestamp
    @Column(name = "insert-time", updatable = false)
    Date insertTime;

    @UpdateTimestamp
    @Column(name = "update-time", insertable = false)
    Date updateTime;

    @ManyToOne
    @JoinColumn(name = "country_id")
    CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    StateEntity state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    CityEntity city;
}

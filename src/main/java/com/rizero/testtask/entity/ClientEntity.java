package com.rizero.testtask.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client_account")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name" , nullable = false, columnDefinition = "TEXT")
    String fullName;

    @Column(name = "gender", length = 1)
    String gender;

    @Column(name = "status", length = 20)
    String status;

    @Column(name = "create_dttm", nullable = false, updatable = false)
    OffsetDateTime createAt;

    @Column(name = "modify_dttm", nullable = false)
    OffsetDateTime updateAt;

    @PrePersist
    private void setCurrentDttm(){
        var time = OffsetDateTime.now();
        createAt = time;
        updateAt = time;
    }
    @PreUpdate
    private void updateCurrentDttm(){
        updateAt = OffsetDateTime.now();
    }
}


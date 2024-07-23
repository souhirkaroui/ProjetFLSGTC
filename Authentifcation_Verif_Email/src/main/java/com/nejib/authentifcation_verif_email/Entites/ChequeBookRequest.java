package com.nejib.authentifcation_verif_email.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table (name = "ChequeBookRequest")
public class ChequeBookRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason")
    private String reason;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "requestDate", nullable = false)
    private Date requestDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "refusalDate")
    private Date refusalDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "acceptedDate")
    private Date acceptedDate;

}


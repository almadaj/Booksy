package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "reading")
public class Reading {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

//    @Column(name = "userId", nullable = false)
//    private UUID userId;

//    @Column(name = "bookId", nullable = false)
//    private UUID bookId;

    @Column()
    private int currentPage;

    @Column()
    private Date startDate;

    @Column()
    private Date endDate;



}

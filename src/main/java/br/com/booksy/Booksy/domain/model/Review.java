package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "rating")
public class Review {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

//    @Column(name = "userId", nullable = false)
//    private UUID userId;

//    @Column(name = "bookId", nullable = false)
//    private UUID bookId;

    @Column(nullable = false)
    private double rating;

    @Column(length = 100)
    private String title;

    @Column
    private String textPost;

    @Column
    private Date postDate;

}

package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column()
    @Min(1)
    @Max(5)
    private int rating;

    @Column(length = 100)
    private String title;

    @Column
    private String textPost;

    @Column
    private Date postDate;

}

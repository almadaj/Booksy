package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
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
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @Size(max = 2000)
    @Column(length = 2000)
    private String textPost;

    @NotNull
    @Column
    private Date postDate;

}

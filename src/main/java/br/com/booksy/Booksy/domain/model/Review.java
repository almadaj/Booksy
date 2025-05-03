package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId",  referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bookId",  referencedColumnName = "id")
    private Book book;

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
    private LocalDateTime postDate;

}

package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Integer pagesNumber;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;*/

    @Column(length = 100)
    private String uploadId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

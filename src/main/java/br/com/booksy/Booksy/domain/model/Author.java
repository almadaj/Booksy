package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 150, nullable = false)
    private String firstName;

    @Column(length = 150, nullable = false)
    private String lastName;

    @Column(length = 150, nullable = false)
    private String originCountry;

    @Column(nullable = false)
    private Integer birthYear;
}

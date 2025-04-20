package br.com.booksy.Booksy.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reading")
public class Reading {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

//    @NotNull
//    @Column(name = "userId", nullable = false)
//    private UUID userId;

//    @NotNull
//    @Column(name = "bookId", nullable = false)
//    private UUID bookId;

    @Min(0)
    @Column()
    private int currentPage;

    @NotNull
    @Column()
    private Date startDate;

    @Column()
    private Date endDate;


}

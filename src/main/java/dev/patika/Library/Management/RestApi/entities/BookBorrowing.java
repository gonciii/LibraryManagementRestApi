package dev.patika.Library.Management.RestApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "bookBorrowings")

public class BookBorrowing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookBorrowing_id")
    private int id;

    @Column(name = "borrowerName",nullable = false)
    private String borrowerName;


    @Column(name = "borrowingDate",nullable = false)
    private Date borrowingDate;


    @Column(name = "returnDate")
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrowing_book_id",referencedColumnName = "book_id")
    private Book book;
}

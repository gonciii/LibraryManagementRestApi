package dev.patika.Library.Management.RestApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_name",nullable = false)
    private String name;

    @Column(name = "book_publicationYear",nullable = false)
    private Date publicationYear;

    @Column(name = "book_stock")
    private int stock;

    // author
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "book_author_id",referencedColumnName = "author_id")
    private Author author;

    // publisher
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_publisher_id",referencedColumnName = "publisher_id")
    private Publisher publisher;

    // borrowing
    @OneToMany(mappedBy = "book",cascade =CascadeType.ALL)
    private List<BookBorrowing> bookBorrowingList;

    // category
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;
}

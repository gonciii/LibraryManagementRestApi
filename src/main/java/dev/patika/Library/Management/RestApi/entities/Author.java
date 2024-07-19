package dev.patika.Library.Management.RestApi.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "authors")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(name = "author_name", nullable = false)
    private String name;

    @Column(name = "author_birthDate")
    private Date birthDate;

    @Column(name = "author_country")
    private String country;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList;
}

package dev.patika.Library.Management.RestApi.dto.response.book;

import dev.patika.Library.Management.RestApi.entities.Author;
import dev.patika.Library.Management.RestApi.entities.Category;
import dev.patika.Library.Management.RestApi.entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookResponse {

    private int id;
    private String name;
    private Date publicationYear;
    private int stock;
    private int authorId;
    private int publisherId;

}

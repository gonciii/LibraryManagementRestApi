package dev.patika.Library.Management.RestApi.dto.request.book;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookUpdateRequest {

    @NotNull(message = "Kitap ID boş olamaz")
    private Integer id;
    private String name;
    private Date publicationYear;
    private int stock;
    private int authorId;
    private int publisherId;
    private List<Integer> categoryId;

}

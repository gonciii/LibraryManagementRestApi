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

public class BookSaveRequest {

    @NotNull(message = "Kitap adı boş olamaz")
    private String name;

    @NotNull(message = "Yayın yılı boş olamaz")
    private Date publicationYear;

    @NotNull(message = "Stok bilgisi boş olamaz")
    private int stock;

    private Integer authorId;


    private Integer publisherId;

    private List<Integer> categoryId;

}

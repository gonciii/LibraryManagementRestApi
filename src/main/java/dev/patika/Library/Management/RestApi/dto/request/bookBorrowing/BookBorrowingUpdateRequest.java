package dev.patika.Library.Management.RestApi.dto.request.bookBorrowing;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookBorrowingUpdateRequest {

    @NotNull
    private int id;

    private String borrowerName;

    @NotBlank
    private Date borrowingDate;

    @NotBlank
    private Date returnDate;

    @NotBlank
    private int bookId;

}

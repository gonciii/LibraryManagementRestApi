package dev.patika.Library.Management.RestApi.dto.request.bookBorrowing;


import jakarta.persistence.NamedQueries;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookBorrowingSaveRequest {

    @NotNull
    private String borrowerName;


    private Date borrowingDate;


    private Date returnDate;


    private int bookId;

}

package dev.patika.Library.Management.RestApi.dto.response.bookBorrowing;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookBorrowingResponse {
// not validation !

    private int id;

    private String borrowerName;

    private Date borrowingDate;

    private Date returnDate;

    private int bookId;
}

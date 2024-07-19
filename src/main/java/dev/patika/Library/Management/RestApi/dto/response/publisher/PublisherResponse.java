package dev.patika.Library.Management.RestApi.dto.response.publisher;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PublisherResponse {

    private int id;
    private String name;
    private Date establishmentYear;
    private String address;
    private int bookId;
}

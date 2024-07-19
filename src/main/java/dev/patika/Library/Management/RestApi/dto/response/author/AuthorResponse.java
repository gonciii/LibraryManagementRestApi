package dev.patika.Library.Management.RestApi.dto.response.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthorResponse {

    private int id;
    private String name;


}

package dev.patika.Library.Management.RestApi.dto.request.author;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {

    @Positive(message = "ID değeri pozitif sayı olmak zorunda.")
    private int id;

    @NotNull(message = "Yazar ismi boş veya null olamaz.")
    private String name;

    @NotNull
    private Date birthDate;

    @NotNull()
    private String country;

}

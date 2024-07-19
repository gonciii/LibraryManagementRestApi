package dev.patika.Library.Management.RestApi.dto.request.author;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthorSaveRequest {

    @NotNull(message = "Yazar adı boş veya null olamaz.")
    private String name;

    @NotNull
    private Date birthDate;

    @NotNull()
    private String country;
}

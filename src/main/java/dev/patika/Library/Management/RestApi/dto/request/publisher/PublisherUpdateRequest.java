package dev.patika.Library.Management.RestApi.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PublisherUpdateRequest {

    @NotNull
    private int id;
    private String name;
    private Date establishmentYear;
    private String address;
    private List<Integer> bookId;
}
